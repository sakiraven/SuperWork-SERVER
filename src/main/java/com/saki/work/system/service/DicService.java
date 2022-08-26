package com.saki.work.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.saki.work.system.module.dto.DicDTO;
import com.saki.work.system.module.po.DicPO;

import java.io.Serializable;

/**
 * (Dic)表服务接口
 *
 * @author lzh
 * @since 2021-08-03 14:43:56
 */
public interface DicService extends IService<DicPO> {
    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    DicDTO getByPK(String id);

    /**
     * 通用分页查询
     *
     * @return 实例对象
     */
    IPage listPage(DicDTO dicDTO);

    /**
     * 通用删除
     *
     * @param id
     */
    void delete(Serializable id);
}