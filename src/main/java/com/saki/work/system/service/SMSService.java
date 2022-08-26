package com.saki.work.system.service;


import com.saki.work.system.module.dto.SMSDTO;

public interface SMSService {

    /**
     * 发送验证码
     * @param smsDTO
     */
    void sendCode(SMSDTO smsDTO);

    /**
     * 校验手机验证码
     * @param phone
     * @param phoneCode
     * @return exist
     */
    Boolean verifyCode(String phone, String phoneCode);
}
