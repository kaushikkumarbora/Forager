package net.eq2online.example.actions;

import net.eq2online.example.ModuleInfo;
import net.eq2online.macros.scripting.api.APIVersion;
import net.eq2online.macros.scripting.api.IMacro;
import net.eq2online.macros.scripting.api.IMacroAction;
import net.eq2online.macros.scripting.api.IReturnValue;
import net.eq2online.macros.scripting.api.IScriptActionProvider;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.eq2online.util.Util;
import baritone.api.BaritoneAPI;
import net.minecraft.util.StringUtils;

@APIVersion(ModuleInfo.API_VERSION)
public class ScriptActionBExec extends ScriptAction {

    private static final String MESSAGE = "&a[Baritone] &eInvalid Number of arguments!";

    public ScriptActionBExec() {
        // Context is the context for this action, action name must be lowercase
        super(ScriptContext.MAIN, "bexec");
    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }

    @Override
    public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {
        if(params.length == 1){
            String command = StringUtils.stripControlCodes(provider.expand(macro, params[0], false));
            BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute(command);
        }
        else {
            provider.actionAddChatMessage(Util.convertAmpCodes(ScriptActionBExec.MESSAGE));
        }
        return null;
    }

    @Override
    public void onInit() {
        this.context.getCore().registerScriptAction(this);
    }

}
