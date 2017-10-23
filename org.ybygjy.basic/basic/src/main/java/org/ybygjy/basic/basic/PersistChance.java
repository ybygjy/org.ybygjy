package org.ybygjy.basic.basic;

/**
 * Created by leye on 2017/10/15.
 */
public class PersistChance {
    public static void main(String[] args) {
        int icount = 0;
        for (int i = 0; i < 10000; i++) {
            Object obj = new Object();
            if (obj.hashCode() % 5 == 0) {
                System.out.println(obj.hashCode() + ":" + i);
                icount++;
            }
        }
        System.out.println("icount:" + icount);
    }
}
