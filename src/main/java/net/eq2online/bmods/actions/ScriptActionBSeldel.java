package net.eq2online.bmods.actions;

import baritone.api.BaritoneAPI;
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


@APIVersion(ModuleInfo.API_VERSION)
public class ScriptActionBSeldel extends ScriptAction {

    private static final String MESSAGE = "&a[Baritone] &eInvalid Number of arguments!";

    public ScriptActionBSeldel() {
        // Context is the context for this action, action name must be lowercase
        super(ScriptContext.MAIN, "bseldel");
    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }

    @Override
    public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {
        if(params.length == 1){
            int times = ScriptCore.tryParseInt(provider.expand(macro, params[0], false),-1);
            if(times <= 0) {
                BaritoneAPI.getProvider().getPrimaryBaritone().getSelectionManager().removeAllSelections();
            }
            else{
                for (int i = 0; i < times; i++) {
                    BaritoneAPI.getProvider().getPrimaryBaritone().getSelectionManager().removeSelection(BaritoneAPI.getProvider().getPrimaryBaritone().getSelectionManager().getLastSelection());
                }
            }
        }
        else {
            provider.actionAddChatMessage(Util.convertAmpCodes(ScriptActionBSeldel.MESSAGE));
        }
        return null;
    }

    @Override
    public void onInit() {
        this.context.getCore().registerScriptAction(this);
    }

}
