package xyz.linyh.datasource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.linyh.common.dto.IdAndStatusDto;
import xyz.linyh.common.enums.AlertStatusEnum;
import xyz.linyh.common.enums.ErrorCodeEnum;
import xyz.linyh.common.exception.BusinessException;
import xyz.linyh.datasource.mapper.DscAlertLogMapper;
import xyz.linyh.datasource.model.dto.AlertLogQueryDto;
import xyz.linyh.datasource.model.entity.DscAlertLog;
import xyz.linyh.datasource.model.entity.DscAlertRule;
import xyz.linyh.datasource.model.entity.DscInfo;
import xyz.linyh.datasource.model.vo.DscAlertLogVO;
import xyz.linyh.datasource.service.DscAlertLogService;
import xyz.linyh.datasource.utils.PageResultUtil;

import java.util.Date;

/**
 * @author linzz
 * @description 针对表【data_source_alert_log(数据源告警日志表)】的数据库操作Service实现
 * @createDate 2024-12-08 22:09:08
 */
@Service
public class DscAlertLogServiceImpl extends ServiceImpl<DscAlertLogMapper, DscAlertLog>
        implements DscAlertLogService {

    @Override
    public void addAlertLog(DscAlertRule alertRule, DscInfo dscInfo, String message) {
        DscAlertLog dscAlertLog = new DscAlertLog();
        dscAlertLog.setAlertRuleId(alertRule.getId());
        dscAlertLog.setDscId(alertRule.getDscId());
        dscAlertLog.setAlertTime(new Date());
        dscAlertLog.setAlertStatus(AlertStatusEnum.NOTIFICATION_SENT.getCode());
        dscAlertLog.setErrorMessage(message);
        dscAlertLog.setNotifyChannel(alertRule.getNotifyChannel());
        dscAlertLog.setNotifyRecipients(alertRule.getNotifyRecipients());
        dscAlertLog.setResolvedAt(null);
        dscAlertLog.setResolvedUserId(null);

        this.save(dscAlertLog);
    }

    @Override
    public Page<DscAlertLogVO> pageDscAlertLog(AlertLogQueryDto dto) {
        Long dscAlertLogId = dto.getDscAlertLogId();
        Long dscAlertRuleId = dto.getDscAlertRuleId();
        Long dscId = dto.getDscId();
        Long recipientId = dto.getRecipientId();

        LambdaQueryWrapper<DscAlertLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(dscAlertLogId != null, DscAlertLog::getId, dscAlertLogId)
                .eq(dscAlertRuleId != null, DscAlertLog::getAlertRuleId, dscAlertRuleId)
                .eq(dscId != null, DscAlertLog::getDscId, dscId)
                .eq(recipientId != null, DscAlertLog::getNotifyRecipients, recipientId);
        Page<DscAlertLog> page = new Page<>(dto.getCurrentPage(), dto.getPageSize());
        IPage<DscAlertLogVO> dscAlertLogPage = baseMapper.pageDscAlertLog(page, wrapper);

        return PageResultUtil.transfer(dscAlertLogPage, dscAlertLog -> {
            DscAlertLogVO dscAlertLogVO = new DscAlertLogVO();
            BeanUtils.copyProperties(dscAlertLog, dscAlertLogVO);
            Long notifyRecipientsId = dscAlertLog.getNotifyRecipients();
            Long resolvedUserId = dscAlertLog.getResolvedUserId();

//            TODO:
//            dscAlertLogVO.setNotifyRecipientsName();
//            dscAlertLogVO.setResolvedUserName();
            return dscAlertLogVO;
        });
    }

    @Override
    public Boolean updateStatus(IdAndStatusDto dto) {
        Long id = dto.getId();
        Integer status = dto.getStatus();
        if(id == null || status == null || (status != 1 && status != 2 && status != 3)){
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
        }

        return lambdaUpdate()
                .eq(DscAlertLog::getId,id)
                .set(DscAlertLog::getAlertStatus,status)
                .update();
    }
}




