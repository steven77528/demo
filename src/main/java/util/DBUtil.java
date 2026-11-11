package util;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


/**
 * 数据库工具类
 *
 * @author 刘彦民 2015年9月19日 下午11:08:28
 */
public class DBUtil {
    //数据库连接池
    private static BasicDataSource ds;

    /**
     * 初始化数据库配置
     */
    static {
        Properties config = new Properties();
        String path = "application.properties";
        try {
            config.load(DBUtil.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver = config.getProperty("c3po.driverClass");
        String url = config.getProperty("c3po.jdbcUrl");
        String username = config.getProperty("c3po.user");
        String password = config.getProperty("c3po.password");
//        int maxActive = Integer.parseInt(config.getProperty("oracle.maxActive"));
//        int maxWait = Integer.parseInt(config.getProperty("oracle.maxWait"));
        ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
//        ds.setMaxActive(maxActive);//数据库的最大连接数
//        ds.setMaxWait(maxWait);//数据库的延时等待时间
    }

    /**
     * 连接数据库
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        try {
            return ds.getConnection();
        } catch (Exception e) {
            System.out.println("数据库连接失败！");
            throw e;
        }
    }

    /**
     * 关闭数据库
     *
     * @param conn
     * @param stat
     * @param rs
     * @throws Exception
     */
    public static void close(Connection conn, Statement stat, ResultSet rs) throws Exception {
        try {
            if (conn != null)
                conn.close();
            if (stat != null)
                stat.close();
            if (rs != null)
                rs.close();
        } catch (Exception e) {
            System.out.println("数据库关闭失败！");
            throw e;
        }
    }
}