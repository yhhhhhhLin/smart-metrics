package xyz.linyh.datasource.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import xyz.linyh.common.response.BaseResponse;
import xyz.linyh.datasource.model.dto.AlertLogQueryDto;
import xyz.linyh.datasource.model.dto.AlertLogStatusUpdateDto;
import xyz.linyh.datasource.service.DscAlertRuleService;

import java.util.List;

/**
 * @author linzz
 */
@RestController
@RequestMapping("/alertLog")
public class DscAlertLogController {

    @Resource
    private DscAlertRuleService dscAlertRuleService;

    @PostMapping("/page")
    public BaseResponse<List<Object>> page(@RequestBody AlertLogQueryDto dto) {
        return null;
    }

    @GetMapping("/{alertLogId}")
    public BaseResponse<Object> getOne(@PathVariable Long alertLogId) {
        return null;

    }

    @PostMapping("/status")
    public BaseResponse<Boolean> updateStatus(@RequestBody AlertLogStatusUpdateDto dto) {
        return null;
    }

}
