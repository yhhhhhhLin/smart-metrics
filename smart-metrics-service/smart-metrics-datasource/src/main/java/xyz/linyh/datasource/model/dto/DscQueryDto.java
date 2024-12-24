package xyz.linyh.datasource.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.linyh.common.dto.PageCommonDto;

/**
 * @author linzz
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DscQueryDto extends PageCommonDto {

    private String dscName;

    private Integer dscTypeCode;

    private String dscDesc;

    private Integer dscStatus;
}
