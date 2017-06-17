package org.ybygjy.jvm.clazzload;

/**
 * Created by leye on 2017/5/14.
 */
public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
    public static int value = 2;
}
