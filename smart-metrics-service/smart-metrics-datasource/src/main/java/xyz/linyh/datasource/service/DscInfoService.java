package xyz.linyh.datasource.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.linyh.datasource.model.dto.DscAddOrUpdateDto;
import xyz.linyh.datasource.model.dto.DscQueryDto;
import xyz.linyh.datasource.model.entity.DscInfo;
import xyz.linyh.datasource.model.vo.DscInfoVO;

/**
 * @author linzz
 */
public interface DscInfoService extends IService<DscInfo> {

    /**
     * 添加数据源
     * @param dto
     * @return
     */
    Boolean addDsc(DscAddOrUpdateDto dto);

    Page<DscInfoVO> pageDscInfo(DscQueryDto dto);

    DscInfoVO getOneDscInfo(Long dscId);

    Boolean updateDscInfo(DscAddOrUpdateDto dto);
}
