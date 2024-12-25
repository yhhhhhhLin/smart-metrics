package xyz.linyh.datasource.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.linyh.common.dto.IdAndStatusDto;
import xyz.linyh.datasource.model.dto.AlertRuleAddOrUpdateDto;
import xyz.linyh.datasource.model.dto.AlertRulePageDto;
import xyz.linyh.datasource.model.entity.DscAlertRule;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.linyh.datasource.model.vo.DscAlertRuleVO;

import java.util.List;

/**
* @author linzz
* @description 针对表【dsc_alert_rule(数据源告警规则表)】的数据库操作Service
* @createDate 2024-12-08 21:52:27
*/
public interface DscAlertRuleService extends IService<DscAlertRule> {

    /**
     * 添加告警数据源告警规则
     * @param dto
     * @return
     */
    Boolean addRule(AlertRuleAddOrUpdateDto dto);

    /**
     * page展示所有告警规则
     * @param dto
     * @return
     */
    Page<DscAlertRuleVO> pageAlertRule(AlertRulePageDto dto);

    /**
     * 按照alertRuleId获取单个的详细信息
     * @param alertId
     * @return
     */
    DscAlertRuleVO getOneById(Long alertId);

    /**
     * 获取所有状态为开启
     * @return
     */
    List<DscAlertRule> listAllEnableAlertRule();

    Boolean updateStatus(IdAndStatusDto idAndStatusDto);
}
