package net.eq2online.example.actions;

import net.eq2online.example.ModuleInfo;
import net.eq2online.macros.scripting.api.APIVersion;
import net.eq2online.macros.scripting.api.IMacro;
import net.eq2online.macros.scripting.api.IMacroAction;
import net.eq2online.macros.scripting.api.IReturnValue;
import net.eq2online.macros.scripting.api.IScriptActionProvider;
import net.eq2online.macros.scripting.api.ReturnValue;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.eq2online.util.Util;
import net.minecraft.util.StringUtils;

/**
 * An example script action which simply takes the input text and makes it
 * yellow, used as an example of expanding input arguments and using return
 * values. It emits the server name if called with no arguments, or the supplied
 * string in yellow if called with an argument.
 * 
 * <p>The {@link APIVersion} annotation must match the target API version for
 * this module, we provide a central location to update the version by storing
 * it in the {@link ModuleInfo} class.</p>
 */
@APIVersion(ModuleInfo.API_VERSION)
public class ScriptActionYellow extends ScriptAction {

    public ScriptActionYellow() {
        super(ScriptContext.MAIN, "yellow");
    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }
    
    @Override
    public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {
        // Fetch the first argument, if it's empty return SERVERNAME in expansion marks
        String text = params.length > 0 ? params[0] : "%SERVERNAME%";
        
        // Expand variables in the string
        String expanded = provider.expand(macro, text, false);
        
        // Strip all the control codes in the string, prepend a yellow control code, then expand
        String coloured = Util.convertAmpCodes("&e" + StringUtils.stripControlCodes(expanded));
        
        // return the decorated value
        return new ReturnValue(coloured);
    }
    
    @Override
    public void onInit() {
        this.context.getCore().registerScriptAction(this);
    }
}
