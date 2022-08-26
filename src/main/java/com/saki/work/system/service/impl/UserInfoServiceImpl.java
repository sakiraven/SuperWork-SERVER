package com.saki.work.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saki.work.base.BaseDTO;
import com.saki.work.base.recall.RecallVO;
import com.saki.work.common.global.exception.capture.BaseBusinessException;
import com.saki.work.common.redis.RedisUtil;
import com.saki.work.myenum.MyEnumDicKey;
import com.saki.work.myenum.MyEnumMessage;
import com.saki.work.myenum.MyEnumRedisKey;
import com.saki.work.system.mapper.UserInfoMapper;
import com.saki.work.system.module.dto.COSImageDTO;
import com.saki.work.system.module.dto.UserInfoDTO;
import com.saki.work.system.module.po.UserInfoPO;
import com.saki.work.system.module.po.UserInfoRoleGroupPO;
import com.saki.work.system.module.vo.LoginVO;
import com.saki.work.system.module.vo.UserInfoVO;
import com.saki.work.system.service.*;
import com.saki.work.utils.LzhUtil;
import com.saki.work.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (UserInfo)表服务实现类
 *
 * @author lzh
 * @since 2021-07-15 10:02:57
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoPO> implements UserInfoService {
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private RedisService redisService;
    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private UserInfoRoleGroupService userInfoRoleGroupService;
    @Resource
    private RoleService roleService;
    @Resource
    private SMSService smsService;

    @Override
    public UserInfoDTO getByPK(Serializable id) {
        UserInfoDTO reUserInfoDTO = new UserInfoDTO();
        UserInfoPO userInfoPO = this.getById(id);
        if (userInfoPO != null) {
            BeanUtils.copyProperties(userInfoPO, reUserInfoDTO);
        } else {
            reUserInfoDTO = null;
        }
        return reUserInfoDTO;
    }

    @Override
    public IPage listPage(UserInfoDTO userInfoDTO) {
        Page<UserInfoPO> userInfoPOPage = new Page<>(userInfoDTO.getPage(), userInfoDTO.getPageSize());
        Page<UserInfoPO> page = this.page(userInfoPOPage);
        return page;
    }

    @Override
    public void delete(Serializable id) {
        this.removeById(id);
    }

    /**
     * 1. 根据id查询用户
     * 2. 判断类型
     * 2.1 手机号登录
     * 2.1.1 校验短信验证码
     * 2.1.2 存在登录成功 不存在 调用注册接口
     * <p>
     * 2.2 其他授权登录 存在登录成功 不存在 throw 绑定手机号
     *
     * @param userInfoDTO
     * @return
     */
    @Override
    public RecallVO<LoginVO> login(UserInfoDTO userInfoDTO) {
        Long id = 0L;

        // 1. 判断类型
        if (userInfoDTO.getLoginType().equals(MyEnumDicKey.LOGIN_PHONE.key)) {
            // 2.1 查询用户
            List<UserInfoPO> loginUserInfoList = this.list(
                    new QueryWrapper<UserInfoPO>()
                            .lambda()
                            .eq(UserInfoPO::getAccount, userInfoDTO.getAccount())
            );

            // 3.1 手机号登录
            // 3.1.1 校验短信验证码
            Boolean isVerify = this.smsService.verifyCode(userInfoDTO.getAccount(), userInfoDTO.getPhoneCode());
            if (!isVerify) {
                throw new BaseBusinessException(MyEnumMessage.PHOTO_VERIFY_CODE_EXPIRE);
            }
            // 3.1.2 存在登录成功 不存在 调用注册接口
            if (loginUserInfoList.isEmpty()) {
                id = this.photoLoginRegister(userInfoDTO.getAccount());
            } else {
                id = loginUserInfoList.get(0).getId();
            }
        } else if (userInfoDTO.getLoginType().equals(MyEnumDicKey.LOGIN_MASTER.key)) {
            List<UserInfoPO> loginUserInfoList = this.list(
                    new QueryWrapper<UserInfoPO>()
                            .lambda()
                            .eq(UserInfoPO::getQqId, userInfoDTO.getMasterId())
            );
            if (loginUserInfoList.isEmpty()) {
                id = this.otherPlatformLoginRegister(null, userInfoDTO.getQqId(), null);
            } else {
                id = loginUserInfoList.get(0).getId();
            }
        } else {
            // 2.2 查询用户
            if (
                    StringUtils.isBlank(userInfoDTO.getWechatId())
                            && StringUtils.isBlank(userInfoDTO.getQqId())
                            && StringUtils.isBlank(userInfoDTO.getAppleId())
            ) {
                BaseBusinessException.throwRunTimeException();
            }
            List<UserInfoPO> loginUserInfoList = this.list(
                    new QueryWrapper<UserInfoPO>()
                            .lambda()
                            .eq(StringUtils.isNotBlank(userInfoDTO.getWechatId()), UserInfoPO::getWechatId, userInfoDTO.getWechatId())
                            .eq(StringUtils.isNotBlank(userInfoDTO.getQqId()), UserInfoPO::getQqId, userInfoDTO.getQqId())
                            .eq(StringUtils.isNotBlank(userInfoDTO.getAppleId()), UserInfoPO::getAppleId, userInfoDTO.getAppleId())
            );
            // 3.2 其他授权登录 存在登录成功 不存在 throw 绑定手机号
            if (loginUserInfoList.isEmpty()) {
//                throw new BaseBusinessException(MyEnumMessage.BINDING_PHONE);
                id = this.otherPlatformLoginRegister(userInfoDTO.getWechatId(), userInfoDTO.getQqId(), userInfoDTO.getAppleId());
            } else {
                id = loginUserInfoList.get(0).getId();
            }
        }

        StpUtil.login(id);

        List<String> roleList = this.listRoleName(id);
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(StpUtil.getTokenValue());
        loginVO.setUserInfoId(Long.parseLong(StpUtil.getLoginId().toString()));
        loginVO.setRoleList(roleList);
        return new RecallVO(loginVO);
    }

    private Long otherPlatformLoginRegister(String wechatId, String qqId, String appleId) {
        // 2. 保存
        UserInfoPO userInfoPO = new UserInfoPO();
        userInfoPO.setAccount("");
        userInfoPO.setNick("saki-" + LzhUtil.getStringRandom(5));
        userInfoPO.setCreateTime(new Date());
        userInfoPO.setFaceImgJson(COSImageDTO.registerDTOJson());
        userInfoPO.setWechatId(wechatId);
        userInfoPO.setQqId(qqId);
        userInfoPO.setAppleId(appleId);
        Long registerRank = this.redisUtil.increment(MyEnumRedisKey.TYPE_STRING_KEY_SYSTEM_REGISTER_RANK.type, 1);
        userInfoPO.setRegisterRank(registerRank.intValue());
        this.save(userInfoPO);
        return userInfoPO.getId();
    }


    private Long photoLoginRegister(String account) {
        // 1. 校验手机号是否注册过
        List<UserInfoPO> list = this.list(
                new QueryWrapper<UserInfoPO>()
                        .lambda()
                        .eq(UserInfoPO::getAccount, account)
        );
        if (!list.isEmpty()) {
            throw new BaseBusinessException(MyEnumMessage.PHONE_REGISTERED);
        }

        // 2. 保存
        UserInfoPO userInfoPO = new UserInfoPO();
        userInfoPO.setAccount(account);
        userInfoPO.setNick("saki-" + LzhUtil.getStringRandom(5));
        userInfoPO.setCreateTime(new Date());
        userInfoPO.setFaceImgJson(COSImageDTO.registerDTOJson());
        Long registerRank = this.redisUtil.increment(MyEnumRedisKey.TYPE_STRING_KEY_SYSTEM_REGISTER_RANK.type, 1);
        userInfoPO.setRegisterRank(registerRank.intValue());
        this.save(userInfoPO);
        return userInfoPO.getId();
    }

    public RecallVO<LoginVO> oldPasswordLogin(UserInfoDTO userInfoDTO) {
        LoginVO loginVO = new LoginVO();

        List<UserInfoPO> relist = this.userInfoService.list(
                new QueryWrapper<UserInfoPO>()
                        .lambda()
                        .eq(UserInfoPO::getAccount, userInfoDTO.getAccount())
        );

        //用户不存在（这个在登录时不会进入，只有在token校验时才有可能进入）
        if (relist.size() == 0) {
            throw new BaseBusinessException(MyEnumMessage.ACCOUNT_PASSWORD);
        }
        UserInfoPO userInfoPO = relist.get(0);
        String md5Password = Md5Util.getPassword(userInfoDTO.getPassword(),userInfoPO.getSalt());
        if (!relist.get(0).getPassword().equals(md5Password)) {
            throw new BaseBusinessException(MyEnumMessage.ACCOUNT_PASSWORD);
        }

        StpUtil.login(userInfoPO.getId());

        loginVO.setToken(StpUtil.getTokenValue());
        loginVO.setUserInfoId(Long.parseLong(StpUtil.getLoginId().toString()));
        return new RecallVO(loginVO);
    }

    /**
     * 根据用户名获取dto
     *
     * @param account
     * @return
     */
    private UserInfoDTO getByAccount(String account) {
        //根据用户名，查询数据库获取到正确的用户信息
        List<UserInfoPO> relist = this.userInfoService.list(
                new QueryWrapper<UserInfoPO>()
                        .lambda()
                        .eq(UserInfoPO::getAccount, account)
        );

        //用户不存在
        if (relist.size() == 0) {
            return null;
        }

        UserInfoPO currentUser = relist.get(0);

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(currentUser, userInfoDTO);
        return userInfoDTO;
    }

    @Override
    public void register(UserInfoDTO userInfoDTO) {
        // 验证账号是否存在
        List<UserInfoPO> existAccount = this.list(
                new QueryWrapper<UserInfoPO>()
                        .lambda()
                        .eq(UserInfoPO::getAccount, userInfoDTO.getAccount())
        );

        if (!existAccount.isEmpty()) {
            throw new BaseBusinessException(MyEnumMessage.REGISTER_ERROR_ACCOUNT_EXIST);
        }

        //生成盐（部分，需要存入数据库中）
        String salt = Md5Util.getSalt();

        //将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
        String pwd = Md5Util.getPassword(userInfoDTO.getPassword(),salt);
        userInfoDTO.setSalt(salt);
        userInfoDTO.setPassword(pwd);

        UserInfoPO userInfoPO = new UserInfoPO();
        BeanUtils.copyProperties(userInfoDTO, userInfoPO);

        this.save(userInfoPO);
    }

    @Override
    public <T extends BaseDTO> void buildSendUserInfoVONullNotExistDefault(List<T> baseDTOList) {
        for (T dto : baseDTOList) {
            dto.setSendUserInfoVO(this.getExistUserInfoVO(dto.getSendUserInfoId()));
        }
    }

    public UserInfoVO getExistUserInfoVO(Long id) {
        UserInfoVO userInfoVO = new UserInfoVO();

        UserInfoPO userInfoPO = this.getById(id);
        if (userInfoPO == null) {
            userInfoVO = new UserInfoVO(false);
        } else {
            userInfoVO.setFaceCOSImageDTO(new Gson().fromJson(userInfoPO.getFaceImgJson(), COSImageDTO.class));
            userInfoVO.setId(userInfoPO.getId());
            userInfoVO.setNick(userInfoPO.getNick());
            userInfoVO.setIsExist(true);
        }
        return userInfoVO;
    }

    @Override
    public List<String> listRoleName(Long userInfoId) {
        List<String> roleNameList = new ArrayList<>();
        try {
            Object roleListJsonObject = this.redisUtil.hashGet(MyEnumRedisKey.TYPE_HASH_KEY_USER_INFO_ROLE.type, String.valueOf(userInfoId));
            if (roleListJsonObject != null) {
                roleNameList = new Gson().fromJson(roleListJsonObject.toString(), new TypeToken<List<String>>() {
                }.getType());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (!roleNameList.isEmpty()) {
                return roleNameList;
            }
        }

        List<Long> roleIdList = this.userInfoRoleGroupService.list(
                new QueryWrapper<UserInfoRoleGroupPO>()
                        .lambda()
                        .eq(UserInfoRoleGroupPO::getUserInfoId, userInfoId)
        ).stream().map(
                userInfoRoleGroupPO -> {
                    return userInfoRoleGroupPO.getRoleId();
                }
        ).collect(Collectors.toList());

        if (roleIdList.isEmpty()) {
            return new ArrayList<>();
        }

        roleNameList = this.roleService.listByIds(roleIdList).stream()
                .map(rolePO -> {
                    return rolePO.getName();
                }).collect(Collectors.toList());
        this.redisUtil.hashPut(MyEnumRedisKey.TYPE_HASH_KEY_USER_INFO_ROLE.type, userInfoId.toString(), new Gson().toJson(roleNameList));
        return roleNameList;
    }
}