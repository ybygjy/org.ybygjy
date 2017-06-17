package org.ybygjy.jvm.assignmemory;

/**
 * -在发生一次MinorGC之前虚拟机会判断是否需要进行FullGC，通过检查平均Eden对象晋升大小、老年代连续可用内存空间是否大于新生代所有对象总合
 * -通过HandlePromotionFailure参数指定是否允许担保失败，分配担保失败后会重新发起一次FullGC
 * -java -cp ./*.jar -Xms20MB -Xmn20MB -Xms10MB -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:-HandlePromotionFailure org.ybygjy.jvm.assignmemory.HandlePromotionFailure
 * Created by leye on 2017/1/20.
 */
public class HandlePromotionFailure {
    private static int _1MB = 1024 * 1024;
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1MB];
        System.out.println("Complete");
    }
}
