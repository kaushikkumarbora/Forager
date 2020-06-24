package net.eq2online.example.actions;

import net.eq2online.example.ModuleInfo;
import net.eq2online.macros.scripting.api.*;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.eq2online.macros.scripting.parser.ScriptCore;
import net.eq2online.util.Util;

import java.util.ArrayList;
import java.util.List;

@APIVersion(ModuleInfo.API_VERSION)
public class ScriptActionBFilter extends ScriptAction {

    private static final String MESSAGE = "&a[Baritone] &eInvalid Number of arguments!";

    public ScriptActionBFilter() {
        // Context is the context for this action, action name must be lowercase
        super(ScriptContext.MAIN, "bfilter");
    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }

    @Override
    public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {
        int pox,poy,poz;
        List<Integer> result =new ArrayList<Integer>();
        if(params.length == 5){
            String Arrayname = provider.expand(macro, params[0], false);
            int minx = ScriptCore.tryParseInt(provider.expand(macro,params[1],false),0);
            int maxx = ScriptCore.tryParseInt(provider.expand(macro,params[2],false),0);
            int minz = ScriptCore.tryParseInt(provider.expand(macro,params[3],false),0);
            int maxz = ScriptCore.tryParseInt(provider.expand(macro,params[4],false),0);
            int Arraysize = provider.getArraySize(macro,Arrayname);
            for(int i = 0 ; i < Arraysize; i += 3) {
                poz = ScriptCore.tryParseInt(provider.popValueFromArray(macro, Arrayname), 0);
                poy = ScriptCore.tryParseInt(provider.popValueFromArray(macro, Arrayname), 0);
                pox = ScriptCore.tryParseInt(provider.popValueFromArray(macro, Arrayname), 0);
                if((pox > maxx) || (pox < minx) || (poz > maxz) || (poz < minz)){
                    result.add(pox);
                    result.add(poy);
                    result.add(poz);
                }
            }
            provider.clearArray(macro,Arrayname);
            Arraysize = result.size();
            for(int i = Arraysize-1 ; i > -1; i -= 3) {
                provider.pushValueToArray(macro,Arrayname,result.get(i-2).toString());
                provider.pushValueToArray(macro,Arrayname,result.get(i-1).toString());
                provider.pushValueToArray(macro,Arrayname,result.get(i).toString());
            }
        }
        else {
            provider.actionAddChatMessage(Util.convertAmpCodes(ScriptActionBFilter.MESSAGE));
        }
        return null;
    }

    @Override
    public void onInit() {
        this.context.getCore().registerScriptAction(this);
    }

}
