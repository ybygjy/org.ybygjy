package org.ybygjy.basic.basic;

public class ThrowTest {
    public void doTest() {
        try {
            Integer i = 0;
            i = i / 0;
        } catch (RuntimeException e) {
            System.out.println("RuntimeException");
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            System.out.println("finally");
        }
        System.out.println("finish");
    }

    public static void main(String[] args) {
        new ThrowTest().doTest();
    }
}
