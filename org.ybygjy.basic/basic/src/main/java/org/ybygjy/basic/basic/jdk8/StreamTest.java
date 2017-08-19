package org.ybygjy.basic.basic.jdk8;

import org.ybygjy.basic.basic.serialize.Gender;
import org.ybygjy.basic.basic.serialize.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by leye on 2017/8/19.
 */
public class StreamTest<T> {
    public void testForEach(Stream<T> stream) {
        stream.forEach(System.out::println);
    }
    public void testFilter(Stream<T> stream, Gender gender) {
        stream.filter((T)->{
            if (T instanceof Person) {
                return ((Person) T).getGender() == gender;
            }
            return false;
        }).forEach(System.out::println);
    }
    public void testFind(Stream<T> stream) {
        System.out.println("find first:" + stream.findFirst());
    }
    public void testFindAny(Stream<T> stream) {
        System.out.println("find any:" + stream.findAny());
    }
    public void testAnyMatch(Stream<T> stream, Predicate<T> predicate) {
        System.out.println("anyMatch:" + stream.anyMatch(predicate));
    }
    public void testMapper(Stream<T> stream, Function<T, Object> function) {
        stream.map(function).forEach(System.out::println);
    }
    public void testMap() {
        Stream<String> stream = Arrays.asList("a", "b", "c", "d", "e").stream();
        stream.map(String::toUpperCase).collect(Collectors.toList()).stream().forEach(System.out::println);
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        nums.stream().map(n->n*n).collect(Collectors.toList()).stream().forEach(System.out::println);

        Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1), Arrays.asList(6, 3), Arrays.asList(4, 5, 2));
        inputStream.flatMap(c->c.stream()).sorted().forEach(System.out::println);
    }
    public void testFilter() {
        Integer[] sixNums = new Integer[]{1, 2, 3, 4, 5, 6};
        Integer[] evens = Arrays.asList(sixNums).stream().filter(n->n%2==0).toArray(Integer[]::new);
        Arrays.asList(evens).stream().forEach(System.out::print);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(getClass().getSimpleName() + ".class")));
        String str = bufferedReader.lines().flatMap(line->Stream.of(line.split("\r\n"))).filter(word -> word.matches(".*\\w+")).collect(Collectors.toList()).stream().collect(Collectors.joining(","));
        System.out.println("str=>" + str);
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void testBuildStream() {
        Stream stream = Stream.of("a", "b", "c");
        Function<String, Integer> function = (s)->{
            return s.hashCode();
        };
        stream.map(function).forEach(System.out::println);

        String[] strArr = new String[]{"A", "B", "C"};
        stream = Stream.of(strArr);
        System.out.println(stream);
        stream = Arrays.stream(strArr);
        System.out.println(stream);
        List<String> dataList = Arrays.asList(strArr);
        stream = dataList.stream();
        String[] tmpStrArr = (String[]) stream.toArray(String[]::new);
        System.out.println("stream to array=>" + Arrays.toString(tmpStrArr));
        stream = dataList.stream();
        dataList = (List<String>) stream.collect(Collectors.toList());
        System.out.println("stream to list=>" + dataList.toString());
        stream = Stream.of("B", "B", "Q", "C", "A");
        Set setInst = (Set) stream.collect(Collectors.toSet());
        System.out.println("stream to set=>" + setInst.toString());
        stream = Stream.of(new String[]{"T", "A", "a", "b", "T"});
        Stack<String> stack = (Stack<String>) stream.collect(Collectors.toCollection(Stack::new));
        System.out.println("stream to stack=>" + stack.toString());
        stream = (Arrays.asList("A", "C", "B", "T")).stream();
        String str = stream.collect(Collectors.joining(",")).toString();
        System.out.println("stream to str=>" + str);
    }

    /**
     * 数值流的构造
     */
    public void buildIntStream() {
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 5).forEach(System.out::println);
        int[] tmparr = IntStream.range(1, 5).toArray();
        Arrays.stream(tmparr).forEach(System.out::print);
    }

    public void testPeek() {
        Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3).peek(e ->System.out.println("Filtered value:" + e)).map(String::toUpperCase).peek(e -> System.out.println("Mapped Value:" + e)).collect(Collectors.toList()).stream().forEach(System.out::println);
    }
    /**
     * 入口
     * @param args 参数列表
     */
    public static void main(String[] args) {
        List<Person> dataList = new ArrayList<>();
        dataList.add(new Person("Wang", "YanCheng", 30, Gender.MALE));
        dataList.add(new Person("Liu", "YaYa", 30, Gender.FEMALE));
        StreamTest<Person> streamTest = new StreamTest();
        streamTest.testForEach(dataList.stream());
        streamTest.testFilter(dataList.stream(), Gender.FEMALE);
        streamTest.testFilter(dataList.stream(), Gender.MALE);
        streamTest.testFind(dataList.stream());
        streamTest.testFindAny(dataList.stream());
        streamTest.testAnyMatch(dataList.stream(), (p)->p.getGender() == Gender.FEMALE);
        streamTest.testAnyMatch(dataList.stream(), (p)->p.getGender() == Gender.MALE);
        dataList.add(new Person("Unknown", "Unknown", 0, null));
        streamTest.testAnyMatch(dataList.stream(), (p)->p.getGender() == null);
        streamTest.testMapper(dataList.stream(), (p)->{
            System.out.println("Mapper:" + p);
            return new Person(p.getFirstName(), p.getLastName(), 0, p.getGender());
        });
        streamTest.testBuildStream();
        streamTest.buildIntStream();
        streamTest.testMap();
        streamTest.testFilter();
        streamTest.testPeek();
    }
}
