package com.saki.work.system.module.dto;

import com.saki.work.system.module.dto.base.RoleBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Role)业务DTO
 *
 * @author lzh
 * @since 2021-11-29 17:35:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO extends RoleBaseDTO implements Serializable {
    private static final long serialVersionUID = 332342980542963968L;
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("备注")
    private String remark;


}