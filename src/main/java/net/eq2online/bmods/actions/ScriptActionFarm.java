package net.eq2online.bmods.actions;

import net.eq2online.bmods.ModuleInfo;
import net.eq2online.macros.scripting.api.APIVersion;
import net.eq2online.macros.scripting.api.IMacro;
import net.eq2online.macros.scripting.api.IMacroAction;
import net.eq2online.macros.scripting.api.IReturnValue;
import net.eq2online.macros.scripting.api.IScriptActionProvider;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import baritone.api.BaritoneAPI;


@APIVersion(ModuleInfo.API_VERSION)
public class ScriptActionFarm extends ScriptAction {

    public ScriptActionFarm() {
        // Context is the context for this action, action name must be lowercase
        super(ScriptContext.MAIN, "farm");
    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }

    @Override
    public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {

        if(!BaritoneAPI.getProvider().getPrimaryBaritone().getFarmProcess().isActive()){
            BaritoneAPI.getProvider().getPrimaryBaritone().getFarmProcess().farm();
        }
        return null;
    }

    @Override
    public void onInit() {
        this.context.getCore().registerScriptAction(this);
    }

}
