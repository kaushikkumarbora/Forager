package net.eq2online.example.vars;

import net.eq2online.example.ModuleInfo;
import net.eq2online.macros.scripting.api.APIVersion;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.eq2online.macros.scripting.variable.VariableCache;

/**
 * This is an example variable provider, by extending {@link VariableCache} we
 * can update variables as often or as rarely as we like, since the values are
 * stored in the underlying caches.
 * 
 * <p>The {@link APIVersion} annotation must match the target API version for
 * this module, we provide a central location to update the version by storing
 * it in the {@link ModuleInfo} class.</p>
 */
@APIVersion(ModuleInfo.API_VERSION)
public class VariableProviderExample extends VariableCache {

    /**
     * Called every frame, allows us to update variables in the current scope
     * when necessary
     * 
     * @see net.eq2online.macros.scripting.api.IVariableProvider#updateVariables(boolean)
     */
    @Override
    public void updateVariables(boolean clock) {
        if (!clock) {
            return;
        }
        
        this.storeVariable("EXAMPLE", "This is an example string value");
    }

    @Override
    public Object getVariable(String variableName) {
        return this.getCachedValue(variableName);
    }

    @Override
    public void onInit() {
        ScriptContext.MAIN.getCore().registerVariableProvider(this);
    }

}
