package org.ybygjy.spring.c1;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ybygjy.spring.c1.entity.User;
import org.ybygjy.spring.c1.service.UserService;

/**
 * @author WangYanCheng
 * @version 2016年9月15日
 */
public class UserServiceImplTest {
    private ApplicationContext context;
    private UserService userService;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.context = new ClassPathXmlApplicationContext(new String[]{"org/ybygjy/spring/c1/beans.xml"});
        userService = (UserService) this.context.getBean("userService");
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
        User userEntity = userService.getUser(10001);
        System.out.println(userEntity);
        Assert.assertNotNull(userEntity);
    }
    @Test
    public void testModifyUser() {
        this.userService.motifyUser(null);
    }
}
