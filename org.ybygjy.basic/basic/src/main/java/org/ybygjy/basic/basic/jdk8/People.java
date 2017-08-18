package org.ybygjy.basic.basic.jdk8;

import org.ybygjy.basic.basic.serialize.Gender;
import org.ybygjy.basic.basic.serialize.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * Created by leye on 2017/8/19.
 */
public class People {
    private List<Person> persons = new ArrayList<>();

    public People(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getMaleList(PersonInterface filter) {
        List<Person> personList = new ArrayList<Person>();
        persons.forEach((Person e) -> {
            if (filter.test(e)) {
                personList.add(e);
            }
        });
        return personList;
    }
    public List<Person> getMaleList(Predicate<Person> predicate) {
        List<Person> personList = new ArrayList<>();
        persons.forEach(person -> {
            if (predicate.test(person)) {
                personList.add(person);
            }
        });
        return personList;
    }

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("王", "延成", 30, Gender.MALE));
        personList.add(new Person("刘", "小丫", 30, Gender.MALE));
        People people = new People(personList);
        PersonInterface personInterface = (p) -> p.getGender() == Gender.FEMALE;
        people.getMaleList(personInterface).forEach((person)->{
            System.out.println(person.toString());
        });
        Predicate<Person> predicate = (person) -> person.getGender() == Gender.MALE;
        people.getMaleList(predicate).forEach((person)->{
            System.out.println(person.toString());
        });
        int count = (int) personList.stream().parallel().sorted((p, s)->{
            return p.hashCode() > s.hashCode() ? 1 : p.hashCode() == s.hashCode() ? 0 : -1;
        }).count();
        System.out.println("parallel=>" + count);
        personList.stream().parallel().forEach((p)->{
            System.out.println("Hello:" + p.toString());
        });
        people.benchMarkSequential(people.buildBenchMarkData());
        people.benchMarkParallel(people.buildBenchMarkData());
    }
    public List<String> buildBenchMarkData() {
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            double dVal = Math.random() * 1000;
            strList.add(dVal + "");
        }
        return strList;
    }
    public void benchMarkSequential(List<String> dataList) {
        long start = System.nanoTime();
        int count = (int) (dataList.stream().sequential().sorted().count());
        long end = System.nanoTime();
        long ms = TimeUnit.NANOSECONDS.toMillis(end - start);
        System.out.println("benchmark:" + ms + "ms");
    }
    public void benchMarkParallel(List<String> dataList) {
        long start = System.nanoTime();
        int count = (int) (dataList.stream().parallel().sorted().count());
        long end = System.nanoTime();
        long ms = TimeUnit.NANOSECONDS.toMillis(end - start);
        System.out.println("benchmark:" + ms + "ms");
    }
}
