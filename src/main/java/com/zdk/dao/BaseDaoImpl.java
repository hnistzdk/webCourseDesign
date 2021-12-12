package com.zdk.dao;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.zdk.config.HikariPoolManager;
import com.zdk.utils.MyPage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zdk
 * @date 2021/12/12 15:55
 */
public class BaseDaoImpl implements BaseDao{
    /**
     * 查询List
     * @param t
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> queryRows(Class<T> t, String sql, Object[] params) {
        List<T> list = new ArrayList<>();
        Connection connection = HikariPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int fileNum = resultSetMetaData.getColumnCount();
            JSONObject object;
            while (resultSet.next()) {
                object = new JSONObject();
                for (int i = 1; i <= fileNum; i++) {
                    String columnName = resultSetMetaData.getColumnLabel(i);
                    object.put(columnName, resultSet.getObject(columnName));
                }
                list.add(BeanUtil.toBean(object, t));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(connection, preparedStatement, resultSet);
        }
        return list;
    }


    /**
     * 查询单条数据
     * @param t
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    @Override
    public <T> T queryRow(Class<T> t, String sql, Object[] params) {
        List<T> list = queryRows(t, sql, params);
        return list.size() > 0 ? list.get(0) : null;
    }

    /**
     * 关闭连接
     * @param connection
     * @param preparedStatement
     * @param resultSet
     * @return
     */
    public static boolean closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        boolean flag = true;
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        return flag;
    }

    /**
     * 获取分页数据
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
    @Override
    public <T> MyPage<T> getPageInfo(Class<T> t, String selectSql, String countSql, Object[] countParams, Object[] selectParams, Integer pageNumber, Integer pageSize) {
        List<T> dataList = queryRows(t, selectSql, selectParams);
        Integer count = getCount(countSql, countParams);
        return new MyPage<>(dataList, count, pageSize, pageNumber);
    }

    /**
     * 获取数据条数
     * @param countSql
     * @param params
     * @return
     */
    @Override
    public Integer getCount(String countSql, Object[] params) {
        Connection connection = HikariPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(countSql);
            if (params != null && params.length > 0 && countSql.contains("?")) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public Integer insert(String insertSql,Object[] params) {
        Connection connection = HikariPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        int update = 0;
        try {
            preparedStatement = connection.prepareStatement(insertSql);
            if (params != null && params.length > 0 && insertSql.contains("?")) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            update = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(connection, preparedStatement, null);
        }
        return update;
    }
}
