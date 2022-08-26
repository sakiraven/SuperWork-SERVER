package com.saki.work.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.saki.work.common.global.exception.capture.BaseBusinessException;
import com.saki.work.common.rabbitmq.RabbitmqConfig;
import com.saki.work.mapper.WorkMapper;
import com.saki.work.module.dto.CourseInfoDTO;
import com.saki.work.module.dto.WorkDTO;
import com.saki.work.module.po.WorkPO;
import com.saki.work.myenum.*;
import com.saki.work.service.WorkService;
import com.saki.work.utils.LzhUtil;
import com.saki.work.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Work)表服务实现类
 *
 * @author lzh
 * @since 2022-08-16 10:11:57
 */
@Service("workService")
public class WorkServiceImpl extends ServiceImpl<WorkMapper, WorkPO> implements WorkService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${path.image}")
    private String pathImage;

    @Override
    public IPage homePage(WorkDTO workDTO) {
        Long loginUserInfoId = StpUtil.getLoginId(0L);
        Integer page = workDTO.getPage();
        Integer pageSize = 20;
        MyEnumOrder myEnumOrder = workDTO.getMyEnumOrder();
        Boolean isJieMessage = workDTO.getIsJieMessage();

        IPage<WorkPO> userInfoVOIPage = new Page<>(page, pageSize);
        IPage<WorkDTO> workDTOIPage = this.page(
                userInfoVOIPage,
                new QueryWrapper<WorkPO>()
                        .lambda()
                        .eq(isJieMessage != null, WorkPO::getIsJieMessage, isJieMessage)
                        .eq(WorkPO::getApproveStatus, MyEnumApproveStatus.APPROVED.key)
                        .orderByDesc(myEnumOrder.equals(MyEnumOrder.LIKE_DESC), WorkPO::getLikeNums)
                        .orderByDesc(myEnumOrder.equals(MyEnumOrder.CREATE_TIME_DESC), WorkPO::getCreateTime)
        ).convert(workPO -> {
            WorkDTO returnWorkDTO = new WorkDTO();
            BeanUtils.copyProperties(workPO, returnWorkDTO);
            this.buildPageDTO(returnWorkDTO, loginUserInfoId);
            return returnWorkDTO;
        });
        return workDTOIPage;
    }

    private void buildPageDTO(WorkDTO returnWorkDTO, Long loginUserInfoId) {
        String mineMessageType = this.getMineMessageType(returnWorkDTO.getId(), loginUserInfoId);
        returnWorkDTO.setIsMineMessage(mineMessageType != null);
        returnWorkDTO.setMineMessageType(mineMessageType);
        returnWorkDTO.setLikeNumsLabel(StringUtil.numberFormat(returnWorkDTO.getLikeNums()));
        returnWorkDTO.setNotLikeNumsLabel(StringUtil.numberFormat(returnWorkDTO.getNotLikeNums()));
        returnWorkDTO.setOrdinaryNumsLabel(StringUtil.numberFormat(returnWorkDTO.getOrdinaryNums()));
        returnWorkDTO.setCreateTimeLabel(StringUtil.friendly_time(returnWorkDTO.getCreateTime()));
        String tagsJson = StringUtils.isNotBlank(returnWorkDTO.getTagsJson()) ? returnWorkDTO.getTagsJson() : "[]";
        List<String> originTagList = new Gson().fromJson(tagsJson, new TypeToken<List<String>>() {
        }.getType());
        List<String> tagNameList = originTagList.stream().map(originName -> {
            return MyEnumTagName.getContentByKey(originName);
        }).collect(Collectors.toList());
        returnWorkDTO.setTagNameList(tagNameList);
    }

    @Override
    public IPage minePage(WorkDTO workDTO) {
        Integer page = workDTO.getPage();
        Integer pageSize = 20;
        Long loginUserInfoId = Long.parseLong((String) StpUtil.getLoginId());

        IPage<WorkPO> userInfoVOIPage = new Page<>(page, pageSize);
        IPage<WorkDTO> workDTOIPage = this.page(
                userInfoVOIPage,
                new QueryWrapper<WorkPO>()
                        .lambda()
                        .eq(WorkPO::getUserInfoId, loginUserInfoId)
                        .orderByDesc(WorkPO::getCreateTime)
        ).convert(workPO -> {
            WorkDTO returnWorkDTO = new WorkDTO();
            BeanUtils.copyProperties(workPO, returnWorkDTO);
            this.buildPageDTO(returnWorkDTO, loginUserInfoId);
            return returnWorkDTO;
        });
        return workDTOIPage;
    }

    @Override
    public void comment(WorkDTO workDTO) {
        Long id = workDTO.getId();
        MyEnumCommentType myEnumCommentType = workDTO.getMyEnumCommentType();
        String jieMessage = workDTO.getJieMessage();
        Long loginUserInfoId = Long.parseLong((String) StpUtil.getLoginId());
        List<String> roleList = StpUtil.getRoleList();

        String mineMessageType = this.getMineMessageType(id, loginUserInfoId);
        if (StringUtils.isNotBlank(mineMessageType)) {
            return;
        }
        this.addMineMessageType(id, loginUserInfoId, myEnumCommentType);

        workDTO.setLoginUserInfoId(loginUserInfoId);
        workDTO.setRoleList(roleList);

        String workDTOJson = new Gson().toJson(workDTO);
        this.rabbitTemplate.convertAndSend(RabbitmqConfig.TOPIC_ONE, workDTOJson);
    }

    @Override
    public IPage pendingReviewPage(WorkDTO workDTO) {
        Long loginUserInfoId = StpUtil.getLoginId(0L);
        Integer page = workDTO.getPage();
        Integer pageSize = 20;

        IPage<WorkPO> userInfoVOIPage = new Page<>(page, pageSize);
        IPage<WorkDTO> workDTOIPage = this.page(
                userInfoVOIPage,
                new QueryWrapper<WorkPO>()
                        .lambda()
                        .eq(WorkPO::getApproveStatus, MyEnumApproveStatus.NOT_START.key)
                        .eq(WorkPO::getIsInit, true)
                        .orderByAsc(WorkPO::getCreateTime)
        ).convert(workPO -> {
            WorkDTO returnWorkDTO = new WorkDTO();
            BeanUtils.copyProperties(workPO, returnWorkDTO);
            this.buildPageDTO(returnWorkDTO, loginUserInfoId);
            return returnWorkDTO;
        });

        return workDTOIPage;
    }

    @Override
    public void putInfo(WorkDTO workDTO) {
        String mineMessage = workDTO.getMineMessage().replaceAll("\n","");
        String courseId = workDTO.getCourseId();
        Long loginUserInfoId = StpUtil.getLoginId(0L);
        if (loginUserInfoId == 0L) {
            return;
        }

        List<WorkPO> workPOList = this.list(
                new QueryWrapper<WorkPO>()
                        .lambda()
                        .eq(WorkPO::getCourseId, courseId)
                        .ne(WorkPO::getApproveStatus, MyEnumApproveStatus.REJECTED.key)
        );
        if (!workPOList.isEmpty()) {
            throw new BaseBusinessException(MyEnumMessage.EXIST_COURSE_ID);
        }

        this.save(
                WorkPO.builder()
                        .userInfoId(loginUserInfoId)
                        .courseId(courseId)
                        .title("")
                        .facePath("")
                        .mineMessage(mineMessage)
                        .isJieMessage(false)
                        .jieMessageType("")
                        .jieMessage("")
                        .approveStatus(MyEnumApproveStatus.NOT_START.key)
                        .rejectMessage("")
                        .tagsJson("[]")
                        .likeNums(0)
                        .notLikeNums(0)
                        .ordinaryNums(0)
                        .thumbnailPath("")
                        .entirePath("")
                        .isInit(false)
                        .createTime(new Date())
                        .initCount(0)
                        .build()
        );
    }

    @Override
    public void postReview(WorkDTO workDTO) {
        Long id = workDTO.getId();
        String rejectMessage = workDTO.getRejectMessage();
        MyEnumApproveStatus myEnumApproveStatus = workDTO.getMyEnumApproveStatus();

        WorkPO workPO = this.getById(id);
        if (workPO == null) {
            return;
        }

        if (!workPO.getApproveStatus().equals(MyEnumApproveStatus.NOT_START.key)) {
            throw new BaseBusinessException(MyEnumMessage.STATUS_ERROR);
        }

        this.updateById(
                WorkPO.builder()
                        .id(id)
                        .rejectMessage(myEnumApproveStatus.equals(MyEnumApproveStatus.REJECTED.key) ? "" : rejectMessage)
                        .approveStatus(myEnumApproveStatus.key)
                        .createTime(new Date())
                        .build()
        );
    }

    @Override
    public void postInit() {
        restTemplate.setRequestFactory(getFactory());
        List<WorkPO> workPOList = this.list(
                new QueryWrapper<WorkPO>()
                        .lambda()
                        .eq(WorkPO::getIsInit, false)
                        .eq(WorkPO::getApproveStatus, MyEnumApproveStatus.NOT_START)
                        .last("limit 1")
                        .orderByAsc(WorkPO::getCreateTime)
        );
        if (workPOList.isEmpty()) {
            return;
        }
        WorkPO workPO = workPOList.get(0);
        String courseId = workPO.getCourseId();

        String url = "https://tgrcode.com/mm2/level_info/" + courseId;
        String thumbnailImageUrl = "https://tgrcode.com/mm2/level_thumbnail/" + courseId;
        String entireThumbnailImageUrl = "https://tgrcode.com/mm2/level_entire_thumbnail/" + courseId;

        String thumbnailPath = "";
        String entireThumbnailPath = "";
        String facePath = "";
        String name = "";
        List<String> tagNameList = new ArrayList<>();
        ResponseEntity<CourseInfoDTO> forEntity = null;
        try {
            forEntity = this.restTemplate.getForEntity(url, CourseInfoDTO.class);
            if (!forEntity.getStatusCode().equals(HttpStatus.OK)) {
                Thread.sleep(60000);
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            CourseInfoDTO courseInfoDTO = forEntity.getBody();
            name = courseInfoDTO.getName();
            String faceUrl = courseInfoDTO.getUploader().getMiiImage();
            tagNameList = courseInfoDTO.getTagsName();
            thumbnailPath = LzhUtil.downloadPicture(pathImage, thumbnailImageUrl);
            entireThumbnailPath = LzhUtil.downloadPicture(pathImage, entireThumbnailImageUrl);
            facePath = LzhUtil.downloadPicture(pathImage, faceUrl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        workPO.setEntirePath(entireThumbnailPath);
        workPO.setFacePath(facePath);
        workPO.setThumbnailPath(thumbnailPath);
        workPO.setTitle(name);
        workPO.setInitCount(workPO.getInitCount() + 1);
        workPO.setTagsJson(new Gson().toJson(tagNameList));
        workPO.setIsInit(
                StringUtils.isNotBlank(name)
                        && StringUtils.isNotBlank(thumbnailPath)
                        && StringUtils.isNotBlank(entireThumbnailPath)
                        && StringUtils.isNotBlank(facePath)
        );

        if (workPO.getInitCount().intValue() >= 10) {
            workPO.setRejectMessage("系统尝试获取10次关卡信息,均为失败.有可能是服务器代理出现异常.也有可能是关卡ID不存在,请稍后再试.");
            workPO.setApproveStatus(MyEnumApproveStatus.REJECTED.key);
        }

        this.updateById(workPO);
    }

    @Override
    public void putTestData() {
        List<WorkPO> list = this.list(
                new QueryWrapper<WorkPO>()
                        .lambda()
                        .last("limit 1")
        );

        for (int i = 0; i < 1000; i++) {
            WorkPO workPO = list.get(0);
            workPO.setId(null);
            this.save(workPO);
        }
    }

    private void addMineMessageType(Long id, Long loginUserInfoId, MyEnumCommentType myEnumCommentType) {
        String redisKey = MessageFormat.format(
                MyEnumRedisKey.TYPE_HASH_KEY_WORK_COMMENT_0.type,
                id.toString()
        );
        this.redisTemplate.opsForHash().put(redisKey, loginUserInfoId.toString(), myEnumCommentType.key);
    }

    private String getMineMessageType(Long id, Long loginUserInfoId) {
        if (loginUserInfoId == 0L) {
            return "";
        }
        String redisKey = MessageFormat.format(
                MyEnumRedisKey.TYPE_HASH_KEY_WORK_COMMENT_0.type,
                id.toString()
        );
        Object redisObject = this.redisTemplate.opsForHash().get(redisKey, loginUserInfoId.toString());
        if (redisObject == null) {
            return "";
        }
        try {
            String redisString = (String) redisObject;
            return redisString;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static SimpleClientHttpRequestFactory getFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //单位为ms
        factory.setReadTimeout(10 * 1000);
        //单位为ms
        factory.setConnectTimeout(30 * 1000);
        // 代理的url网址或ip, port端口
        InetSocketAddress address = new InetSocketAddress("192.168.50.148", 7890);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
        factory.setProxy(proxy);
        return factory;
    }
}