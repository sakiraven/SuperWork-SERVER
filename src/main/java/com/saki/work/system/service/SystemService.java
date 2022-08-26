package com.saki.work.system.service;

import com.saki.work.system.module.dto.SystemDTO;

import java.io.ByteArrayOutputStream;

public interface SystemService {
    /**
     * 文件上传
     *
     * @param systemDTO
     * @return
     */
    SystemDTO uploadSingle(SystemDTO systemDTO);

    /**
     * 文件上传
     *
     * @param systemDTO
     * @return
     */
    SystemDTO uploadArray(SystemDTO systemDTO);

    /**
     * 获取验证码
     * @param uuid
     * @return
     */
    ByteArrayOutputStream getCodeImg(String uuid);

    /**
     * ios版本号
     * @return
     */
    SystemDTO versionIOS();

    SystemDTO versionAndroid();

    /**
     * 获取系统图片
     * @param path
     * @return
     */
    ByteArrayOutputStream getImage(String path);
}
