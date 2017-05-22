package org.ybygjy.jvm.clazzload;

/**
 * 类初始化场景之被动引用
 * Created by leye on 2017/5/14.
 */
public class ConstantClass {
    static {
        System.out.println("ConstantClass init!");
    }
    public static final String SLOGAN_TEXT = "HelloWorld.";
}
