package org.ybygjy.spring.service.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ybygjy.spring.c1.entity.User;

/**
 * @author WangYanCheng
 * @version 2016年9月15日
 */
public class UserServiceImplTest {
    private ApplicationContext context;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.context = new ClassPathXmlApplicationContext(new String[]{"org/ybygjy/spring/c1/beans.xml"});
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetUser() {
        String[] userBeanNames = this.context.getBeanNamesForType(User.class);
        for(String userBeanName : userBeanNames) {
            System.out.println(this.context.getBean(userBeanName));
        }
//        UserService userService = (UserService) this.context.getBean("userService");
//        User userEntity = userService.getUser(10001);
//        System.out.println(userEntity);
//        Assert.assertNotNull(userEntity);
    }
}
