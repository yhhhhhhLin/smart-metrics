package xyz.linyh.datasource.client;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import xyz.linyh.common.enums.ErrorCodeEnum;
import xyz.linyh.common.exception.BusinessException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author linzz
 */
@Slf4j
@Data
public class MysqlDatasourceClient implements DatasourceClient {

    /**
     * 连接地址(需要包括数据库)
     */
    private String url;

    private String databaseName;

    /**
     * 连接用户名
     */
    private String username;

    /**
     * 连接密码
     */
    private String password;

    public MysqlDatasourceClient(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
//        根据url解析出数据库名称
        this.databaseName = getDatabaseName(url);
    }

    public static String getDatabaseName(String url) {
        String regex = "jdbc:mysql://[^/]+/([^?]*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    @Override
    public Boolean testConnection() {
        Connection connection = getConnection();
        if (connection == null) {
            return false;
        }
        try {
            return connection.isValid(1);
        } catch (SQLException e) {
            log.error("数据库测试连接错误: {}", e.getMessage());
            return false;
        } finally {
            // 确保连接在使用后被关闭
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("关闭连接时出现错误: {}", e.getMessage());
            }
        }
    }

private Connection getConnection() {
    try {
        return DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
        log.error("连接数据源失败: {}", e.getMessage());
        throw new BusinessException(ErrorCodeEnum.DATASOURCE_ERROR, "连接数据源错误"+e.getMessage());
    }
}
}
