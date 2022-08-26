package com.saki.work.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.saki.work.system.module.po.PermissionPO;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * (Permission)表数据库访问层
 *
 * @author lzh
 * @since 2021-07-14 16:38:21
 */
public interface PermissionMapper extends BaseMapper<PermissionPO> {

    /**
     * 根据用户id获取用户权限
     *
     * @param userInfoId
     * @return
     */
    Set<String> setPermissionByUserInfoId(@Param("userInfoId") String userInfoId);
}