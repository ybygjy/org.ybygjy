package org.ybygjy.jvm.struct;

/**
 * Class类文件的结构
 * <pre>
 *     javap -verbose org.ybygjy.jvm.struct.ClassStructTest
 * </pre>
 * Created by leye on 2017/2/14.
 */
public class ClassStructTest {
    private int limit;
    private static int off = 1;
    private static int on = 2;
    public int increse() {
        return limit ++;
    }
}
