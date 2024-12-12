package xyz.linyh.datasource.model.dto;

import lombok.Data;

/**
 * @author linzz
 */
@Data
public class DscQueryDto{

    private Long pageSize;

    private Long currentPage;

    private String dscName;

    private Integer dscTypeCode;

    private String dscDesc;

    private Integer dscStatus;
}
