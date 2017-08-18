package org.ybygjy.basic.basic.jdk8;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.*;
import java.util.function.Function;

/**
 * Created by leye on 2017/8/19.
 */
public class LambdaTest {
    public static void main(String[] args) {
        Button button = new Button();
        button.addActionListener((ActionEvent e) -> {
            System.out.printf(e.toString());
        });

        FunctionalInterface functionalInterface = () -> {};
        System.out.println("functionalInterface=>" + functionalInterface);

        Thread thread = new Thread(()->{
            System.out.println("It's from a thread in run.");
        });
        thread.run();
        Function<Integer, String> function = (t)->{return "Hello_" + t;};
        System.out.println(function + ":" + function.apply(1));
        Function<Integer, String> f = String::valueOf;
        System.out.println(f + ":" + f.apply(2));


    }
}
