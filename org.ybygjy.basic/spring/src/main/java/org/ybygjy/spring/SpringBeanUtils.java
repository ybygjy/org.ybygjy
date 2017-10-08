package org.ybygjy.spring;

import org.springframework.beans.BeanUtils;
import org.ybygjy.spring.c1.entity.User;

/**
 * Created by leye on 2017/9/16.
 */
public class SpringBeanUtils {
    public static void main(String[] args) {
        User user = new User();
        UserExt userExt = new UserExt(10001);
        BeanUtils.copyProperties(userExt, user);
        System.out.println(user);
    }
}
class UserExt {
    private Integer userNo;

    public UserExt(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }
}
