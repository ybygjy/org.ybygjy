package org.ybygjy.spring.orderservice.dao;

import java.util.List;

/**
 * 定义DAO基础操作
 * @author WangYanCheng
 * @version 2016年10月2日
 */
public interface BaseDao<T> {
    /**
     * 增
     * @param obj
     * @return
     */
    public int insert(T obj);
    /**
     * 改
     * @param obj
     * @return
     */
    public int update(T obj);
    /**
     * ID查
     * @param id
     * @return
     */
    public T findById(Object id);
    /**
     * 删除
     * @param obj
     * @return
     */
    public int delete(T obj);
    /**
     * 查询
     * @param obj
     * @return
     */
    public List<T> select(T obj);
    /**
     * 查询
     * @param obj
     * @return
     */
    public T selectOne(T obj);
}
