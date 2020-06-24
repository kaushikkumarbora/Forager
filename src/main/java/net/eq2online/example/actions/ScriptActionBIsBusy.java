package net.eq2online.example.actions;

import baritone.api.BaritoneAPI;
import net.eq2online.example.ModuleInfo;
import net.eq2online.macros.scripting.api.*;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.eq2online.util.Util;


@APIVersion(ModuleInfo.API_VERSION)
public class ScriptActionBIsBusy extends ScriptAction {


    private static final String MESSAGE = "&a[Baritone] &eInvalid Number of arguments!";

    public ScriptActionBIsBusy() {
        // Context is the context for this action, action name must be lowercase
        super(ScriptContext.MAIN, "bisbusy");
    }


    @Override
    public boolean isThreadSafe() {
        return false;
    }
    boolean ret = false;

    @Override
    public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {
        if(params.length == 0){
            ret = BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing();
        }
        else {
            provider.actionAddChatMessage(Util.convertAmpCodes(ScriptActionBIsBusy.MESSAGE));
        }
        return new ReturnValue(ret);
    }


    @Override
    public void onInit() {
        this.context.getCore().registerScriptAction(this);
    }

}
