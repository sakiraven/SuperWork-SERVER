package com.saki.work.system.module.dto;

import com.saki.work.system.module.dto.base.RolePermissionGroupBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (RolePermissionGroup)业务DTO
 *
 * @author lzh
 * @since 2021-11-29 17:35:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermissionGroupDTO extends RolePermissionGroupBaseDTO implements Serializable {
    private static final long serialVersionUID = -39982006029642793L;
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("权限id")
    private Long permissionId;


}