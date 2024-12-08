package xyz.linyh.datasource.controller;

import org.springframework.web.bind.annotation.*;
import xyz.linyh.common.response.BaseResponse;
import xyz.linyh.datasource.model.dto.DscAddOrUpdateDto;
import xyz.linyh.datasource.model.dto.DscQueryDto;

import java.util.List;

/**
 * @author linzz
 */
@RestController("/dsc")
public class DscController {

    @PostMapping("/add")
    public BaseResponse<Boolean> add(@RequestBody DscAddOrUpdateDto dto) {
        return null;
    }

    @GetMapping("/page")
    public BaseResponse<List<Object>> page(DscQueryDto dto) {
        return null;
    }

    @GetMapping("/{dscId}")
    public BaseResponse<Object> get(@PathVariable Long dscId) {
        return null;
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody DscAddOrUpdateDto dto) {
        return null;
    }

    @GetMapping("/del/{dscId}")
    public BaseResponse<Boolean> del(@PathVariable Long dscId) {
        return null;
    }

    @PostMapping("/test")
    public BaseResponse<Boolean> test(DscAddOrUpdateDto dto) {
        return null;
    }

}
