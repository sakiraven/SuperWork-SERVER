package com.saki.work.system.module.vo;

import com.saki.work.system.module.dto.COSImageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (UserInfo)根据主键获取对象接口对外VO
 *
 * @author lzh
 * @since 2021-07-14 11:06:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoVO implements Serializable {
    private static final long serialVersionUID = -24626747111383117L;

    private Long id;

    @ApiModelProperty(value = "昵称", required = false)
    private String nick;

    @ApiModelProperty(value = "头像", required = false)
    private COSImageDTO faceCOSImageDTO;

    @ApiModelProperty(value = "用户是否存在", required = false)
    private Boolean isExist;

    public UserInfoVO(Boolean isExist) {
        this.id = 0L;
        this.nick = "此用户不存在";
        this.faceCOSImageDTO = new COSImageDTO();
        this.isExist = false;
    }

    public static UserInfoVO emptyUserInfoVO(){
        return UserInfoVO.builder()
                .id(0L)
                .nick("")
                .faceCOSImageDTO(COSImageDTO.emptyDTO())
                .isExist(false)
                .build();
    }
}