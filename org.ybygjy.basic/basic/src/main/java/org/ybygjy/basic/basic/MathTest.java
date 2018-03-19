package org.ybygjy.basic.basic;

public class MathTest {
    public static Double roundTest() {
        Double dVal = (Math.round(Math.random() * 1000000) / 100D);
        return dVal;
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println("result=>" + (MathTest.roundTest()) + ":" + ((int)(Math.random() * 10 % 2L + 1) + "0"));
//        }
        int totalCount = 100;
        int pageSize = 10;
        int pageNums = (totalCount + pageSize - 1) / pageSize;
        int limitStart = 0;
        for (int i = 0; i < pageNums; i++) {
            System.out.println("limit " + limitStart + ", " + pageSize);
            limitStart = i * pageSize;
        }
    }
}
