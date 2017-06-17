package org.ybygjy.basic.basic.serialize;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by leye on 2017/3/2.
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 0L;
    private String firstName;
    private String lastName;
    private int age;
    private Person spouse;
    private Gender gender;
    public Person(String fn, String ln, int a, Gender gender) {
        this.firstName = fn;
        this.lastName = ln;
        this.age = a;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                /*", spouse=" + spouse +*/
                ", gender=" + gender +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    private void writeObject(ObjectOutputStream oos) throws IOException, ClassNotFoundException {
        gender = gender == Gender.MALE ? Gender.FEMALE : Gender.MALE;
        oos.defaultWriteObject();
    }
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        gender = gender == Gender.MALE ? Gender.FEMALE : Gender.MALE;
    }
}
