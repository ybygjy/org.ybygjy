package org.ybygjy.basic.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 泛型
 * Created by MLS on 2016-10-31.
 */
public class GenericType {
    public void doWork() {
        List<String> entities = new ArrayList<String>();
        this.showInfo(entities);
    }
    public void showInfo(List<?> entities) {
        for(Object obj : entities) {
            System.out.println(obj);
        }
    }
    public static void main(String[] args) {
        GenericType genericType = new GenericType();
        genericType.doWork();
    }
    public void wildcard(List<?> list) {

    }
    class MyString implements Comparable<String> {
        public int compareTo(String str) {
            return 0;
        }
    }
}
