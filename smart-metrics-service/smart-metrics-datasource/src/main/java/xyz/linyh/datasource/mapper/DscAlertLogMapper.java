package xyz.linyh.datasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.linyh.datasource.model.entity.DscAlertLog;

/**
 * @author linzz
 * @description 针对表【data_source_alert_log(数据源告警日志表)】的数据库操作Mapper
 * @createDate 2024-12-08 22:09:08
 * @Entity xyz.linyh.datasource.model.entity.DataSourceAlertLog
 */
@Mapper
public interface DscAlertLogMapper extends BaseMapper<DscAlertLog> {

}




