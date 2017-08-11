package org.ybygjy.basic.basic.junit;

import org.junit.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Junit4单元测试
 * Created by leye on 2017/7/19.
 */
public class RegularExpressionTest{
    protected static String zipRegEx = "^\\d{5}([\\-]\\d{4})?$";
    protected static Pattern pattern;
    protected String phrase;
    protected boolean match;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        pattern = Pattern.compile(zipRegEx);
    }
    @AfterClass
    public static void tearDownClass() {
        pattern = null;
        System.out.println("release resource..");
    }
    @Before
    public void setUpBefore() {
        System.out.println("set up before.");
    }
    @After
    public void tearDown() {
        System.out.println("tear down");
    }
    @Test
    public void verifyZipCodeNoMatch() throws Exception {
        Matcher matcher = pattern.matcher(phrase);
        boolean notValid = matcher.matches();
        Assert.assertEquals("invalid zipcode", notValid, phrase);
    }
    @Test
    public void verifyZipCodeGroupException() throws Exception {
        Matcher matcher = pattern.matcher(phrase);
        boolean isValid = matcher.matches();
        Assert.assertTrue(isValid);
    }
}
