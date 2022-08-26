package com.saki.work.system.controller;

import com.saki.work.base.recall.RecallVO;
import com.saki.work.common.filter.LimitRequest;
import com.saki.work.system.module.bo.SystemCodeImgBO;
import com.saki.work.system.module.bo.SystemUploadArrayBO;
import com.saki.work.system.module.bo.SystemUploadSingleBO;
import com.saki.work.system.module.dto.SystemDTO;
import com.saki.work.system.module.vo.SystemUploadArrayVO;
import com.saki.work.system.module.vo.SystemUploadSingleVO;
import com.saki.work.system.module.vo.SystemVersionIosVO;
import com.saki.work.system.service.SystemService;
import com.saki.work.utils.VerifyCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * (UserInfo)表服务实现类
 *
 * @author lzh
 * @since 2021-07-15 10:02:57
 */
@Api(tags = "(系统)服务实现类")
@Validated
@RestController
@RequestMapping("/system")
public class SystemController {
    @Resource
    private SystemService systemService;

    @ApiOperation(
            value = "上传文件",
            notes = ""
    )
    @PostMapping("/upload/single")
    public RecallVO<SystemUploadSingleVO> uploadSingle(SystemUploadSingleBO systemUploadSingleBO) {
        SystemDTO systemDTO = new SystemDTO();
        BeanUtils.copyProperties(systemUploadSingleBO, systemDTO);
        return new RecallVO(this.systemService.uploadSingle(systemDTO), SystemUploadSingleVO.class);
    }

    @ApiOperation(
            value = "上传文件",
            notes = ""
    )
    @PostMapping("/upload/array")
    public RecallVO<SystemUploadSingleVO> uploadArray(SystemUploadArrayBO systemUploadArrayBO) {
        SystemDTO systemDTO = new SystemDTO();
        BeanUtils.copyProperties(systemUploadArrayBO, systemDTO);
        return new RecallVO(this.systemService.uploadArray(systemDTO), SystemUploadArrayVO.class);
    }

    @LimitRequest(count = 20)
    @ApiOperation(
            value = "获取图形验证码",
            notes = ""
    )
    @GetMapping("/code/img/{uuid}")
    public void getVerifyCodeImg(HttpServletResponse response, HttpSession session,@PathVariable String uuid) {
        ByteArrayOutputStream output = this.systemService.getCodeImg(uuid);
        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @LimitRequest(count = 20)
    @ApiOperation(
            value = "获取苹果端版本号",
            notes = ""
    )
    @PostMapping("/version/ios")
    public RecallVO<SystemVersionIosVO> versionIOS() {
        return new RecallVO(this.systemService.versionIOS(), SystemVersionIosVO.class);
    }

    @LimitRequest(count = 20)
    @ApiOperation(
            value = "获取安卓端版本号",
            notes = ""
    )
    @PostMapping("/version/android")
    public RecallVO<SystemVersionIosVO> versionAndroid() {
        return new RecallVO(this.systemService.versionAndroid(), SystemVersionIosVO.class);
    }

    @LimitRequest(count = 1000)
    @ApiOperation(
            value = "获取图片",
            notes = ""
    )
    @GetMapping("/image")
    public void getImage(HttpServletResponse response, HttpSession session,@RequestParam String path) {
        ByteArrayOutputStream output = this.systemService.getImage(path);
        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}