package org.ybygjy.jvm.precompile;

/**
 * Created by leye on 2017/7/14.
 */
public class PreCompileTest {
    enum colors {
        red, blue, green, gray;
    }
    static final int _FORTY_TWO = 42;
    public static int NOT_A_CONSTANTS = _FORTY_TWO;
    protected void BADLY_NAMED_CODE() {
        return;
    }
    public void NOTCamelElementName() {
        return;
    }
    public static void main(String[] args) {
        System.out.println("SayHello");
    }
}
