package org.ybygjy.jvm.assignmemory;

/**
 * 动态对象年龄判定
 * -如果在Survivor空间中相同年龄所有对象大小的总和大于Survivor空间的一半，年龄大于或等于该年龄的对象就可以直接进入老年代，无须等到MaxTenuringThreshold中要求的年龄
 * -java -cp ./jvm-1.0.0.jar -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:+PrintTenuringDistribution -XX:MaxTenuringThreshold=15 org.ybygjy.jvm.assignmemory.ObjectAgeTenuringThreshold
 * Created by leye on 2017/1/19.
 */
public class ObjectAgeTenuringThreshold {
    private static int _1MB = 1024 * 1024;
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5;
        allocation1 = new byte[0];
        //allocation1 + allocation2 大于survivor空间一半
        allocation2 = new byte[0];
        allocation3 = new byte[_1MB * 4];
        allocation4 = new byte[_1MB * 4];
        allocation4 = null;
        allocation4 = new byte[_1MB * 4];
        allocation5 = new byte[_1MB * 4];
    }
}
