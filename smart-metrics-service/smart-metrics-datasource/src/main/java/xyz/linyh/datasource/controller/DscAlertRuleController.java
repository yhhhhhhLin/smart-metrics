package xyz.linyh.datasource.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import xyz.linyh.common.response.BaseResponse;
import xyz.linyh.common.response.ResultUtils;
import xyz.linyh.datasource.model.dto.AlertRuleAddOrUpdateDto;
import xyz.linyh.datasource.model.dto.AlertRulePageDto;
import xyz.linyh.datasource.model.entity.DscAlertRule;
import xyz.linyh.datasource.model.vo.DscAlertRuleVO;
import xyz.linyh.datasource.service.DscAlertRuleService;

import java.util.List;

/**
 * @author linzz
 */
@RestController
@RequestMapping("/alert")
public class DscAlertRuleController {

    @Resource
    private DscAlertRuleService dscAlertRuleService;

    @PostMapping("/add")
    public BaseResponse<Boolean> add(@RequestBody AlertRuleAddOrUpdateDto dto) {
        Boolean result = dscAlertRuleService.addRule(dto);
        return ResultUtils.success(result);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody AlertRuleAddOrUpdateDto dto) {
        return null;
    }

    @GetMapping("/del/{alertId}")
    public BaseResponse<Boolean> del(@PathVariable Long alertId) {
        boolean result = dscAlertRuleService.removeById(alertId);
        return ResultUtils.success(result);
    }

    @PostMapping("/page")
    public BaseResponse<Page<DscAlertRuleVO>> page(@RequestBody AlertRulePageDto dto) {
        Page<DscAlertRuleVO> result = dscAlertRuleService.pageAlertRule(dto);
        return ResultUtils.success(result);
    }

    @GetMapping("/{alertId}")
    public BaseResponse<DscAlertRuleVO> get(@PathVariable Long alertId) {
        DscAlertRuleVO dscAlertRuleVO = dscAlertRuleService.getOneById(alertId);
        return ResultUtils.success(dscAlertRuleVO);
    }

}
