package org.ybygjy.spring.orderservice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybygjy.spring.orderservice.dao.SysUserDao;
import org.ybygjy.spring.orderservice.entity.SysUser;

@Service
public class SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    public boolean createSysUser(Map<String, String> dataMap) {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(dataMap.get("user_name"));
        sysUser.setUserNo(dataMap.get("user_no"));
        sysUser.setUserRole(dataMap.get("user_role"));
        sysUser.setStateFlag(Integer.parseInt(dataMap.get("state_flag")));
        int rtnFlag = sysUserDao.insert(sysUser);
        return (rtnFlag == 1);
    }
    public boolean deleteSysUser(Map<String, String> dataMap) {
        SysUser sysUser = new SysUser();
        sysUser.setId(Long.parseLong(dataMap.get("user_id")));
        sysUser.setUserNo(dataMap.get("user_no"));
        int rtnFlag = sysUserDao.delete(sysUser);
        return (rtnFlag == 1);
    }
    public List<SysUser> getAllSysUser(int limit, int position) {
        SysUser sysUser = new SysUser();
        return sysUserDao.select(sysUser);
    }
    public SysUser getDetailByUserNo(String userNo) {
        SysUser sysUser = new SysUser();
        sysUser.setUserNo(userNo);
        return sysUserDao.selectOne(sysUser);
    }
}
