package org.ybygjy.jvm.struct;

import org.ybygjy.jvm.assignmemory.ObjectAgeTenuringThreshold;

/**
 * Created by leye on 2017/4/9.
 */
public class ThrowExceptionTable {
    private static final int CONSTANT_10 = 10;
    private static final int CONSTANT_20 = 20;
    private static final int CONSTANT_30 = 30;
    public int doWork() {
        Object object = new ObjectAgeTenuringThreshold();
        int tmpVal = 7 ^ 6;
System.out.println(tmpVal);
        int rtnVal = CONSTANT_10 + CONSTANT_30;
        try {
            rtnVal = CONSTANT_10;
        } catch (Exception ioe) {
            rtnVal = CONSTANT_20;
            return rtnVal;
        } finally {
            rtnVal = CONSTANT_30;
        }
        if (rtnVal == CONSTANT_10) {
            System.out.println("HelloWorld");
        } else {
            throw new RuntimeException("HelloWorld");
        }
        return rtnVal;
    }
    public static void main(String[] args) {
        new ThrowExceptionTable().doWork();
    }
}
