package com.saki.work.system.service;

import com.saki.work.system.module.dto.SystemDTO;

public interface COSService {
    /**
     * 获取临时密钥
     *
     * @return
     */
    SystemDTO secretTemp();
}
