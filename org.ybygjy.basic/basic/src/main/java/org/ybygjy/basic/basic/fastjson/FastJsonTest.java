package org.ybygjy.basic.basic.fastjson;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by leye on 2017/7/13.
 */
public class FastJsonTest {
    public static void main(String[] args) {
        FastJsonTest fastJsonTest = new FastJsonTest();
        UserEntity userEntity = new UserEntity();
        List<String> testList = new ArrayList<String>();
        testList.add("A");
        testList.add("B");
        userEntity.setUserAge(18);
        userEntity.setUserName("王延成");
        userEntity.setAddress(testList);
        List<UserEntity> children = new ArrayList<UserEntity>();
        UserEntity child01 = new UserEntity();
        child01.setUserName("王小成01");
        child01.setUserAge(10);
        child01.setAddress(testList);
        children.add(child01);
        children.add(child01);
        userEntity.setChildren(children);
        Object resultStr = parseJSON(userEntity);
        System.out.println(JSONObject.toJSONString(resultStr));
    }
    public static Map<String, Object> parseJSON(Object userEntity) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Field[] fields = userEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object obj = null;
            try {
                obj = field.get(userEntity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (null == obj) {
                continue;
            }
            if (obj instanceof List) {
                List tmpArr = (List) obj;
                System.out.println(obj.getClass().getTypeParameters());
                List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();
                for (Object o : tmpArr) {
                    tmpList.add(parseJSON(o));
                }
                dataMap.put(getUnderlineName(fieldName), tmpList);
            } else {
                dataMap.put(getUnderlineName(fieldName), String.valueOf(obj));
            }
        }
        System.out.println(dataMap);
        return dataMap;
    }
    public static String getUnderlineName(String camelName) {
        char[] charArr = camelName.toCharArray();
        StringBuilder sbud = new StringBuilder();
        for (char c : charArr) {
            if (c >= 'A' && c <='Z') {
                c += 32;
                sbud.append('_').append(c);
            } else {
                sbud.append(c);
            }
        }
        return sbud.toString();
    }
    static class UserEntity {
        private String userName;
        private Integer userAge;
        private List<String> address;
        private List<UserEntity> children;
        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Integer getUserAge() {
            return userAge;
        }

        public void setUserAge(Integer userAge) {
            this.userAge = userAge;
        }

        public List<String> getAddress() {
            return address;
        }

        public void setAddress(List<String> address) {
            this.address = address;
        }

        public List<UserEntity> getChildren() {
            return children;
        }

        public void setChildren(List<UserEntity> children) {
            this.children = children;
        }
    }
}
