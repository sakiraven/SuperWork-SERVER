package com.saki.work.system.controller;

import com.saki.work.base.recall.RecallVO;
import com.saki.work.system.module.vo.COSSecretVO;
import com.saki.work.system.module.vo.SystemUploadSingleVO;
import com.saki.work.system.service.COSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (UserInfo)表服务实现类
 *
 * @author lzh
 * @since 2021-07-15 10:02:57
 */
@Api(tags = "(对象存储)服务实现类")
@Validated
@RestController
@RequestMapping("/cos")
public class COSController {
    @Resource
    private COSService cosService;

    @ApiOperation(
            value = "获取临时密钥",
            notes = ""
    )
    @PostMapping("/secret/temp")
    public RecallVO<SystemUploadSingleVO> secretTemp() {
        return new RecallVO(this.cosService.secretTemp(), COSSecretVO.class);
    }

}