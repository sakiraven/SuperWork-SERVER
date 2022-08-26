package com.saki.work.system.module.dto;

import com.saki.work.system.module.dto.base.PermissionBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Permission)业务DTO
 *
 * @author lzh
 * @since 2021-11-29 17:35:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionDTO extends PermissionBaseDTO implements Serializable {
    private static final long serialVersionUID = -77786516723865483L;
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("权限")
    private String perms;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("排序 升序")
    private Integer sort;

    @ApiModelProperty("父id")
    private Long parentId;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("备注")
    private String remark;


}