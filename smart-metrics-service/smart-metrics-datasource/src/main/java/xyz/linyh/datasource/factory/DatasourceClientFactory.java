package xyz.linyh.datasource.factory;


import xyz.linyh.common.enums.DscTypeEnum;
import xyz.linyh.common.enums.ErrorCodeEnum;
import xyz.linyh.common.exception.BusinessException;
import xyz.linyh.datasource.client.DatasourceClient;
import xyz.linyh.datasource.client.MysqlDatasourceClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linzz
 */
public class DatasourceClientFactory {



    public static DatasourceClient getClient(Integer dscTypeCode, String url, String username, String password) {

        DatasourceClient client = null;
        DscTypeEnum dscType = DscTypeEnum.getDscTypeEnum(dscTypeCode);
        switch (dscType) {
            case MYSQL57:
                client = new MysqlDatasourceClient(url, username, password);
                break;
            case MYSQL80:
//                TODO: 8.0请求url后面需要拼接时区
                url = url;
                client = new MysqlDatasourceClient(url, username, password);
                break;
            default:
                throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "没有定义的数据类型");
        }


        return client;
    }
}
