package org.ybygjy.spring.exception;

/**
 * 应用系统异常常量
 * Created by leye on 2017/11/22.
 */
public enum ApplicationError {
    ;

    private String code;
    private String desc;
    private ApplicationError(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
