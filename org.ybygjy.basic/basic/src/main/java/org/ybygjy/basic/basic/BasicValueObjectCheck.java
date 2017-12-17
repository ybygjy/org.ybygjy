package org.ybygjy.basic.basic;

public class BasicValueObjectCheck {
    public static void main(String[] args) {
        Boolean a = new Boolean("abc");
        Boolean c = Boolean.valueOf(false);
        Boolean b = Boolean.getBoolean("abc");
        System.out.println(a.booleanValue() == b.booleanValue());
        System.out.println(c == a);
        System.out.println(c == b);
    }
}
