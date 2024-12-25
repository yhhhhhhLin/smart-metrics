package xyz.linyh.datasource.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.linyh.datasource.model.entity.DscAlertLog;
import xyz.linyh.datasource.model.vo.DscAlertLogVO;

/**
 * @author linzz
 * @description 针对表【data_source_alert_log(数据源告警日志表)】的数据库操作Mapper
 * @createDate 2024-12-08 22:09:08
 * @Entity xyz.linyh.datasource.model.entity.DataSourceAlertLog
 */
@Mapper
public interface DscAlertLogMapper extends BaseMapper<DscAlertLog> {

    IPage<DscAlertLogVO> pageDscAlertLog(Page<DscAlertLog> page, @Param("ew") LambdaQueryWrapper<DscAlertLog> wrapper);
}




