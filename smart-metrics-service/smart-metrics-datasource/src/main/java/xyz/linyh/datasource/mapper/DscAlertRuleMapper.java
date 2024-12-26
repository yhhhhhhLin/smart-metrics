package xyz.linyh.datasource.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.linyh.datasource.model.entity.DscAlertRule;
import xyz.linyh.datasource.model.vo.DscAlertRuleVO;

/**
 * @author linzz
 * @description 针对表【dsc_alert_rule(数据源告警规则表)】的数据库操作Mapper
 * @createDate 2024-12-08 21:52:27
 * @Entity xyz.linyh.datasource.model.entity.DscAlertRule
 */
@Mapper
public interface DscAlertRuleMapper extends BaseMapper<DscAlertRule> {

    IPage<DscAlertRuleVO> pageDscAlertRule(@Param("page") Page<DscAlertRule> page, @Param("ew") LambdaQueryWrapper<DscAlertRule> wrapper);
}




