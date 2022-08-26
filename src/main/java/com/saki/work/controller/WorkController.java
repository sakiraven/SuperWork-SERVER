package com.saki.work.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.saki.work.base.recall.RecallCUD;
import com.saki.work.base.recall.RecallList;
import com.saki.work.module.bo.*;
import com.saki.work.module.dto.WorkDTO;
import com.saki.work.module.vo.WorkHomePageVO;
import com.saki.work.module.vo.WorkMinePageVO;
import com.saki.work.module.vo.WorkReviewPageVO;
import com.saki.work.service.WorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * (Work)表服务实现类
 *
 * @author lzh
 * @since 2022-08-16 10:11:56
 */
@Api(tags = "(Work)表服务实现类")
@Validated
@RestController
@RequestMapping("/work")
public class WorkController {

    /**
     * 服务对象
     */
    @Resource
    private WorkService workService;

    @ApiOperationSupport(order = 1)
    @ApiOperation("首页翻页查询")
    @GetMapping("/home/page")
    public RecallList<WorkHomePageVO> homePage(@Valid WorkHomePageBO workHomePageBO) {
        WorkDTO workDTO = new WorkDTO();
        BeanUtils.copyProperties(workHomePageBO, workDTO);

        return new RecallList(this.workService.homePage(workDTO), WorkHomePageVO.class);
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation("我的列表")
    @GetMapping("/mine/page")
    public RecallList<WorkMinePageVO> minePage(@Valid WorkMinePageBO workMinePageBO) {
        StpUtil.checkLogin();
        WorkDTO workDTO = new WorkDTO();
        BeanUtils.copyProperties(workMinePageBO, workDTO);

        return new RecallList(this.workService.minePage(workDTO), WorkMinePageVO.class);
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation("待审核列表")
    @GetMapping("/review/page")
    public RecallList<WorkReviewPageVO> pendingReviewPage(@Valid WorkReviewPageBO workReviewPageBO) {
        StpUtil.checkLogin();
        StpUtil.checkRole("admin");
        WorkDTO workDTO = new WorkDTO();
        BeanUtils.copyProperties(workReviewPageBO, workDTO);
        return new RecallList(this.workService.pendingReviewPage(workDTO), WorkReviewPageVO.class);
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation("发布")
    @PostMapping("/info")
    public RecallCUD putInfo(@Valid @RequestBody WorkPutInfoBO workPutInfoBO) {
        StpUtil.checkLogin();
        WorkDTO workDTO = new WorkDTO();
        BeanUtils.copyProperties(workPutInfoBO, workDTO);
        this.workService.putInfo(workDTO);
        return new RecallCUD();
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation("审查")
    @PostMapping("/review")
    public RecallCUD postReview(@Valid @RequestBody WorkPostReviewBO workPostReviewBO) {
        StpUtil.checkLogin();
        WorkDTO workDTO = new WorkDTO();
        BeanUtils.copyProperties(workPostReviewBO, workDTO);
        this.workService.postReview(workDTO);
        return new RecallCUD();
    }

    @ApiOperationSupport(order = 6)
    @ApiOperation("评价")
    @PostMapping("/comment")
    public RecallCUD putComment(@Valid @RequestBody WorkCommentPageBO workCommentPageBO) {
        StpUtil.checkLogin();
        WorkDTO workDTO = new WorkDTO();
        BeanUtils.copyProperties(workCommentPageBO, workDTO);
        this.workService.comment(workDTO);
        return new RecallCUD();
    }

}