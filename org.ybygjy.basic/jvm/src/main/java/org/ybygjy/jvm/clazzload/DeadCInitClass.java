package org.ybygjy.jvm.clazzload;

/**
 * 虚拟机会保证一个类的<clinit>()方法在多线程环境中被正确地加锁、同步，如果多个线程同时去初始化一个类，那么只会有一个线程去执行这个类的<clinit>()方法，其它纯种都需要阻塞等待，直到活动线程执行<clinit>()方法完毕。
 * 如果在一个类的<clinit>()方法中有耗时很长的操作，就可能造成多个进程阻塞，在实际应用中这种阻塞很隐蔽；
 * Created by leye on 2017/5/18.
 */
public class DeadCInitClass {
    static {
        if (1 == 1) {
            System.out.println(Thread.currentThread() + "init DeadLoopClass.");
            while(true) {
            }
        }
    }
    public static void main(String[] args) {
        Runnable script = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                DeadCInitClass deadCInitClass = new DeadCInitClass();
                System.out.println(Thread.currentThread() + " run over");
            }
        };
        Thread th1 = new Thread(script);
        Thread th2 = new Thread(script);
        th1.start();
        th2.start();
    }
}
