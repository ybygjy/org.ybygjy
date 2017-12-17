package org.ybygjy.basic.basic.tenum;

import sun.misc.SharedSecrets;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EnumOrdinary {
    A(10),B(20),C(30);
    private Integer val;
    private EnumOrdinary(Integer val){
        this.val = val;
    }

    public static void main(String[] args) {
        Stream.of(EnumOrdinary.values()).forEach(s->{
            System.out.println(s.ordinal());
        });
        EnumMap<EnumOrdinary, String> enumMap = new EnumMap<EnumOrdinary, String>(EnumOrdinary.class);
        enumMap.put(EnumOrdinary.A, "Hello");
        enumMap.put(EnumOrdinary.C, "World");
        System.out.println(enumMap);
        EnumOrdinary[] result = SharedSecrets.getJavaLangAccess().getEnumConstantsShared(EnumOrdinary.class);
        System.out.println(EnumOrdinary.values());
        System.out.println(EnumOrdinary.values());
        System.out.println(EnumOrdinary.values());
        System.out.println(EnumOrdinary.values());
        Stream.of(SharedSecrets.getJavaLangAccess().getEnumConstantsShared(EnumOrdinary.class)).collect(Collectors.toList()).forEach(s->{
            System.out.println(s.val);
        });
        result[0].val = 50;
        Stream.of(SharedSecrets.getJavaLangAccess().getEnumConstantsShared(EnumOrdinary.class)).forEach(s->{
            System.out.println(s.val);
        });
        System.out.println(result);
    }
}
