package net.eq2online.example.actions;

import baritone.api.BaritoneAPI;
import baritone.api.utils.BlockOptionalMetaLookup;
import baritone.api.utils.IPlayerContext;
import net.eq2online.example.ModuleInfo;
import net.eq2online.macros.scripting.api.*;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.eq2online.macros.scripting.parser.ScriptCore;
import net.eq2online.util.Util;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.MathHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@APIVersion(ModuleInfo.API_VERSION)
public class ScriptActionBScanWorld extends ScriptAction {

    private static final String MESSAGE = "&a[Baritone] &eInvalid Number of arguments!";

    public ScriptActionBScanWorld() {
        // Context is the context for this action, action name must be lowercase
        super(ScriptContext.MAIN, "bscan");
    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }

    @Override
    public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {
        if(params.length >= 5){
            EntityPlayerSP thePlayer = this.mc.player;
            int max = ScriptCore.tryParseInt(provider.expand(macro, params[1], false),0);
            int Y = ScriptCore.tryParseInt(provider.expand(macro, params[2], false),0);
            int Radius = ScriptCore.tryParseInt(provider.expand(macro, params[3], false),0);
            IPlayerContext ctx = BaritoneAPI.getProvider().getPrimaryBaritone().getPlayerContext();
            BlockOptionalMetaLookup filter = new BlockOptionalMetaLookup(StringUtils.stripControlCodes(provider.expand(macro, params[0], false)));
            List<BlockPos> result = BaritoneAPI.getProvider().getWorldScanner().scanChunkRadius(ctx,filter,max,Y,Radius);
            int px = MathHelper.floor(thePlayer.posX);
            int py = MathHelper.floor(thePlayer.posY);
            int pz = MathHelper.floor(thePlayer.posZ);
            Collections.sort(result, new Comparator<BlockPos>() {
                @Override
                public int compare(BlockPos o1, BlockPos o2) {
                    int Dist1 = (int) Math.sqrt(Math.pow(o1.getX() - px,2) + Math.pow(o1.getY() - py,2) + Math.pow(o1.getZ() - pz,2));
                    int Dist2 = (int) Math.sqrt(Math.pow(o2.getX() - px,2) + Math.pow(o2.getY() - py,2) + Math.pow(o2.getZ() - pz,2));
                    return Dist1 - Dist2;
                }
            });
            String Arrayname = provider.expand(macro,params[4],false);
            provider.clearArray(macro,Arrayname);
            for(BlockPos block:result){
                provider.pushValueToArray(macro,Arrayname,"" + block.getX());
                provider.pushValueToArray(macro,Arrayname,"" + block.getY());
                provider.pushValueToArray(macro,Arrayname,"" + block.getZ());
            }
        }
        else {
            provider.actionAddChatMessage(Util.convertAmpCodes(ScriptActionBScanWorld.MESSAGE));
        }
        return null;
    }

    @Override
    public void onInit() {
        this.context.getCore().registerScriptAction(this);
    }

}
