package org.ybygjy.spring.logger;

/**
 * 定义日志实体
 * Created by leye on 2017/11/22.
 */
public class AppLog {
    private String level;
    private String appName;
    private String bizType;
    private String traceId;
    private LogOperation logOperation;
    private String methodName;
    private Long startTime;
}
