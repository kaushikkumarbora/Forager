package net.eq2online.bmods.actions;

import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalBlock;
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
import baritone.api.BaritoneAPI;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.MathHelper;

@APIVersion(ModuleInfo.API_VERSION)
public class ScriptActionMoveto extends ScriptAction {

    private static final String MESSAGE = "&a[Baritone] &eInvalid Number of arguments!";

    public ScriptActionMoveto() {
        // Context is the context for this action, action name must be lowercase
        super(ScriptContext.MAIN, "moveto");
    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }

    @Override
    public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {
        if(params.length == 3){
            EntityPlayerSP thePlayer = this.mc.player;
            int xPos = ScriptCore.tryParseInt(provider.expand(macro, params[0], false), MathHelper.floor(thePlayer.posX));
            int yPos = ScriptCore.tryParseInt(provider.expand(macro, params[1], false),MathHelper.floor(thePlayer.posX));
            int zPos = ScriptCore.tryParseInt(provider.expand(macro, params[2], false),MathHelper.floor(thePlayer.posX));

            if((xPos != MathHelper.floor(thePlayer.posX)) || (yPos != MathHelper.floor(thePlayer.posY)) || (zPos != MathHelper.floor(thePlayer.posZ))) {
                Goal dest = new GoalBlock(xPos,yPos,zPos);
                BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(dest);
            }
        }
        else {
            provider.actionAddChatMessage(Util.convertAmpCodes(ScriptActionMoveto.MESSAGE));
        }
        return null;
    }

    @Override
    public void onInit() {
        this.context.getCore().registerScriptAction(this);
    }

}
