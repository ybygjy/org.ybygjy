package org.ybygjy.jvm;

import java.lang.invoke.*;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * Created by leye on 2017/6/18.
 */
public class InvokeDynamicTest {
    public static void main(String[] args) {
        try {
            indyBootstrapMethod().invokeExact("leye");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
    public static void testMethod(String str) {
        System.out.println("HelloString:" + str);
    }
    public static CallSite BootstrapMethod(MethodHandles.Lookup lookup, String name, MethodType mt) throws Throwable {
        return new ConstantCallSite((lookup.findStatic(InvokeDynamicTest.class, name, mt)));
    }
    private static MethodType mtBootstrapMethod() {
        return MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", null);
    }
    private static MethodHandle mhBootstrapMethod() throws Throwable {
        return lookup().findStatic(InvokeDynamicTest.class, "BootstrapMethod", mtBootstrapMethod());
    }
    private static MethodHandle indyBootstrapMethod() throws Throwable {
        CallSite cs = (CallSite) mhBootstrapMethod().invokeWithArguments(lookup(), "testMethod", MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V", null));
        return cs.dynamicInvoker();
    }
}
