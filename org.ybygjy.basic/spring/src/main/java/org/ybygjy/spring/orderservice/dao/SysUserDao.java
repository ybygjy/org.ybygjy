package org.ybygjy.spring.orderservice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.ybygjy.spring.orderservice.entity.SysUser;

/**
 * 系统用户DAO
 * @author WangYanCheng
 * @version 2016年10月2日
 */
@Repository
public class SysUserDao implements BaseDao<SysUser> {
    @Autowired
    private SqlSessionTemplate sqlSession;
    private Class<SysUser> clazz;
    public SysUserDao() {
        this.clazz = SysUser.class;
    }
    
    @Override
    public int saveOrUpdate(SysUser t) {
        if (t.getId() > 0) {
            return this.sqlSession.insert(this.clazz.getSimpleName() + "Mapper.insert", t);
        } else {
            return this.sqlSession.update(this.clazz.getSimpleName() + "Mapper.update", t);
        }
    }
    @Override
    public SysUser findById(SysUser sysUser) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", sysUser.getId());
        return this.sqlSession.selectOne(this.clazz.getSimpleName() + "Mapper.select", paramMap);
    }
    @Override
    public List<SysUser> select(SysUser obj) {
        return this.sqlSession.selectList(this.clazz.getSimpleName() + "Mapper.select", obj);
    }
    @Override
    public int delete(SysUser sysUser) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        long id = sysUser.getId();
        if (id != 0) {
            paramMap.put("id", id);
        }
        String userNo = sysUser.getUserNo();
        if (null != userNo) {
            paramMap.put("user_no", userNo);
        }
        return this.sqlSession.delete(this.clazz.getSimpleName() + "Mapper.delete", paramMap);
    }
    @Override
    public SysUser selectOne(SysUser sysUser) {
        return this.sqlSession.selectOne(this.clazz.getSimpleName() + "Mapper.select", sysUser);
    }
}
