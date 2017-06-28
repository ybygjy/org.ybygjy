package org.ybygjy.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * Created by leye on 2017/6/18.
 */
public class InvokeVirtualTest {
    class GrandFather {
        void thinking() {
            System.out.println("I am grandfather.");
        }
    }
    class Father extends GrandFather {
        void thinking() {
            System.out.println("I am father.");
        }
    }
    class Son extends Father {
        void thinking() {
            try {
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle mh = lookup().findSpecial(GrandFather.class, "thinking", mt, getClass());
                mh.invoke(this);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        (new InvokeVirtualTest().new Son()).thinking();
    }
}
