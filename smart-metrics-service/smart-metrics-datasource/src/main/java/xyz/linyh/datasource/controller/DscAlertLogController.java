package xyz.linyh.datasource.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import xyz.linyh.common.dto.IdAndStatusDto;
import xyz.linyh.common.response.BaseResponse;
import xyz.linyh.common.response.ResultUtils;
import xyz.linyh.datasource.model.dto.AlertLogQueryDto;
import xyz.linyh.datasource.model.dto.AlertLogStatusUpdateDto;
import xyz.linyh.datasource.model.vo.DscAlertLogVO;
import xyz.linyh.datasource.service.DscAlertLogService;
import xyz.linyh.datasource.service.DscAlertRuleService;

/**
 * @author linzz
 */
@RestController
@RequestMapping("/alertLog")
public class DscAlertLogController {

    @Resource
    private DscAlertLogService dscAlertLogService;

    @PostMapping("/page")
    public BaseResponse<Page<DscAlertLogVO>> page(@RequestBody AlertLogQueryDto dto) {
        Page<DscAlertLogVO> result = dscAlertLogService.pageDscAlertLog(dto);
        return ResultUtils.success(result);
    }

//    @GetMapping("/{alertLogId}")
//    public BaseResponse<DscAlertLogVO> getOne(@PathVariable Long alertLogId) {
//        returndscAlertLogService.getOneAlertLog(alertLogId);
//        return null;
//    }

    @PostMapping("/status")
    public BaseResponse<Boolean> updateStatus(@RequestBody IdAndStatusDto dto) {
        Boolean result = dscAlertLogService.updateStatus(dto);
        return ResultUtils.success(result);
    }

}
