package org.ybygjy.jvm;

/**
 * 引用计数，在对象头信息中加入引用计数器，用于记录对象引用次数，判定对象是否存活。
 * 引用计数简单、效率高，但他没有解决对象之间相互循环引用的问题
 * @author WangYanCheng
 * @version 2016年9月8日
 */
public class RefrenceCountingGC {
    public Object instance = null;
    private static byte[] bigSize = new byte[5 * 1024 * 1024];
    public static void testGC() {
        RefrenceCountingGC ref1 = new RefrenceCountingGC();
        RefrenceCountingGC ref2 = new RefrenceCountingGC();
        ref1.instance = ref2;
        ref2.instance = ref1;
        
        ref1 = null;
        ref2 = null;
    }
    public static void main(String[] args) {
        while (true) {
            System.out.println("RefrenceCountingGC.testGC()");
            RefrenceCountingGC.testGC();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
