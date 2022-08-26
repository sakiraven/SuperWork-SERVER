package com.saki.work.system.service.impl;

import com.saki.work.common.global.exception.capture.BaseBusinessException;
import com.saki.work.common.redis.RedisUtil;
import com.saki.work.myenum.MyEnumMessage;
import com.saki.work.myenum.MyEnumRedisKey;
import com.saki.work.system.module.dto.SystemDTO;
import com.saki.work.system.service.COSService;
import com.tencent.cloud.Response;
import com.tencent.cloud.cos.util.Jackson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("COSService")
public class COSServiceImpl implements COSService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public SystemDTO secretTemp() {
//        cosUtil.testGetCredential();

        Object json = this.redisUtil.getValue(MyEnumRedisKey.TYPE_STRING_KEY_SECRET_COS_JSON.type);
        SystemDTO systemDTO = null;
        if (null != json) {
            Response response = Jackson.fromJsonString(String.valueOf(json), Response.class);
            systemDTO = new SystemDTO();
            systemDTO.setTmpSecretId(response.credentials.tmpSecretId);
            systemDTO.setTmpSecretKey(response.credentials.tmpSecretKey);
            systemDTO.setSessionToken(response.credentials.sessionToken);
            systemDTO.setStartTime(response.startTime);
            systemDTO.setExpiredTime(response.expiredTime);
        } else {
            throw new BaseBusinessException(MyEnumMessage.REDIS_NOT_FOUND);
        }
        return systemDTO;
    }
}
