package org.ybygjy.basic.basic;

public class MathTest {
    public static Double roundTest() {
        Double dVal = (Math.round(Math.random() * 1000000) / 100D);
        return dVal;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("result=>" + (MathTest.roundTest()) + ":" + ((int)(Math.random() * 10 % 2L + 1) + "0"));
        }
    }
}
