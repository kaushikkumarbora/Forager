package net.eq2online.example.actions;

import baritone.api.BaritoneAPI;
import baritone.api.utils.BetterBlockPos;
import net.eq2online.example.ModuleInfo;
import net.eq2online.macros.scripting.api.*;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.eq2online.macros.scripting.parser.ScriptCore;
import net.eq2online.util.Util;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.MathHelper;

@APIVersion(ModuleInfo.API_VERSION)
public class ScriptActionBCreatesel extends ScriptAction {

    private static final String MESSAGE = "&a[Baritone] &eInvalid Number of arguments!";

    public ScriptActionBCreatesel() {
        // Context is the context for this action, action name must be lowercase
        super(ScriptContext.MAIN, "bcreatesel");
    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }

    @Override
    public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {
        BetterBlockPos BlockL = null;
        BetterBlockPos BlockR = null;
        int bx,by,bz;
        int r1x,r1y,r1z,r2x,r2y,r2z;
        if(params.length == 2){
            String Blocks = provider.expand(macro, params[0], false);
            int Bsize = provider.getArraySize(macro,Blocks);
            String Regions = provider.expand(macro, params[0], false);
            int Rsize = provider.getArraySize(macro,Regions);
            for (int i = 0; i < Bsize; i += 3) {
                bx = ScriptCore.tryParseInt(provider.getArrayElement(macro,Blocks,i).toString(),0);
                by = ScriptCore.tryParseInt(provider.getArrayElement(macro,Blocks,i+1).toString(),0);
                bz = ScriptCore.tryParseInt(provider.getArrayElement(macro,Blocks,i+2).toString(),0);
                for(int j = 0; j < Rsize; j += 6){
                    r1x = ScriptCore.tryParseInt(provider.getArrayElement(macro,Regions,j).toString(),0);
                    r1y = ScriptCore.tryParseInt(provider.getArrayElement(macro,Regions,j+1).toString(),0);
                    r1z = ScriptCore.tryParseInt(provider.getArrayElement(macro,Regions,j+2).toString(),0);
                    r2x = ScriptCore.tryParseInt(provider.getArrayElement(macro,Regions,j+3).toString(),0);
                    r2y = ScriptCore.tryParseInt(provider.getArrayElement(macro,Regions,j+4).toString(),0);
                    r2z = ScriptCore.tryParseInt(provider.getArrayElement(macro,Regions,j+5).toString(),0);
                    if((bx <= r2x) && (bx >= r1x) &&(by <= r2y) && (by >= r1y) &&(bz <= r2z) && (bz >= r1z)){
                        BlockL = new BetterBlockPos(r1x,r1y,r1z);
                        BlockR = new BetterBlockPos(r2x,r2y,r2z);
                        BaritoneAPI.getProvider().getPrimaryBaritone().getSelectionManager().addSelection(BlockL,BlockR);
                    }
                }
            }
        }
        else {
            provider.actionAddChatMessage(Util.convertAmpCodes(ScriptActionBCreatesel.MESSAGE));
        }
        return new ReturnValue(false);
    }

    @Override
    public void onInit() {
        this.context.getCore().registerScriptAction(this);
    }

}
