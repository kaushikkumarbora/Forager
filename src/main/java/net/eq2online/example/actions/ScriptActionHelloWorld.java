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


/**
 * This is an example script action. It registers itself in the MAIN context and
 * simply emits the text string "Hello World" when invoked.
 * 
 * <p>The {@link APIVersion} annotation must match the target API version for
 * this module, we provide a central location to update the version by storing
 * it in the {@link ModuleInfo} class.</p>
 */
@APIVersion(ModuleInfo.API_VERSION)
public class ScriptActionHelloWorld extends ScriptAction {
    
    /**
     * Message to emit when called
     */
    private static final String MESSAGE = "&aHello &eWorld!";

    /**
     * Module members must have a noarg constructor
     */
    public ScriptActionHelloWorld() {
        // Context is the context for this action, action name must be lowercase
        super(ScriptContext.MAIN, "helloworld");
    }

    /**
     * This action cannot run on the network thread, eg. it is invalid in chat
     * filter scripts or onSendChatMessage handlers
     *
     * @see net.eq2online.macros.scripting.parser.ScriptAction#isThreadSafe()
     */
    @Override
    public boolean isThreadSafe() {
        return false;
    }

    /**
     * Execute the action
     * 
     * @see net.eq2online.macros.scripting.parser.ScriptAction#execute(
     *      net.eq2online.macros.scripting.api.IScriptActionProvider,
     *      net.eq2online.macros.scripting.api.IMacro,
     *      net.eq2online.macros.scripting.api.IMacroAction,
     *      java.lang.String, java.lang.String[])
     */
    @Override
    public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {
        provider.actionAddChatMessage(Util.convertAmpCodes(ScriptActionHelloWorld.MESSAGE));
        return null;
    }

    /**
     * Called after this action is initialised, the action should register
     * with the script core.
     * 
     * @see net.eq2online.macros.scripting.parser.ScriptAction#onInit()
     */
    @Override
    public void onInit() {
        this.context.getCore().registerScriptAction(this);
    }

}
