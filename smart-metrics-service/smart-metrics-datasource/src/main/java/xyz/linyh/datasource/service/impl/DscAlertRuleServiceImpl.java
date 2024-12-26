package xyz.linyh.datasource.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.linyh.common.constant.CommonConstant;
import xyz.linyh.common.context.UserIdContext;
import xyz.linyh.common.dto.IdAndStatusDto;
import xyz.linyh.common.enums.ErrorCodeEnum;
import xyz.linyh.common.exception.BusinessException;
import xyz.linyh.datasource.mapper.DscAlertRuleMapper;
import xyz.linyh.datasource.model.dto.AlertRuleAddOrUpdateDto;
import xyz.linyh.datasource.model.dto.AlertRulePageDto;
import xyz.linyh.datasource.model.entity.DscAlertRule;
import xyz.linyh.datasource.model.entity.DscInfo;
import xyz.linyh.datasource.model.vo.DscAlertRuleVO;
import xyz.linyh.datasource.service.DscAlertRuleService;
import xyz.linyh.datasource.service.DscInfoService;
import xyz.linyh.datasource.utils.PageResultUtil;
import xyz.linyh.dubbo.service.DubboUserService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author linzz
 * @description 针对表【dsc_alert_rule(数据源告警规则表)】的数据库操作Service实现
 * @createDate 2024-12-08 21:52:27
 */
@Service
public class DscAlertRuleServiceImpl extends ServiceImpl<DscAlertRuleMapper, DscAlertRule>
        implements DscAlertRuleService {

    @Resource
    private DscInfoService dscInfoService;

    @Resource
    private DscAlertRuleMapper dscAlertRuleMapper;

    @DubboReference
    private DubboUserService dubboUserService;

    @Override
    public Boolean addRule(AlertRuleAddOrUpdateDto dto) {
        Long dscId = dto.getDscId();

        if (dscId == null || dscId == 0) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "数据源id不能为空");
        }
        Long alertRuleCreateUserId = UserIdContext.getUserId();

        DscInfo dscInfo = dscInfoService.getById(dscId);
        if (dscInfo == null) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "数据源为空");
        }
        Long dscCreateUserId = dscInfo.getCreatedUserId();

        DscAlertRule dscAlertRule = new DscAlertRule();
        switch (dto.getNotifyRecipientsType()) {
            case 1:
//                告警接受为告警创建人
                dscAlertRule.setNotifyRecipientsType(1);
                dscAlertRule.setNotifyRecipients(alertRuleCreateUserId);
                break;
            case 2:
//                告警接受为数据源创建人
                dscAlertRule.setNotifyRecipientsType(2);
                dscAlertRule.setNotifyRecipients(dscCreateUserId);
                break;
            case 3:
//                告警接受为其他
                dscAlertRule.setNotifyRecipientsType(3);
                dscAlertRule.setNotifyRecipients(dto.getNotifyRecipients());
                break;
            default:
                throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "当前告警接受方式无效");
        }
        dscAlertRule.setDscId(dto.getDscId());
        dscAlertRule.setRuleName(dto.getRuleName());
        dscAlertRule.setRuleType(dto.getRuleType());
        dscAlertRule.setNotifyChannel(dto.getNotifyChannel());
        dscAlertRule.setCreatedUserId(alertRuleCreateUserId);
        dscAlertRule.setUpdatedUserId(alertRuleCreateUserId);

        return this.save(dscAlertRule);
    }

    @Override
    public Page<DscAlertRuleVO> pageAlertRule(AlertRulePageDto dto) {
        LambdaQueryWrapper<DscAlertRule> wrapper = new LambdaQueryWrapper<DscAlertRule>()
                .like(StrUtil.isNotBlank(dto.getAlertRuleName()), DscAlertRule::getRuleName, dto.getAlertRuleName());
        Page<DscAlertRule> page = new Page<>(dto.getCurrentPage(), dto.getPageSize());
        IPage<DscAlertRuleVO> dscAlertRuleVoPage = dscAlertRuleMapper.pageDscAlertRule(page, wrapper);

        return PageResultUtil.transfer(dscAlertRuleVoPage, dscAlertRule -> {


            DscAlertRuleVO dscAlertRuleVO = new DscAlertRuleVO();
            BeanUtils.copyProperties(dscAlertRule, dscAlertRuleVO);

            Long createdUserId = dscAlertRule.getCreatedUserId();
            Long updatedByUserId = dscAlertRule.getUpdatedUserId();
            Long notifyRecipients = dscAlertRule.getNotifyRecipients();
            Map<Long, String> userIdNameMap = dubboUserService.getUserNameByIds(List.of(createdUserId, updatedByUserId, notifyRecipients));
            dscAlertRuleVO.setCreatedUserName(userIdNameMap.get(createdUserId));
            dscAlertRuleVO.setUpdatedUserName(userIdNameMap.get(updatedByUserId));
            dscAlertRuleVO.setNotifyRecipientName(userIdNameMap.get(notifyRecipients));
            return dscAlertRuleVO;
        });


    }

    @Override
    public DscAlertRuleVO getOneById(Long alertId) {
        DscAlertRule dscAlertRule = getById(alertId);

        DscAlertRuleVO dscAlertRuleVO = new DscAlertRuleVO();
        BeanUtils.copyProperties(dscAlertRule, dscAlertRuleVO);

//        TODO:
        Long createdUserId = dscAlertRule.getCreatedUserId();
        Long updatedUserId = dscAlertRule.getUpdatedUserId();
//        dscAlertRuleVO.setCreatedUserName(null);
//        dscAlertRuleVO.setUpdatedUserName(null);

        return dscAlertRuleVO;
    }

    @Override
    public List<DscAlertRule> listAllEnableAlertRule() {
        return lambdaQuery().eq(DscAlertRule::getIsEnabled, CommonConstant.STATUS_ENABLED)
                .list();
    }

    @Override
    public Boolean updateStatus(IdAndStatusDto idAndStatusDto) {
        Long id = idAndStatusDto.getId();
        Integer status = idAndStatusDto.getStatus();
        if (id == null || status == null || status != 0 && status != 1) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
        }

        return lambdaUpdate()
                .eq(DscAlertRule::getId, id)
                .set(DscAlertRule::getIsEnabled, status)
                .update();
    }

    @Override
    public void addAlertTime(Long id) {
        lambdaUpdate().eq(DscAlertRule::getId, id)
                .set(DscAlertRule::getAlertTime, new Date())
                .update();

    }
}




