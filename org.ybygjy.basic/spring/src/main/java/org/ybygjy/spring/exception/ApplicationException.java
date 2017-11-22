package org.ybygjy.spring.exception;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 应用系统异常
 * Created by leye on 2017/11/22.
 */
public class ApplicationException extends RuntimeException {
    private String errorCode;
    private String errorDesc;
    private Map<String, Object> additions = new LinkedHashMap<String, Object>();

    public ApplicationException(String errorCode) {
        this.errorCode = errorCode;
    }
}
