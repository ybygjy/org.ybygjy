package org.ybygjy.jvm.assignmemory;

/**
 * <p>新生代GC(Minor GC)，发生在新生代的垃圾收集动作，Minor GC比较频繁一般回收速度也比较快</p>
 * <p>老年代GC(MajorGC/Full GC)，发生在老年代的GC，MajorGC通常比MinorGC慢十倍</p>
 * <p>该类主要测试的场景是，新生代MinorGC后空间仍不足的情况下，对象分配到Eden，原Eden+Survivor区的对象通过分配担保转移到老年代</p>
 * <p>JVM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8</p>
 * Created by leye on 2017/1/12.
 */
public class MinorGCHandlePromotion {
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
        System.out.println("Finish");
    }
}
