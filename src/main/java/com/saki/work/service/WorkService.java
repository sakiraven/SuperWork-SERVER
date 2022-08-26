package com.saki.work.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.saki.work.module.dto.WorkDTO;
import com.saki.work.module.po.WorkPO;

import java.util.List;

/**
 * (Work)表服务接口
 *
 * @author lzh
 * @since 2022-08-16 10:11:57
 */
public interface WorkService extends IService<WorkPO> {
    /**
     * 主页信息
     * @param workDTO
     * @return
     */
    IPage homePage(WorkDTO workDTO);

    /**
     * 我的列表
     * @param workDTO
     * @return
     */
    IPage minePage(WorkDTO workDTO);

    /**
     * 评论
     * @param workDTO
     */
    void comment(WorkDTO workDTO);

    /**
     * 等待审查页
     * @param workDTO
     */
    IPage pendingReviewPage(WorkDTO workDTO);

    /**
     * 添加信息
     * @param workDTO
     */
    void putInfo(WorkDTO workDTO);

    /**
     * 更新审查
     * @param workDTO
     */
    void postReview(WorkDTO workDTO);

    /**
     * 更新数据
     */
    void postInit();

    void putTestData();
}