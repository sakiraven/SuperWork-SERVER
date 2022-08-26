package com.saki.work.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saki.work.system.mapper.UserInfoRoleGroupMapper;
import com.saki.work.system.module.dto.UserInfoRoleGroupDTO;
import com.saki.work.system.module.po.UserInfoRoleGroupPO;
import com.saki.work.system.service.UserInfoRoleGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * (UserInfoRoleGroup)表服务实现类
 *
 * @author lzh
 * @since 2021-07-14 18:28:31
 */
@Service("userInfoRoleGroupService")
public class UserInfoRoleGroupServiceImpl extends ServiceImpl<UserInfoRoleGroupMapper, UserInfoRoleGroupPO> implements UserInfoRoleGroupService {
    @Resource
    private UserInfoRoleGroupMapper userInfoRoleGroupMapper;

    @Override
    public UserInfoRoleGroupDTO getByPK(Serializable id) {
        UserInfoRoleGroupDTO reUserInfoRoleGroupDTO = new UserInfoRoleGroupDTO();
        BeanUtils.copyProperties(this.getById(id), reUserInfoRoleGroupDTO);
        return reUserInfoRoleGroupDTO;
    }

    @Override
    public IPage listPage(UserInfoRoleGroupDTO userInfoRoleGroupDTO) {
        Page<UserInfoRoleGroupPO> userInfoRoleGroupPOPage = new Page<>(userInfoRoleGroupDTO.getPage(), userInfoRoleGroupDTO.getPageSize());
        Page<UserInfoRoleGroupPO> page = this.page(userInfoRoleGroupPOPage);
        return page;
    }

    @Override
    public void delete(Serializable id) {
        this.removeById(id);
    }

}