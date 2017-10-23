package org.ybygjy.basic.basic;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by leye on 2017/10/14.
 */
public class GroovyTest {
    public int sum(int a, int b) {
        return a + b;
    }
    public void doWork() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        scriptEngineManager.getEngineFactories().forEach(System.out::println);
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("groovy");
        try {
            scriptEngine.put("a", 10);
            scriptEngine.put("b", 20);
            Object obj = (Integer)scriptEngine.eval("import org.ybygjy.basic.basic.GroovyTest;return new GroovyTest().sum(a,b);");
            System.out.println(obj);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        System.out.println(scriptEngine);
    }
    public static void main(String[] args) {
        new GroovyTest().doWork();
    }
}
