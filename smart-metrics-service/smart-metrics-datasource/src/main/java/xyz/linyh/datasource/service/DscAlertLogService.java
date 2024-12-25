package xyz.linyh.datasource.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.linyh.common.dto.IdAndStatusDto;
import xyz.linyh.datasource.model.dto.AlertLogQueryDto;
import xyz.linyh.datasource.model.entity.DscAlertLog;
import xyz.linyh.datasource.model.entity.DscAlertRule;
import xyz.linyh.datasource.model.entity.DscInfo;
import xyz.linyh.datasource.model.vo.DscAlertLogVO;

/**
 * @author linzz
 * @description 针对表【data_source_alert_log(数据源告警日志表)】的数据库操作Service
 * @createDate 2024-12-08 22:09:08
 */
public interface DscAlertLogService extends IService<DscAlertLog> {

    /**
     * 添加告警记录
     * @param alertRule
     * @param dscInfo
     * @param message
     */
    void addAlertLog(DscAlertRule alertRule, DscInfo dscInfo, String message);

    /**
     * 分页获取告警记录
     * @param dto
     * @return
     */
    Page<DscAlertLogVO> pageDscAlertLog(AlertLogQueryDto dto);

    /**
     * 更改告警记录状态
     * @param dto
     * @return
     */
    Boolean updateStatus(IdAndStatusDto dto);
}
