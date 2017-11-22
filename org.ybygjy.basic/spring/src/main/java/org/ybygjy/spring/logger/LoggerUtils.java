package org.ybygjy.spring.logger;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Logger工具类
 * Created by leye on 2017/11/22.
 */
public class LoggerUtils {
    /**
     * 利用异常调用栈取日志调用来源
     * @return rtnStr
     */
    public static String getInvocationInfo() {
        Throwable throwable = new Throwable();
        Optional<StackTraceElement> optional;
        optional = Stream.of(throwable.getStackTrace()).filter(s->{
            return !s.getClassName().startsWith(LoggerUtils.class.getPackage().getName());
        }).findFirst();
        return optional.isPresent() ? optional.get().getClassName() + "#" + optional.get().getMethodName() + ":" + optional.get().getLineNumber() : LoggerConstants.UNKNOWN;
    }
    /**
     * 利用异常调用栈取日志调用来源
     * @param level 调用来源堆栈层级
     * @return rtnStr
     */
    public static String getInvocationInfo(int level) {
        level = level < 0 ? 0 : level;
        Throwable throwable = new RuntimeException();
        Optional<StackTraceElement> optional = Stream.of(throwable.getStackTrace()).limit(level + 1).skip(level).findFirst();
        return optional.isPresent() ? optional.get().getClassName() + "#" + optional.get().getMethodName() : LoggerConstants.UNKNOWN;
    }

    /**
     * 取应用系统代码
     * @return 系统代码或UNKNOWN
     */
    public static String getApplicationName() {
        return System.getProperty("app.name", LoggerConstants.UNKNOWN);
    }
}
