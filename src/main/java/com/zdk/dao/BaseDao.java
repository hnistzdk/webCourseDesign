package com.zdk.dao;

import com.zdk.utils.MyPage;

import java.util.List;

/**
 * @author zdk
 * @date 2021/12/12 15:50
 * dao层基本接口
 */
public interface BaseDao {
    /**
     * 查询list
     * @param t
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    <T> List<T> queryRows(Class<T> t, String sql, Object[] params);

    /**
     * 查询单条数据
     * @param t
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    <T> T queryRow(Class<T> t,String sql,Object[] params);

    /**
     * 分页
     * @param t
     * @param selectSql
     * @param countSql
     * @param countParams
     * @param selectParams
     * @param pageNumber
     * @param pageSize
     * @param <T>
     * @return
     */
    <T> MyPage<T> getPageInfo(Class<T> t, String selectSql, String countSql, Object[] countParams, Object[] selectParams, Integer pageNumber, Integer pageSize);

    /**
     * 总条数查询
     * @param countSql
     * @param params
     * @return
     */
    Integer getCount(String countSql,Object[] params);

    /**
     * 插入数据
     * @param insertSql
     * @param params
     * @return
     */
    Integer insert(String insertSql,Object[] params);
}
