package org.ybygjy.spring.orderservice.dao;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ybygjy.spring.orderservice.entity.SysUser;

/**
 * 测试用例
 * @author WangYanCheng
 * @version 2016年10月2日
 */
public class SysUserDaoTest {
    private ApplicationContext appCtx;
    private BaseDao<SysUser> sysUserDao;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.appCtx = new ClassPathXmlApplicationContext(new String[]{"classpath:org/ybygjy/spring/orderservice/beans.xml"});
        String[] beanNames = this.appCtx.getBeanDefinitionNames();
        for(String beanName : beanNames) {
            System.out.println(beanName);
        }
        this.sysUserDao = (BaseDao<SysUser>) this.appCtx.getBean("userDaoTarget");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInsert() {
        SysUser sysUser = new SysUser();
        sysUser.setUserName("HelloWorld");
        sysUser.setUserNo("100002");
        if (null == sysUserDao.selectOne(sysUser)) {
            sysUser.setPassword("123456");
            sysUser.setStateFlag(10);
            sysUser.setUserRole("SYS_USER");
            sysUser.setModifyTime(new Date());
            sysUser.setCreateTime(new Date());
            int result = sysUserDao.saveOrUpdate(sysUser);
            Assert.assertEquals(result, 1);
        }
    }

    @Test
    public void testUpdate() {
        SysUser sysUser = new SysUser();
        sysUser.setUserNo("100002");
        List<SysUser> sysUserList = this.sysUserDao.select(sysUser);
        if (null != sysUserList && sysUserList.size() > 0) {
            sysUser = sysUserList.get(0);
            sysUser.setPassword("7758521");
            sysUser.setUserName("1000002");
            sysUser.setUserRole("SYS_USER");
            sysUser.setStateFlag(20);
            sysUser.setModifyTime(new Date());
            sysUser.setCreateTime(new Date());
            int result = sysUserDao.saveOrUpdate(sysUser);
            Assert.assertEquals(result, 1);
        }
    }

    @Test
    public void testFindById() {
        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        sysUser = sysUserDao.findById(sysUser);
System.out.println(sysUser);
        Assert.assertNotNull(sysUser);
    }
    @Test
    public void testListBySysUser() {
        SysUser sysUser = new SysUser();
        sysUser.setStateFlag(10);
        List<SysUser> sysUserList = sysUserDao.select(sysUser);
        Assert.assertNotNull(sysUserList);
        for(SysUser sysUserTmp : sysUserList) {
            System.out.println(sysUserTmp);
        }
    }
    @Test
    public void testDelete() {
        SysUser sysUser = new SysUser();
        sysUser.setUserNo(this.userNo);
        List<SysUser> sysUserList = sysUserDao.select(sysUser);
        if (null != sysUserList && sysUserList.size() > 0) {
            sysUser = sysUserList.get(0);
            int result = sysUserDao.delete(sysUser);
            Assert.assertEquals(result, 1);
        }
    }
    private String userNo = "100002";
}
