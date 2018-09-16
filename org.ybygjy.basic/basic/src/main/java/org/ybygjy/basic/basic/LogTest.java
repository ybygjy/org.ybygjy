package org.ybygjy.basic.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leye
 * @version 2018-09-06
 */
public class LogTest {
    private Logger logger = LoggerFactory.getLogger(LogTest.class);
    public void testInfoLog() {
        Long userId = 0x1010101L;
        String userName = "HelloWorld";
        logger.info("LogTest.testInfoLog|request|userId={}", userId);
        try {
            throw new RuntimeException("HelloWorld");
        } catch (Exception e) {
            logger.error("LogTest.testInfoLog|EXCEPTION|userId={},userName={}", userId, userName, e);
            logger.error("LogTest.testInfoLog|EXCEPTION|userId={}", userId, e);
        }
    }

    public static void main(String[] args) {
        LogTest logTest = new LogTest();
        logTest.testInfoLog();
    }
}
