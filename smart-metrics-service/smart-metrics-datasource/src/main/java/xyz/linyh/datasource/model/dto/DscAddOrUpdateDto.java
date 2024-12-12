package xyz.linyh.datasource.model.dto;

import lombok.Data;

/**
 * @author linzz
 */
@Data
public class DscAddOrUpdateDto {

    private Long dscId;

    /**
     * 数据源类型对应code --> dsyTypeEnum
     */
    private Integer datasourceTypeCode;

    /**
     * 数据源名称
     */
    private String datasourceName;

    /**
     * 数据源描述
     */
    private String datasourceDesc;

    private String url;

    private String username;

    private String password;
}
