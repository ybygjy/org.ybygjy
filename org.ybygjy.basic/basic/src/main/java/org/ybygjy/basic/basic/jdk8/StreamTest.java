package org.ybygjy.basic.basic.jdk8;

import org.ybygjy.basic.basic.serialize.Gender;
import org.ybygjy.basic.basic.serialize.Person;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
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
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e ->System.out.println("Filtered value:" + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped Value:" + e))
                .collect(Collectors.toList())
                .stream()
                .forEach(System.out::print);
        Stream.of("A", "B", "C").filter(e->Objects.nonNull(e)).peek(System.out::println).forEach((e)-> System.out.println("H_" + e));
    }
    public void testFindFirst() {
        String str = null;
        System.out.println("optional present result:" + Optional.ofNullable(str).isPresent());
        Arrays.asList("A", "C", "B").stream().findFirst().ifPresent((e)->{
            System.out.println("test find first=>" + e);
        });
    }
    public void testReduce() {
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println("test reduce:" + concat);
        Double minVal = Stream.of(-1.5, -2.0, -3.0, 0.0, 1.0, 3.0, 9.0).reduce(Double.MAX_VALUE, (a, b)->{
            return Math.min(a, b);
        });
        System.out.println("test reduce minValue:" + minVal);
        Number sumVal = Stream.of(80, 30.00, 200, 300).reduce(0, (s, a)->{
            return s.intValue()+a.intValue();
        });
        System.out.println("test reduce sumVal:" + sumVal.doubleValue());
        int val = Stream.of(1, 2, 3, 4).reduce((a, b)->{
            System.out.printf("test reduce " + a + ":" + b);
            return a+b;
        }).get();
        System.out.println("test reduct=>" + val);
        concat = Stream.of("A", "B", "C", "E", "T").filter((x)->{
            return x.hashCode() < 'a';
        }).reduce(String::concat).get();
        System.out.println("test reduce=>" + concat);
    }
    public void testLimitSkip() {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            persons.add(new Person("F_" + i, "L_" + i, i, i%2 == 0 ? Gender.FEMALE : Gender.MALE));
        }
        List<String> personSegment = persons.stream().map((p)->{
            return p.getFirstName() + "_" + p.getLastName() + "_" + p.getAge() + "_" + p.getGender();
        }).limit(10).skip(5).collect(Collectors.toList());
        System.out.println("testLimitSkip:" + personSegment);

        persons = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            persons.add(new Person("F_" + i, "L_" + i, i, i%2 == 0 ? Gender.FEMALE : Gender.MALE));
        }
        List<Person> personList = persons.stream().sorted((p1, p2)->{
            return p1.getFirstName().compareTo(p2.getFirstName());
        }).limit(2).collect(Collectors.toList());
        System.out.println("test limit and skip:" + personList);
    }
    public void testSorted() {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            personList.add(new Person("F_" + i, "L_" + i, i, i%2 == 0 ? Gender.MALE : Gender.FEMALE));
        }
        List<Person> persons = personList.stream().sorted((p1, p2)->{
            return p1.getGender().compareTo(p2.getGender());
        }).limit(12).collect(Collectors.toList());
        System.out.println("test stream sorted:" + persons);
    }
    public void testMinMax() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(getClass().getResourceAsStream(getClass().getSimpleName() + ".class"))));
        int minVal = br.lines().mapToInt(String::length).min().getAsInt();
        br.close();
        br = new BufferedReader(new InputStreamReader(new BufferedInputStream(getClass().getResourceAsStream(getClass().getSimpleName() + ".class"))));
        int maxVal = br.lines().mapToInt((s)->{
            return s.length();
        }).max().getAsInt();
        br.close();
        System.out.println("minMax:" + maxVal + ",minVal:" + minVal);
    }
    public void testDistinct() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(getClass().getSimpleName() + ".class")));
        br.lines().flatMap((s)->{
            return Stream.of(s.split("\\s"));
        }).filter((s)->{
            return s.length() > 0;
        }).map(String::toUpperCase).distinct().sorted().collect(Collectors.toList()).stream().forEach(System.out::println);
        br.close();
    }
    public void testStreamMatch() {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            personList.add(new Person("F_" + i, "L_" + i, i, i%2 == 0 ? Gender.FEMALE : Gender.MALE));
        }
        boolean isAllAdult = personList.stream().allMatch((p)->{
            return p.getAge() > 18;
        });
        System.out.println("test stream match AllMatch=>" + isAllAdult);
        boolean isThereAnyChild = personList.stream().anyMatch((p)->{
            return p.getAge() < 12;
        });
        System.out.println("test stream match AnyMatch=>" + isThereAnyChild);
        boolean isThereNoneFemale = personList.stream().noneMatch((p)->{
            return p.getGender() != Gender.FEMALE;
        });
        System.out.println("test stream match noneMatch=>" + isThereNoneFemale);
    }
    public void testGenerate4Random() {
        Random seed = new Random();
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(10).forEach(System.out::println);
        Stream.generate(()->{
            return (int)(System.nanoTime() % 100);
        }).limit(10).forEach(System.out::println);
    }
    public void testGenerate4Cust() {
        Stream.generate(new PersonSupplier()).limit(10).forEach((p)->{
            System.out.println(p);
        });
    }
    private class PersonSupplier implements Supplier<Person> {
        private int index = 0;
        private Random random = new Random();
        public Person get() {
            return new Person("Test_" + index, "Test_" + index, index++, index%2 == 0 ? Gender.FEMALE : Gender.MALE);
        }
    }
    private void testStreamIterator() {
        Stream.iterate(0, n->n+3).limit(10).forEach(x->{
            System.out.println(x + " ");
        });
    }
    private void testStreamGrouping() {
        Map<Integer, List<Person>> personGroups = Stream.generate(new PersonSupplier()).limit(100).collect(Collectors.groupingBy((person)->{
            return person.getGender().hashCode();
        }));
        Iterator iterator = personGroups.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Person>> persons = (Map.Entry) iterator.next();
            System.out.println("Age " + persons.getKey() + ":" + persons.getValue().size());
        }
    }
    private void testStreamPartitioning() {
        Map<Boolean, List<Person>> children = Stream.generate(new PersonSupplier()).limit(100).collect(Collectors.partitioningBy((person)->{
            return person.getAge() < 18;
        }));
        System.out.println("test stream partitioning, children:" + children.get(true));
        System.out.println("test stream partitioning, adult:" + children.get(false));
    }
    /**
     * 入口
     * @param args 参数列表
     */
    public static void main(String[] args) throws IOException {
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
        streamTest.testFindFirst();
        streamTest.testReduce();
        streamTest.testLimitSkip();
        streamTest.testSorted();
        streamTest.testMinMax();
        streamTest.testDistinct();
        streamTest.testStreamMatch();
        streamTest.testGenerate4Random();
        streamTest.testGenerate4Cust();
        streamTest.testStreamIterator();
        streamTest.testStreamGrouping();
        streamTest.testStreamPartitioning();
    }
}
