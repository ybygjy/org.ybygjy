package org.ybygjy.basic.basic;

public class AutoInboxAndUnboxTest {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        //Integer对-128~127之间的数是有缓存的，之外的直接new Integer()
        System.out.println(e == f);//false
        System.out.println(c == d);//true
        System.out.println(c == (a + b));//true
        System.out.println(c.equals(a + b));//true
        System.out.println(g == (a + b));//true
        System.out.println(g.equals(a + b));//false
    }
}
