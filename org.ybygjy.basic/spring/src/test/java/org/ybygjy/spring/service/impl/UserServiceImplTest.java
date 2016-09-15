package org.ybygjy.spring.service.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ybygjy.spring.dao.UserDao;
import org.ybygjy.spring.dao.impl.UserDaoImpl4MySql;
import org.ybygjy.spring.service.UserService;

/**
 * @author WangYanCheng
 * @version 2016年9月15日
 */
public class UserServiceImplTest {
    private UserService userService;
    private UserDao userDao;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.userService = new UserServiceImpl();
        this.userDao = new UserDaoImpl4MySql();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetUser() {
        this.userService.setUserDao(this.userDao);
        this.userService.getUser();
    }
}
