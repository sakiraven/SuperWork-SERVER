package com.saki.work.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.saki.work.system.module.dto.UserInfoRoleGroupDTO;
import com.saki.work.system.module.po.UserInfoRoleGroupPO;

import java.io.Serializable;

/**
 * (UserInfoRoleGroup)表服务接口
 *
 * @author lzh
 * @since 2021-07-14 18:28:30
 */
public interface UserInfoRoleGroupService extends IService<UserInfoRoleGroupPO> {
    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    UserInfoRoleGroupDTO getByPK(Serializable id);

    /**
     * 通用分页查询
     *
     * @return 实例对象
     */
    IPage listPage(UserInfoRoleGroupDTO userInfoRoleGroupDTO);

    /**
     * 通用删除
     *
     * @param id
     */
    void delete(Serializable id);
}