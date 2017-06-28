package org.ybygjy.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import java.lang.invoke.MethodHandles;

/**
 * Created by leye on 2017/6/28.
 */
public class ArrayTest {
    static class ClassA {
        public void println(String s) {
            System.out.println(s);
        }
    }
    private static MethodHandle getPrintlnMH(Object receiver) throws Throwable {
        MethodType methodType = MethodType.methodType(void.class, String.class);
        return MethodHandles.lookup().findVirtual(receiver.getClass(), "println", methodType).bindTo(receiver);
    }
    public static void main(String[] args) throws Throwable {
        Object obj = new ClassA();
        getPrintlnMH(obj).invokeExact("HelloWorld");
    }
}
