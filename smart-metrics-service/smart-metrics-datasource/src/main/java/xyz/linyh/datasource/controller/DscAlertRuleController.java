package xyz.linyh.datasource.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import xyz.linyh.common.response.BaseResponse;
import xyz.linyh.common.response.ResultUtils;
import xyz.linyh.datasource.model.dto.AlertRuleAddOrUpdateDto;
import xyz.linyh.datasource.model.dto.AlertRulePageDto;
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
        return null;
    }

    @PostMapping("/page")
    public BaseResponse<List<Object>> page(@RequestBody AlertRulePageDto dto) {
        return null;
    }

    @GetMapping("/{alertId}")
    public BaseResponse<Object> get(@PathVariable Long alertId) {
        return null;
    }

}
