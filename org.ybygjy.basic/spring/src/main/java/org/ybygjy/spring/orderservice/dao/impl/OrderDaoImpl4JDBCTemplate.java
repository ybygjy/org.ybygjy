package org.ybygjy.spring.orderservice.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.ybygjy.spring.orderservice.dao.BaseDao;
import org.ybygjy.spring.orderservice.entity.Order;

@Repository
public class OrderDaoImpl4JDBCTemplate implements BaseDao<Order> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Order findById(Order obj) {
        String sqlTmpl = "SELECT * FROM t_order WHERE id=?";
        return this.jdbcTemplate.query(sqlTmpl, new Object[]{obj.getId()}, new ResultSetExtractor<Order>(){
            @Override
            public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (!rs.next()) {
                    return null;
                }
                return entityMapping4ResultSet(rs);
            }
        });
    }

    @Override
    public int delete(Order obj) {
        String sqlTmpl = "DELETE FROM t_order WHERE id=?";
        return this.jdbcTemplate.update(sqlTmpl, obj.getId());
    }

    @Override
    public List<Order> select(Order obj) {
        String sqlTmpl = "SELECT * FROM t_order";
        List<Order> rtnList = this.jdbcTemplate.query(sqlTmpl, new RowMapper<Order>(){
            public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
                return OrderDaoImpl4JDBCTemplate.this.entityMapping4ResultSet(rs);
            }
        });
        return rtnList;
    }
    @Override
    public Order selectOne(Order obj) {
        String sqlTmpl = "SELECT * FROM t_order where order_no=?";
        return this.jdbcTemplate.query(sqlTmpl, new Object[]{obj.getOrderNo()}, new ResultSetExtractor<Order>(){
            public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return OrderDaoImpl4JDBCTemplate.this.entityMapping4ResultSet(rs);
                }
                return null;
            }
        });
    }

    @Override
    public int saveOrUpdate(Order t) {
        int rtnFlag = -1;
        if (t.getId() > 0) {
            String sqlTmpl = "UPDATE t_order SET order_no=?,order_amount=?,order_flag=?,buyer_id=?,sales_id=?,send_time=?,order_remark=? where id=?";
            rtnFlag = this.jdbcTemplate.update(sqlTmpl, t.getOrderNo(), t.getOrderAmount(), t.getOrderFlag(), t.getBuyerId(), t.getSalesId(), t.getSendTime(), t.getOrderRemark(), t.getId());
        } else {
            String sqlTmpl = "INSERT INTO t_order(order_no,order_amount,order_flag,buyer_id,sales_id,send_time,order_remark) values(?,?,?,?,?,?,?)";
            rtnFlag = this.jdbcTemplate.update(sqlTmpl, t.getOrderNo(), t.getOrderAmount(), t.getOrderFlag(), t.getBuyerId(), t.getSalesId(), t.getSendTime(), t.getOrderRemark());
        }
        return rtnFlag;
    }
    /**
     * JDBC ResultSet 与 DTO实体的映射
     * @param rs {@link ResultSet}
     * @return rtnObj
     * @throws SQLException {@link SQLException}
     */
    private Order entityMapping4ResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setOrderNo(rs.getString("order_no"));
        order.setOrderAmount(rs.getDouble("order_amount"));
        order.setOrderFlag(rs.getInt("order_flag"));
        order.setBuyerId(rs.getLong("buyer_id"));
        order.setBuyerName(rs.getString("buyer_name"));
        order.setSalesName(rs.getString("sales_name"));
        order.setSalesId(rs.getLong("sales_id"));
        order.setOrderRemark(rs.getString("order_remark"));
        order.setSendTime(new Date(rs.getDate("send_time").getTime()));
        order.setOrderMtime(new Date(rs.getDate("order_mtime").getTime()));
        order.setOrderCtime(new Date(rs.getDate("order_ctime").getTime()));
        return order;
    }    
}
