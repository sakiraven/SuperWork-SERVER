package com.saki.work.system.module.dto.base;

import com.saki.work.base.BaseDTO;
import com.saki.work.system.module.dto.COSImageDTO;
import com.saki.work.system.module.vo.UserInfoVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * (UserInfo)业务扩展DTO
 *
 * @author lzh
 * @since 2021-07-15 10:02:58
 */
@Data
public class UserInfoBaseDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 365366053633765197L;

    @ApiModelProperty("每月打赏前几名用户")
    private List<UserInfoVO> rewardUserInfoVOList;

    @ApiModelProperty("身份认证")
    private List<String> authList;

    @ApiModelProperty("头像图片对象")
    private COSImageDTO faceCOSImageDTO;

    @ApiModelProperty("用户信息")
    private UserInfoVO userInfoVO;

    @ApiModelProperty("集邮总数")
    private Integer photoStampNum;

    @NotBlank
    @ApiModelProperty(value = "登录方式", required = true)
    private String loginType;

    @ApiModelProperty(value = "手机验证码", required = false)
    private String phoneCode;

    @ApiModelProperty(value = "被点赞数", required = false)
    private Integer likeNum;

    @ApiModelProperty(value = "未读系统通知数", required = false)
    private Integer unreadSystemNoticeNum;

    @ApiModelProperty(value = "未读评论数", required = false)
    private Integer unreadCommentReplyNum;

    @ApiModelProperty(value = "未读点赞数", required = false)
    private Integer unreadLikeNum;

    @ApiModelProperty(value = "未读留言数", required = false)
    private Integer unreadMessageNum;

    @ApiModelProperty(value = "未读打赏数", required = false)
    private Integer unreadRewardNum;

    @ApiModelProperty(value = "相集数", required = false)
    private Integer photoNum;

    @ApiModelProperty(value = "昵称", required = false)
    private String otherPlatformNick;

    @ApiModelProperty(value = "头像url", required = false)
    private String otherPlatformFaceImgUrl;

    @ApiModelProperty("日推数")
    private Integer newsNum;

    @ApiModelProperty("角色信息")
    private List<String> roleList;

    @ApiModelProperty("角色信息")
    private List<String> tagList;

    @ApiModelProperty("登陆码")
    private String masterId;
}