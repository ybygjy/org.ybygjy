package org.ybygjy.jvm.struct;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by leye on 2017/2/15.
 */
public class BinaryTest {
    public static void main(String[] args) {
        String filePath = "/Users/leye/1006_gitwork/org.ybygjy/org.ybygjy.basic/jvm/target/classes/org/ybygjy/jvm/struct/ClassStructTest.class";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            byte[] buff = new byte[1024];
            int flag = -1;
            while ((flag = fis.read(buff)) != -1) {
                for (int i = 0; i < flag; i++) {
                    System.out.print(Integer.toBinaryString(buff[i]) + "," + Integer.toHexString(buff[i]) + '\t');
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println();
        System.out.println(Integer.toHexString(Integer.parseInt("11001010", 2)));
    }
}
