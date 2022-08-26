package com.saki.work.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.saki.work.base.BaseDTO;
import com.saki.work.base.recall.RecallVO;
import com.saki.work.system.module.dto.UserInfoDTO;
import com.saki.work.system.module.po.UserInfoPO;
import com.saki.work.system.module.vo.LoginVO;
import com.saki.work.system.module.vo.UserInfoVO;

import java.io.Serializable;
import java.util.List;

/**
 * (UserInfo)表服务接口
 *
 * @author lzh
 * @since 2021-07-15 10:02:57
 */
public interface UserInfoService extends IService<UserInfoPO> {
    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    UserInfoDTO getByPK(Serializable id);

    /**
     * 通用分页查询
     *
     * @return 实例对象
     */
    IPage listPage(UserInfoDTO userInfoDTO);

    /**
     * 通用删除
     *
     * @param id
     */
    void delete(Serializable id);

    /**
     * 登陆
     *
     * @param userInfoDTO
     * @return
     */
    RecallVO<LoginVO> login(UserInfoDTO userInfoDTO);

    /**
     * 注册
     *
     * @param userInfoDTO
     */
    void register(UserInfoDTO userInfoDTO);


    /**
     * 构建用户信息
     *
     * @param baseDTOList
     * @param <T>
     */
    <T extends BaseDTO> void buildSendUserInfoVONullNotExistDefault(List<T> baseDTOList);

    /**
     * 角色列表
     * @param userInfoId
     * @return
     */
    List<String> listRoleName(Long userInfoId);
}