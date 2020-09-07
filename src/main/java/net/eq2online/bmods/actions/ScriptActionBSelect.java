package net.eq2online.bmods.actions;

import baritone.api.BaritoneAPI;
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
import net.minecraft.util.math.MathHelper;

@APIVersion(ModuleInfo.API_VERSION)
public class ScriptActionBSelect extends ScriptAction {

    private static final String MESSAGE = "&a[Baritone] &eInvalid Number of arguments!";

    public ScriptActionBSelect() {
        // Context is the context for this action, action name must be lowercase
        super(ScriptContext.MAIN, "bselect");
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
            BaritoneAPI.getProvider().getPrimaryBaritone().getSelectionManager().addSelection(Block1,Block2);
        }
        else {
            provider.actionAddChatMessage(Util.convertAmpCodes(ScriptActionBSelect.MESSAGE));
        }
        return null;
    }

    @Override
    public void onInit() {
        this.context.getCore().registerScriptAction(this);
    }

}
