package org.ybygjy.spring.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;
import org.ybygjy.spring.web.dao.UserDao;
import org.ybygjy.spring.web.entity.User;

/**
 * UserService
 * @author WangYanCheng
 * @version 2016年9月27日
 */
public class UserService {
    private UserDao userDao;
    public String createUser(User userInst) {
        if (userInst == null) {
            throw new RuntimeException("异常信息！");
        }
        return "user.list";
    }
    public ModelAndView userDetail(String no) {
        User user = this.userDao.getUserByNo(no);
        ModelAndView mv = new ModelAndView("user.detail");
        mv.addObject("userObj", user);
        return mv;
    }
    /**
     * 查询User列表
     * @param qryData
     */
    public List<User> queryList(Map<String, String> qryData) {
        User user = new User();
        user.setAge(Integer.parseInt(qryData.get("user.age")));
        return this.userDao.getList(user);
    }
    public UserDao getUserDao() {
        return userDao;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
