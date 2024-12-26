package xyz.linyh.datasource.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import xyz.linyh.common.response.BaseResponse;
import xyz.linyh.common.response.ResultUtils;
import xyz.linyh.datasource.client.DatasourceClient;
import xyz.linyh.datasource.factory.DatasourceClientFactory;
import xyz.linyh.datasource.model.dto.DscAddOrUpdateDto;
import xyz.linyh.datasource.model.dto.DscQueryDto;
import xyz.linyh.datasource.model.entity.DscInfo;
import xyz.linyh.datasource.model.vo.DscInfoVO;
import xyz.linyh.datasource.service.DscInfoService;

import java.util.List;

/**
 * @author linzz
 */
@RestController
@RequestMapping("/dsc")
public class DscController {

    @Resource
    private DscInfoService dscInfoService;

    @PostMapping("/add")
    public BaseResponse<Boolean> add(@RequestBody DscAddOrUpdateDto dto) {
        Boolean result = dscInfoService.addDsc(dto);
        return ResultUtils.success(result);
    }

    @PostMapping("/list")
    public BaseResponse<List<DscInfoVO>> list(@RequestBody DscQueryDto dto) {
        List<DscInfoVO> result = dscInfoService.listDscInfo(dto);
        return ResultUtils.success(result);
    }

    @PostMapping("/page")
    public BaseResponse<Page<DscInfoVO>> page(@RequestBody DscQueryDto dto) {
        Page<DscInfoVO> result = dscInfoService.pageDscInfo(dto);
        return ResultUtils.success(result);
    }

    @GetMapping("/{dscId}")
    public BaseResponse<DscInfoVO> get(@PathVariable Long dscId) {
        DscInfoVO result = dscInfoService.getOneDscInfo(dscId);
        return ResultUtils.success(result);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody DscAddOrUpdateDto dto) {
        Boolean result = dscInfoService.updateDscInfo(dto);
        return ResultUtils.success(result);
    }

    @GetMapping("/del/{dscId}")
    public BaseResponse<Boolean> del(@PathVariable Long dscId) {
//        TODO: 删除需要保证没有指标使用这个数据源才可以删除？
        return null;
    }

    @PostMapping("/test")
    public BaseResponse<Boolean> test(@RequestBody DscAddOrUpdateDto dto) {
        DatasourceClient client = DatasourceClientFactory.getClient(dto.getDatasourceTypeCode(), dto.getUrl(), dto.getUsername(), dto.getPassword());
        return ResultUtils.success(client.testConnection());
    }

}
