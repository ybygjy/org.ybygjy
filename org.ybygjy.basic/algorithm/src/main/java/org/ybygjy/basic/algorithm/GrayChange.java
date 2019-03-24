package org.ybygjy.basic.algorithm;

import java.util.concurrent.ThreadLocalRandom;

public class GrayChange {
    public static void main(String[] args) {
        final int coin = 10000;
        final int persent = 0;
        ThreadLocalRandom.current().longs().map((long s) -> Math.abs(s)).limit(10).filter(s->{
            System.out.println(s + ":" + "(" + s + "%" + coin + ")" + s % coin + "/" + persent);
            return (s % coin / persent < 1);
        }).forEach(System.out::println);
    }
}
