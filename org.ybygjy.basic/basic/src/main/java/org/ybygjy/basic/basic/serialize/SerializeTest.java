package org.ybygjy.basic.basic.serialize;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SealedObject;
import java.io.*;

/**
 * Created by leye on 2017/3/23.
 */
public class SerializeTest {
    private String serializeDataFile = "data.person.sear";
    public static void main(String[] args) {
        SerializeTest serializeTest = new SerializeTest();
        serializeTest.serializeToDisk();
        serializeTest.loadObjectFromDisk();
    }
    public void serializeToDisk() {
        Person personA = new Person("YanCheng", "Wang", 88, Gender.MALE);
        Person personB = new Person("DongYing", "Liu", 88, Gender.FEMALE);
        try {
            SealedObject sealedObject = new SealedObject(personA, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        personA.setSpouse(personB);
        personB.setSpouse(personA);
        FileOutputStream fous = null;
        try {
            fous = new FileOutputStream(serializeDataFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(fous);
            outputStream.writeObject(personA);
            outputStream.writeObject(personB);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void loadObjectFromDisk() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(serializeDataFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
            Person personA = (Person) ois.readObject();
            Person personB = (Person) ois.readObject();
            System.out.println(personA);
            System.out.println(personB);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
