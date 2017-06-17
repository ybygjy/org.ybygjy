package org.ybygjy.jvm.assignmemory;

/**
 * -虚拟机给每个对象定义了一个对象年龄计数器。
 * -对象每熬过一次MinorGC对应年龄就会加1当增长到一定程度（15）就会被晋升到老年代
 * -对象晋升到老年代的年龄阀值通过参数-XX:MaxTenuringThreshold设置
 * <pre>java -cp ./jvm-1.0.0.jar -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution -XX:+UseSerialGC org.ybygjy.jvm.assignmemory.MaxTenuringThreshold</pre>
 * Created by leye on 2017/1/18.
 */
public class MaxTenuringThreshold {
    private static int _1MB = 1024 * 1024;
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB * 4];
        allocation3 = new byte[_1MB * 4];
        allocation3 = null;
        allocation3 = new byte[_1MB * 4];
    }
}
