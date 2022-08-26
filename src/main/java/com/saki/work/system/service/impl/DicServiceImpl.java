package com.saki.work.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.saki.work.common.global.exception.capture.BaseBusinessException;
import com.saki.work.common.redis.RedisUtil;
import com.saki.work.myenum.MyEnumRedisKey;
import com.saki.work.system.mapper.DicMapper;
import com.saki.work.system.module.dto.DicDTO;
import com.saki.work.system.module.po.DicPO;
import com.saki.work.system.service.DicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.MessageFormat;

/**
 * (Dic)表服务实现类
 *
 * @author lzh
 * @since 2021-08-03 14:43:57
 */
@Service("dicService")
public class DicServiceImpl extends ServiceImpl<DicMapper, DicPO> implements DicService {
    @Resource
    private DicMapper dicMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public DicDTO getByPK(String key) {
        String redisKey = MessageFormat.format(MyEnumRedisKey.TYPE_STRING_KEY_SYSTEM_DIC_0.type, key);
        Object value = this.redisUtil.getValue(redisKey);
        DicDTO reDicDTO = new DicDTO();
        if (value == null) {
            DicPO dicPO = this.getById(key);
            if (dicPO == null) {
                BaseBusinessException.throwRunTimeException();
            }
            BeanUtils.copyProperties(dicPO, reDicDTO);
            redisUtil.setValueTimeout(redisKey, new Gson().toJson(reDicDTO), RedisUtil.oneDayExpireTime);
        } else {
            reDicDTO = new Gson().fromJson((String) value, DicDTO.class);
        }

        return reDicDTO;
    }

    @Override
    public IPage listPage(DicDTO dicDTO) {
        Page<DicPO> dicPOPage = new Page<>(dicDTO.getPage(), dicDTO.getPageSize());
        Page<DicPO> page = this.page(dicPOPage);
        return page;
    }

    @Override
    public void delete(Serializable id) {
        this.removeById(id);
    }

}