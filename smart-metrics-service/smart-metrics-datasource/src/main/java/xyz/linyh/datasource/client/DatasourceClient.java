package xyz.linyh.datasource.client;

/**
 * @author linzz
 */
public interface DatasourceClient {

    /**
     * 测试数据源连通性
     * @return
     */
    Boolean testConnection();

}
