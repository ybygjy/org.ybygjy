package org.ybygjy.spring.c2.rm;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.support.MethodReplacer;

public class ReplacementComputeValue implements MethodReplacer {

    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        List<String> result = new ArrayList<String>();
        result.add(method.toString());
        result.add(obj.toString());
        for(Object arg : args) {
            result.add(arg.toString());
        }
        return Arrays.toString(result.toArray());
    }
}
