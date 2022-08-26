package com.saki.work.system.module.dto;

import com.saki.work.system.module.dto.base.DicBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Dic)业务DTO
 *
 * @author lzh
 * @since 2021-11-29 17:35:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DicDTO extends DicBaseDTO implements Serializable {
    private static final long serialVersionUID = -39268799437287223L;
    @ApiModelProperty("主键")
    private String key;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("描述")
    private String describe;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;


}