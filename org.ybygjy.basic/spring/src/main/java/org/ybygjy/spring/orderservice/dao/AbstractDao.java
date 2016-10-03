package org.ybygjy.spring.orderservice.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Template Pattern
 * @author WangYanCheng
 * @version 2016年10月2日@param <T>
 */
public abstract class AbstractDao<T> implements BaseDao<T> {
    @Autowired
    private SqlSessionTemplate sqlSession;
    private Class<T> clazz;
    public AbstractDao() {
        this.clazz = this.getGenericTypeClass(getClass());
    }
    protected Class<T> getGenericTypeClass(Class<? extends AbstractDao> clazz) {
        Type type = clazz.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        for(Type tmpType : types) {
            System.out.println(tmpType);
        }
        return (Class<T>) types[0];
    }
    @Override
    public int insert(T obj) {
        return this.sqlSession.insert(this.clazz.getSimpleName() + "Mapper.insert", obj);
    }
    @Override
    public int update(T obj) {
        return this.sqlSession.update(this.clazz.getSimpleName() + "Mapper.update", obj);
    }
    @Override
    public T findById(Object id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        return this.sqlSession.selectOne(this.clazz.getSimpleName() + "Mapper.select", paramMap);
    }
    @Override
    public List<T> select(T obj) {
        return this.sqlSession.selectList(this.clazz.getSimpleName() + "Mapper.select", obj);
    }
    @Override
    public int delete(Object id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        return this.sqlSession.delete(this.clazz.getSimpleName() + "Mapper.delete", paramMap);
    }
}
