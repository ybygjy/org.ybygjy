package org.ybygjy.basic.network.rpc.serialize;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ObjectSerializationServiceTest {
    private ObjectSerializationService objectSerializationObj = new ObjectSerializationService();
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSerializeObject() {
        Person person = new Person();
        person.setUserName("WangYanCheng");
        person.setPassword("1234567890");
        try {
            byte[] dataArr = this.objectSerializationObj.serializeObject(person);
            Person newPerson = (Person) this.objectSerializationObj.readObject(dataArr);
            Assert.assertNotEquals(person, newPerson);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testReadObject() {
        fail("Not yet implemented");
    }
}
