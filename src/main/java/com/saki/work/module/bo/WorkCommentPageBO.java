package com.saki.work.module.bo;

import com.saki.work.myenum.MyEnumCommentType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class WorkCommentPageBO {
    @NotNull
    @ApiModelProperty(value = "作业主键id",required = true)
    private Long id;

    @NotNull
    @ApiModelProperty(value = "评论类型",required = true)
    private MyEnumCommentType myEnumCommentType;

    @ApiModelProperty(value = "超级小桀留言",required = true)
    private String jieMessage;
}
