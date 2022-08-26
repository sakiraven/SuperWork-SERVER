package com.saki.work.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saki.work.system.mapper.RolePermissionGroupMapper;
import com.saki.work.system.module.dto.RolePermissionGroupDTO;
import com.saki.work.system.module.po.RolePermissionGroupPO;
import com.saki.work.system.service.RolePermissionGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * (RolePermissionGroup)表服务实现类
 *
 * @author lzh
 * @since 2021-07-14 16:38:33
 */
@Service("rolePermissionGroupService")
public class RolePermissionGroupServiceImpl extends ServiceImpl<RolePermissionGroupMapper, RolePermissionGroupPO> implements RolePermissionGroupService {
    @Resource
    private RolePermissionGroupMapper rolePermissionGroupMapper;

    @Override
    public RolePermissionGroupDTO getByPK(Serializable id) {
        RolePermissionGroupDTO reRolePermissionGroupDTO = new RolePermissionGroupDTO();
        BeanUtils.copyProperties(this.getById(id), reRolePermissionGroupDTO);
        return reRolePermissionGroupDTO;
    }

    @Override
    public IPage listPage(RolePermissionGroupDTO rolePermissionGroupDTO) {
        Page<RolePermissionGroupPO> rolePermissionGroupPOPage = new Page<>(rolePermissionGroupDTO.getPage(), rolePermissionGroupDTO.getPageSize());
        Page<RolePermissionGroupPO> page = this.page(rolePermissionGroupPOPage);
        return page;
    }

    @Override
    public void delete(Serializable id) {
        this.removeById(id);
    }

}