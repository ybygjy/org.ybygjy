package org.ybygjy.spring.orderservice.dao.impl;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ybygjy.spring.orderservice.dao.BaseDao;
import org.ybygjy.spring.orderservice.entity.SysUser;

public class SysUserDaoImpl4JDBCTemplateTest {
    private ApplicationContext appCtx;
    private BaseDao<SysUser> sysUserDao = null;
    private String userNo = "88086";
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.appCtx = new ClassPathXmlApplicationContext(new String[]{"classpath:org/ybygjy/spring/orderservice/beans.xml"});
        this.sysUserDao = (BaseDao<SysUser>) this.appCtx.getBean("userDao4JdbcTemplate");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInsert() {
        SysUser sysUser = new SysUser();
        sysUser.setUserNo(this.userNo);
        sysUser.setUserName(sysUser.getUserNo());
        sysUser.setPassword("123456");
        sysUser.setUserRole("SYS_USER");
        sysUser.setStateFlag(10);
        int rtnFlag = sysUserDao.saveOrUpdate(sysUser);
        Assert.assertEquals(rtnFlag, 1);
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testFindById() {
        SysUser obj = new SysUser();
        obj.setUserNo(this.userNo);
        obj = this.sysUserDao.selectOne(obj);
        if (null != obj) {
            obj.setStateFlag(20);
            int rtnFlag = this.sysUserDao.saveOrUpdate(obj);
            Assert.assertEquals(rtnFlag, 1);
        }
    }

    @Test
    public void testDelete() {
        SysUser sysUser = new SysUser();
        sysUser.setUserNo(this.userNo);
        sysUser = this.sysUserDao.selectOne(sysUser);
        if (null != sysUser) {
            int rtnFlag = this.sysUserDao.delete(sysUser);
            Assert.assertEquals(rtnFlag, 1);
        }
    }

    @Test
    public void testSelect() {
        SysUser sysUser = new SysUser();
        sysUser.setUserNo(this.userNo);
        sysUser.setUserName("88");
        List<SysUser> sysUserList = this.sysUserDao.select(sysUser);
        Assert.assertNotNull(sysUserList);
        for(SysUser sysUserTemp : sysUserList) {
            System.out.println(sysUserTemp);
        }
    }

    @Test
    public void testSelectOne() {
        this.testSelect();
    }
}
