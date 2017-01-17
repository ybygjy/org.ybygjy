package org.ybygjy.jvm.assignmemory;

/**
 * -大对象指长度很长的数组、字符串，需要大量连续内存空间的Java对象
 * -经常出现大对象容易导致内存提前触发垃圾回收以提供连续空间放置大对象
 * -虚拟机提供了-XX:+PreTenureSizeThreshold参数，指定超过阀值的对象直接在老年代分配，避免Eden和Survivor频繁发生MinorGC
 * -该参数只在Serial、ParNew两款收集器模式下有效
 * <pre>java -cp ./jvm-1.0.0.jar -verbose:gc -Xmn10M -Xms20M -Xmx20M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 org.ybygjy.jvm.assignmemory.PretenureThreshold</pre>
 * Created by leye on 2017/1/17.
 */
public class PretenureThreshold {
    private static int _1MB = 1024 * 1024;
    /**
     * -verbose:gc -XX:+UseSerialGC -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:PreTenureThreshold
     * @param args args
     */
    public static void main(String[] args) {
        byte[] buff = new byte[4 * _1MB];
        System.out.println("OK");
    }
}
