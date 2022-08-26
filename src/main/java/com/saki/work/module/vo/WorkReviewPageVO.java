package com.saki.work.module.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkReviewPageVO {
    @ApiModelProperty(value = "主键id",required = true)
    private Long id;

    @ApiModelProperty("喜欢数量标签")
    private String likeNumsLabel;

    @ApiModelProperty("不喜欢数量标签")
    private String notLikeNumsLabel;

    @ApiModelProperty("普通数量标签")
    private String ordinaryNumsLabel;

    @ApiModelProperty("关卡id 用户查询关卡详情")
    private String courseId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("头像路径")
    private String facePath;

    @ApiModelProperty("用户自己的留言")
    private String mineMessage;

    @ApiModelProperty("超级小桀留言")
    private String jieMessage;

    @ApiModelProperty("超级小桀是否评论")
    private Boolean isJieMessage;

    @ApiModelProperty("是否通过审核")
    private String approveStatus;

    @ApiModelProperty("标签json")
    private Object tagsJson;

    @ApiModelProperty("超级小桀评价类型")
    private String jieMessageType;

    @ApiModelProperty("喜欢数")
    private Integer likeNums;

    @ApiModelProperty("不喜欢数量")
    private Integer notLikeNums;

    @ApiModelProperty("普通数量")
    private Integer ordinaryNums;

    @ApiModelProperty("缩略图")
    private String thumbnailPath;

    @ApiModelProperty("整个缩略图")
    private String entirePath;

    @ApiModelProperty("创建时间")
    private String createTimeLabel;

    @ApiModelProperty(value = "标签列表",required = true)
    private List<String> tagNameList;

    @ApiModelProperty("评价类型")
    private String mineMessageType;
}
