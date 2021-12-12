package com.zdk.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author zdk
 * @date 2021/12/12 16:10
 * Hikari连接池
 */
public class HikariPoolManager {
    private static HikariDataSource dataSource;
    public static Properties prop;
    static {
        try {
            prop = new Properties();
            prop.load(HikariPoolManager.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //初始化HikariConfig配置
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(prop.getProperty("jdbcUrl"));
        config.setUsername(prop.getProperty("username"));
        config.setPassword(prop.getProperty("password"));
        config.addDataSourceProperty("cachePrepStmts", prop.getProperty("dataSource.cachePrepStmts"));
        config.addDataSourceProperty("prepStmtCacheSize", prop.getProperty("dataSource.prepStmtCacheSize"));
        config.addDataSourceProperty("prepStmtCacheSqlLimit", prop.getProperty("dataSource.prepStmtCacheSqlLimit"));
        config.addDataSourceProperty("useServerPrepStmts", prop.getProperty("dataSource.useServerPrepStmts"));
        config.addDataSourceProperty("useLocalSessionState", prop.getProperty("dataSource.useLocalSessionState"));
        config.addDataSourceProperty("rewriteBatchedStatements", prop.getProperty("dataSource.rewriteBatchedStatements"));
        config.addDataSourceProperty("cacheResultSetMetadata", prop.getProperty("dataSource.cacheResultSetMetadata"));
        config.addDataSourceProperty("cacheServerConfiguration", prop.getProperty("dataSource.cacheServerConfiguration"));
        config.addDataSourceProperty("elideSetAutoCommits", prop.getProperty("dataSource.elideSetAutoCommits"));
        config.addDataSourceProperty("maintainTimeStats", prop.getProperty("dataSource.maintainTimeStats"));
        //初始化HikariDataSource
        dataSource = new HikariDataSource(config);
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
