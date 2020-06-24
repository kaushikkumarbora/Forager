package net.eq2online.example.actions;

import net.eq2online.example.ModuleInfo;
import net.eq2online.macros.scripting.api.*;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.eq2online.macros.scripting.parser.ScriptCore;
import net.eq2online.util.Util;
import net.minecraft.util.StringUtils;

@APIVersion(ModuleInfo.API_VERSION)
public class ScriptActionIsAdjacent extends ScriptAction {

    private static final String MESSAGE = "&a[Baritone] &eInvalid Number of arguments!";

    public ScriptActionIsAdjacent() {
        // Context is the context for this action, action name must be lowercase
        super(ScriptContext.MAIN, "isadjacent");
    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }

    @Override
    public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {
        boolean ret = false;
        if(params.length == 7){
            int xPos1 = ScriptCore.tryParseInt(provider.expand(macro, params[0], false),0);
            int yPos1 = ScriptCore.tryParseInt(provider.expand(macro, params[1], false),0);
            int zPos1 = ScriptCore.tryParseInt(provider.expand(macro, params[2], false),0);
            int xPos2 = ScriptCore.tryParseInt(provider.expand(macro, params[3], false),0);
            int yPos2 = ScriptCore.tryParseInt(provider.expand(macro, params[4], false),0);
            int zPos2 = ScriptCore.tryParseInt(provider.expand(macro, params[5], false),0);
            int dist = ScriptCore.tryParseInt(provider.expand(macro, params[6], false),2);
            xPos1 = Math.abs(xPos1 - xPos2);
            yPos1 = Math.abs(yPos1 - yPos2);
            zPos1 = Math.abs(zPos1 - zPos2);
            if ((xPos1 < dist) && (yPos1 < 2) && (zPos1 < 2)){
                ret = true;
            }
            else if((yPos1 < dist) && (xPos1 < 2) && (zPos1 < 2)){
                ret = true;
            }
            else if((zPos1 < dist) && (xPos1 < 2) && (yPos1 < 2)){
                ret = true;
            }
        }
        else {
            provider.actionAddChatMessage(Util.convertAmpCodes(ScriptActionIsAdjacent.MESSAGE));
        }
        return new ReturnValue(ret);
    }

    @Override
    public void onInit() {
        this.context.getCore().registerScriptAction(this);
    }

}
