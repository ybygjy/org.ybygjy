package org.hessian.test;

import javax.annotation.Resource;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ybygjy.hessian.dto.UserInfo;
import org.ybygjy.hessian.service.UserService;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-test.xml")
public class AppTest extends TestCase
{
	@Resource(name="userService")
	private UserService userService;
	@Before
	public void setUp(){
	}
	@After
	public void tearDown() {
	}
	@Test
	public void test() {
		UserInfo userInfo = new UserInfo();
		userInfo.setAge(200);
		userInfo.setExtData("ExtData_001", "ExtData_001");
		userInfo.setExtData3("ExtData_001", "ExtData_002");
		userInfo = this.userService.getUserDetail(userInfo);
		Assert.assertNotNull(userInfo);
		System.out.println(userInfo);
	}
	public String sayHello(String message) {
		System.out.println("HelloWorld#" + message);
		return "HelloWorld";
	}
}
