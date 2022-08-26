package com.saki.work.system.module.dto;

import com.saki.work.system.module.dto.base.UserInfoRoleGroupBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (UserInfoRoleGroup)业务DTO
 *
 * @author lzh
 * @since 2021-11-29 17:35:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoRoleGroupDTO extends UserInfoRoleGroupBaseDTO implements Serializable {
    private static final long serialVersionUID = -93092271131022440L;
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userInfoId;

    @ApiModelProperty("角色id")
    private Long roleId;


}