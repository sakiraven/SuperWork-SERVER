package com.saki.work.common.rabbitmq;

import com.google.gson.Gson;
import com.saki.work.module.dto.WorkDTO;
import com.saki.work.module.po.WorkPO;
import com.saki.work.myenum.MyEnumCommentType;
import com.saki.work.service.WorkService;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class RabbitmqConsumerService {

    @Resource
    private WorkService workService;

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("topic.one"))
    public void process(String workDTOJson) {
        WorkDTO workDTO = new Gson().fromJson(workDTOJson, WorkDTO.class);
        Long id = workDTO.getId();
        Long loginUserInfoId = workDTO.getLoginUserInfoId();
        MyEnumCommentType myEnumCommentType = workDTO.getMyEnumCommentType();
        List<String> roleList = workDTO.getRoleList();
        String jieMessage = workDTO.getJieMessage();

        WorkPO workPO = this.workService.getById(id);
        if (workPO == null) {
            return;
        }

        switch (myEnumCommentType) {
            case LIKE:
                workPO.setLikeNums(workPO.getLikeNums() + 1);
                break;
            case NOT_LIKE:
                workPO.setNotLikeNums(workPO.getNotLikeNums() + 1);
                break;
            case ORDINARY:
                workPO.setOrdinaryNums(workPO.getOrdinaryNums() + 1);
                break;
        }

        WorkPO updateWorkPO = WorkPO.builder()
                .id(id)
                .likeNums(workPO.getLikeNums())
                .notLikeNums(workPO.getNotLikeNums())
                .ordinaryNums(workPO.getOrdinaryNums())
                .build();

        for (String role : roleList) {
            if (role.equals("jie")){
                updateWorkPO.setJieMessage(jieMessage);
                updateWorkPO.setIsJieMessage(true);
                updateWorkPO.setJieMessageType(myEnumCommentType.key);
            }
        }

        this.workService.updateById(updateWorkPO);
        System.out.println("消费者消费消息111=====" + workDTO);
    }
}
