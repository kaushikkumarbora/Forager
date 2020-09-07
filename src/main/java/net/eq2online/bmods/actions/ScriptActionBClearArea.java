package net.eq2online.bmods.actions;

import baritone.api.BaritoneAPI;
import baritone.api.schematic.FillSchematic;
import baritone.api.utils.BetterBlockPos;
import net.eq2online.bmods.ModuleInfo;
import net.eq2online.macros.scripting.api.APIVersion;
import net.eq2online.macros.scripting.api.IMacro;
import net.eq2online.macros.scripting.api.IMacroAction;
import net.eq2online.macros.scripting.api.IReturnValue;
import net.eq2online.macros.scripting.api.IScriptActionProvider;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.eq2online.macros.scripting.parser.ScriptCore;
import net.eq2online.util.Util;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

@APIVersion(ModuleInfo.API_VERSION)
public class ScriptActionBClearArea extends ScriptAction {

    private static final String MESSAGE = "&a[Baritone] &eInvalid Number of arguments!";

    public ScriptActionBClearArea() {
        // Context is the context for this action, action name must be lowercase
        super(ScriptContext.MAIN, "bclear");
    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }

    @Override
    public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {

        if(params.length == 6){
            EntityPlayerSP thePlayer = this.mc.player;
            int xPos1 = ScriptCore.tryParseInt(provider.expand(macro, params[0], false), MathHelper.floor(thePlayer.posX));
            int yPos1 = ScriptCore.tryParseInt(provider.expand(macro, params[1], false),MathHelper.floor(thePlayer.posY));
            int zPos1 = ScriptCore.tryParseInt(provider.expand(macro, params[2], false),MathHelper.floor(thePlayer.posZ));
            int xPos2 = ScriptCore.tryParseInt(provider.expand(macro, params[3], false), MathHelper.floor(thePlayer.posX));
            int yPos2 = ScriptCore.tryParseInt(provider.expand(macro, params[4], false),MathHelper.floor(thePlayer.posY));
            int zPos2 = ScriptCore.tryParseInt(provider.expand(macro, params[5], false),MathHelper.floor(thePlayer.posZ));

            BetterBlockPos Block1 = new BetterBlockPos(xPos1,yPos1,zPos1);
            BetterBlockPos Block2 = new BetterBlockPos(xPos2,yPos2,zPos2);
            BlockPos origin = new BlockPos(Math.min(Block1.getX(), Block2.getX()), Math.min(Block1.getY(), Block2.getY()), Math.min(Block1.getZ(), Block2.getZ()));
            int widthX = Math.abs(Block1.getX() - Block2.getX()) + 1;
            int heightY = Math.abs(Block1.getY() - Block2.getY()) + 1;
            int lengthZ = Math.abs(Block1.getZ() - Block2.getZ()) + 1;
            BaritoneAPI.getProvider().getPrimaryBaritone().getBuilderProcess().build("clear area", new FillSchematic(widthX, heightY, lengthZ, Blocks.AIR.getDefaultState()), origin);
        }
        else {
            provider.actionAddChatMessage(Util.convertAmpCodes(ScriptActionBClearArea.MESSAGE));
        }
        return null;
    }

    @Override
    public void onInit() {
        this.context.getCore().registerScriptAction(this);
    }

}
