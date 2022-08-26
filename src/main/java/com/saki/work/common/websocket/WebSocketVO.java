package com.saki.work.common.websocket;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketVO {
    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("json数据")
    private String data;
}
