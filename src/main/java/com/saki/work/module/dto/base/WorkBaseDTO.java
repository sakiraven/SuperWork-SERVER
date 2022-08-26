package com.saki.work.module.dto.base;

import com.saki.work.base.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Work)业务扩展DTO
 *
 * @author lzh
 * @since 2022-08-17 11:24:18
 */
@Data
public class WorkBaseDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 215032977647228547L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("用户信息")
    private Long userInfoId;

    @ApiModelProperty("关卡id 用户查询关卡详情")
    private String courseId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("头像路径")
    private String facePath;

    @ApiModelProperty("整个缩略图")
    private String entirePath;

    @ApiModelProperty("缩略图")
    private String thumbnailPath;

    @ApiModelProperty("用户自己的留言")
    private String mineMessage;

    @ApiModelProperty("超级小桀是否评论")
    private Boolean isJieMessage;

    @ApiModelProperty("超级小桀评价类型")
    private String jieMessageType;

    @ApiModelProperty("超级小桀留言")
    private String jieMessage;

    @ApiModelProperty("是否通过审核")
    private String approveStatus;

    @ApiModelProperty("拒绝原因")
    private String rejectMessage;

    @ApiModelProperty("标签json")
    private String tagsJson;

    @ApiModelProperty("喜欢数")
    private Integer likeNums;

    @ApiModelProperty("不喜欢数量")
    private Integer notLikeNums;

    @ApiModelProperty("普通数量")
    private Integer ordinaryNums;

    @ApiModelProperty("是否初始化")
    private Boolean isInit;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("初始化次数")
    private Integer initCount;

}