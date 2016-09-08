package org.ybygjy.jvm;

/**
 * 对像经历过两次标记后会被真正回收，标记过程中会检测对象finalize方法，该类演示finalize方法重新建立对自己的引用，避免了对象的垃圾回收
 * @author WangYanCheng
 * @version 2016年9月8日
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC ref = null;
    public void isAlive() {
        System.out.println("I'm still alive.");
    }
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalize method executed!");
        FinalizeEscapeGC.ref = this;
    }
    public static void main(String[] args) throws InterruptedException {
        FinalizeEscapeGC.ref = new FinalizeEscapeGC();
        FinalizeEscapeGC.ref = null;
        System.gc();
        Thread.sleep(500);
        if (FinalizeEscapeGC.ref != null) {
            FinalizeEscapeGC.ref.isAlive();
        } else {
            System.out.println("I'm dead!");
        }
        FinalizeEscapeGC.ref = null;
        System.gc();
        Thread.sleep(500);
        if (FinalizeEscapeGC.ref != null) {
            FinalizeEscapeGC.ref.isAlive();
        } else {
            System.out.println("I'm dead!");
        }
    }
}
