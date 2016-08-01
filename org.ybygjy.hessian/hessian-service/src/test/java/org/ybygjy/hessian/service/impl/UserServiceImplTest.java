package org.ybygjy.hessian.service.impl;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ybygjy.hessian.dto.UserInfo;
import org.ybygjy.hessian.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class UserServiceImplTest {
	@Resource(name="hessian-service")
	private UserService userServiceImpl;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		UserInfo userInfo = new UserInfo();
		userInfo.setAge(10);
		userInfo.setUserName("userName_001");
		userInfo.setUserNumber("userNumber_889910");
		userInfo = userServiceImpl.getUserDetail(userInfo);
		System.out.println(userInfo);
		Assert.assertNotNull(userInfo);
	}

}
