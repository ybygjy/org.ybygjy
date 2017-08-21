package org.ybygjy.basic.basic.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 测试套件
 * Created by leye on 2017/7/20.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({RegularExpressionTest.class, ParametricRegularExpressionTest.class})
public class JUnit4Suite {
}
