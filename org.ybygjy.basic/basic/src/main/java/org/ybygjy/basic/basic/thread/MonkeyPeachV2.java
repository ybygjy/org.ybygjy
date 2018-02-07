package org.ybygjy.basic.basic.thread;

/**
 * @author leye
 * @version 2018-02-06
 */
public class MonkeyPeachV2 implements Runnable {
    private static Integer peachNums = 9;
    private int eatNums;
    public MonkeyPeachV2(int eatNums) {
        this.eatNums = eatNums;
    }
    @Override
    public void run() {
        while(this.eatPeach()) {
//            try {
//                Thread.currentThread().sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
    private boolean eatPeach() {
        synchronized (peachNums) {
System.out.println(Thread.currentThread().getName() + ":" + peachNums.hashCode() + "," + eatNums);
            int currNums = peachNums - eatNums;
//            try {
//                Thread.currentThread().sleep((long) (Math.random() * 100));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            if (currNums >= 0) {
                peachNums = currNums;
                System.out.println(Thread.currentThread().getName() + ",苹果数量:" + peachNums + "," + eatNums);
                return true;
            }
            return false;
        }
    }
    public static void main(String[] args) {
        new Thread(new MonkeyPeachV2(1)).start();
        new Thread(new MonkeyPeachV2(2)).start();
        new Thread(new MonkeyPeachV2(3)).start();
        new Thread(new MonkeyPeachV2(4)).start();
        new Thread(new MonkeyPeachV2(7)).start();
        //new Thread(new MonkeyPeachV2(3)).start();
    }
}