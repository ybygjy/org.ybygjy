package org.ybygjy.spring.logger;

import java.util.Map;

/**
 * 定义日志实体
 * Created by leye on 2017/11/22.
 */
public class AppLog {
    /** 日志级别*/
    private String level;
    /** 业务标识*/
    private String bizType;
    /** 上下文唯一标识*/
    private String ctxTraceId;
    /** 调用操作*/
    private LogInvokeInfo invokeInfo;
    /** 方法名*/
    private String methodName;
    /** 日志开始时间*/
    private Long startTime;
    private Map<String, Object> request;
    private Map<String, Object> response;
    /** 影响行数*/
    private Integer effectCount;
    /** 业务状态*/
    private Integer bizResult;
    /** 异常信息*/
    private Object exceptionInfo;
}
