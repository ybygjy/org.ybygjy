package org.ybygjy.basic.basic.junit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;

/**
 * Created by leye on 2017/7/30.
 */
@RunWith(Parameterized.class)
public class ParametricRegularExpressionTest extends RegularExpressionTest {

    public ParametricRegularExpressionTest(String phrase, boolean match) {
        this.phrase = phrase;
        this.match = match;
    }
    @Parameterized.Parameters
    public static Collection regExpValues() {
        return Arrays.asList(new Object[][]{
            {"20101", true},
            {"2017x2", false},
            {"20-210", false},
            {"102010100101", false}
        });
    }
    @Test
    public void verifyCode() {
        System.out.println(this.phrase + ":" + this.match);
        Matcher matcher = pattern.matcher(phrase);
        boolean isValid = matcher.matches();
        Assert.assertEquals("Pattern did not validate zip code!", isValid, match);
    }
}
