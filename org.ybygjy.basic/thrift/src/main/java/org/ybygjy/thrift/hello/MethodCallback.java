package org.ybygjy.thrift.hello;

import org.apache.thrift.async.AsyncMethodCallback;


/**
 * Thrift 异步客户端回调实现
 * @author WangYanCheng
 * @version 2016年9月14日
 */
public class MethodCallback implements AsyncMethodCallback<Object> {
    private Object response = null;
    public Object getResult() {
        return this.response;
    }
    @Override
    public void onComplete(Object response) {
        this.response = response;
    }

    @Override
    public void onError(Exception exception) {
    }
}
