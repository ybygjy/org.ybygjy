package org.ybygjy.spring.orderservice.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.ybygjy.spring.orderservice.dao.BaseDao;
import org.ybygjy.spring.orderservice.entity.SysUser;

/**
 * SYSUSER DAO JDBCTEMPLATE IMPLEMENTS
 * @author WangYanCheng
 * @version 2016年10月2日
 */
@Repository
public class SysUserDaoImpl4JDBCTemplate implements BaseDao<SysUser> {
    @Autowired
    private JdbcOperations jdbcTemplate;
    private static final String INSERT_SYSUSER = "INSERT T_SYS_USER(USER_NO,USER_NAME,USER_ROLE,PASSWORD,STATE_FLAG) VALUES(?,?,?,?,?)";
    private static final String UPDATE_SYSUSER = "UPDATE T_SYS_USER WHERE SET USER_NO=?,USER_NAME=?,USER_ROLE=?,PASSWORD=?,STATE_FLAG=? WHERE ID=?";
    private static final String DELETE_SYSUSER = "DELETE T_SYS_USER WHERE ID=?";
    private static final String SELECT_SYSUSER = "SELECT * FROM T_SYS_USER a WHERE 1=1 ";

    @Override
    public int saveOrUpdate(SysUser obj) {
        int flag = -1;
        if (obj.getId() > 0) {
            flag = this.jdbcTemplate.update(INSERT_SYSUSER, obj.getUserNo(), obj.getUserName(), obj.getUserRole(), obj.getPassword(), obj.getStateFlag());
        } else {
            flag = this.jdbcTemplate.update(UPDATE_SYSUSER, obj.getUserNo(), obj.getUserName(), obj.getUserRole(), obj.getPassword(), obj.getStateFlag(), obj.getId());
        }
        return flag;
    }

    @Override
    public SysUser findById(Object id) {
        SysUser sysUser = this.jdbcTemplate.queryForObject("select * from t_sys_user a where a.id=?", new RowMapper<SysUser>() {
            @Override
            public SysUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                SysUser sysUser = new SysUser();
                sysUser.setId(rs.getLong("id"));
                sysUser.setUserNo(rs.getString("user_no"));
                sysUser.setUserName(rs.getString("user_name"));
                sysUser.setUserRole(rs.getString("user_role"));
                sysUser.setModifyTime(new Date(rs.getDate("mtime").getTime()));
                sysUser.setCreateTime(new Date(rs.getDate("ctime").getTime()));
                return sysUser;
            }
        }, id);
        return sysUser;
    }

    @Override
    public int delete(SysUser obj) {
        return this.jdbcTemplate.update(DELETE_SYSUSER, obj.getId());
    }

    @Override
    public List<SysUser> select(SysUser obj) {
        StringBuilder sbud = new StringBuilder(SELECT_SYSUSER);
        List<String> parameterSegment = new ArrayList<String>();
        if (obj.getUserNo() != null) {
            sbud.append(" and a.user_no=?");
            parameterSegment.add(obj.getUserNo());
        }
        if (obj.getUserName() != null) {
            sbud.append(" and a.user_name like ?");
            parameterSegment.add(obj.getUserName() + "%");
        }
        if (obj.getUserRole() != null) {
            sbud.append(" and a.user_role = ?");
            parameterSegment.add(obj.getUserRole());
        }
        List<SysUser> rtnList = this.jdbcTemplate.query(sbud.toString(), new RowMapper<SysUser>() {
            @Override
            public SysUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                SysUser sysUser = new SysUser();
                sysUser.setId(rs.getLong("id"));
                sysUser.setUserNo(rs.getString("user_no"));
                sysUser.setUserName(rs.getString("user_name"));
                sysUser.setUserRole(rs.getString("user_role"));
                sysUser.setStateFlag(rs.getInt("state_flag"));
                sysUser.setModifyTime(new Date(rs.getDate("mtime").getTime()));
                sysUser.setCreateTime(new Date(rs.getDate("ctime").getTime()));
                return sysUser;
            }
            
        }, parameterSegment.toArray());
        return rtnList;
    }

    @Override
    public SysUser selectOne(SysUser obj) {
        List<SysUser> rtnList = this.select(obj);
        return null == rtnList ? null : rtnList.get(0);
    }

}
