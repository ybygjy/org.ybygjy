package org.ybygjy.basic.basic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.ybygjy.basic.basic.tenum.Color;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leye on 2017/11/23.
 */
public class EnumUtils {
    public static JSONArray buildEnumToJSON(Class clazz) {
        JSONArray jsonArray = new JSONArray();
        if (!clazz.isEnum()) {
            return jsonArray;
        }
        Map<String, Field> fieldCacheMap = Stream.of(clazz.getDeclaredFields()).filter(s->{return !s.isEnumConstant() && !s.isSynthetic();}).collect(Collectors.toMap(Field::getName, s->{
            s.setAccessible(true);
            return s;
        }));
        Stream.of(clazz.getEnumConstants()).forEach(s->{
            System.out.println(s.toString());
        });
        Stream.of(clazz.getEnumConstants()).forEach(s->{
            JSONObject jsonObject = new JSONObject();
            fieldCacheMap.forEach((s1,s2)->{
                try {
                    jsonObject.put(s1, String.valueOf(s2.get(s)));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            jsonArray.add(jsonObject);
        });
        jsonArray.forEach(s->{
            System.out.println(s.toString());
        });
        return jsonArray;
    }

    public static void main(String[] args) {
        EnumUtils.buildEnumToJSON(Color.class);
    }
}
