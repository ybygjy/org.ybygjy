package org.ybygjy.spring.c2;

import java.net.URL;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.ybygjy.spring.c2.impl.AsyncAccounting;
import org.ybygjy.spring.c2.impl.GeneralAccounting;
import org.ybygjy.spring.c2.rm.MyValueCalculator;

public class AccountingTest {
    private ListableBeanFactory context;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        URL urlInst = Thread.currentThread().getContextClassLoader().getResource("org/ybygjy/spring/c2/beans.xml");
        ConfigurableListableBeanFactory configurableListableBeanFactory = new XmlBeanFactory(new FileSystemResource(urlInst.getFile()));
        this.context = configurableListableBeanFactory;
        Assert.assertNotNull(this.context);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAccounting() {
        String[] beanNames = this.context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        AsyncAccounting asyncAccounting = (AsyncAccounting) this.context.getBean("asyncAccounting");
        System.out.println(asyncAccounting.getAccountingHelper());
        GeneralAccounting generalAccounting = (GeneralAccounting) this.context.getBean("generalAccounting");
        System.out.println(generalAccounting.getAccountingHelper());
        Assert.assertNotSame(asyncAccounting, generalAccounting);
        MyValueCalculator calculator = (MyValueCalculator) this.context.getBean("myValueCalculator");
        String value = calculator.computeValue("HelloWorld");
        System.out.println("Calculator result=>" + value);
    }
}
