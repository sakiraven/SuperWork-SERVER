package com.saki.work.system.service.impl;

//import com.saki.work.common.fastdfs.FastDFSClient;

import com.saki.work.common.redis.RedisUtil;
import com.saki.work.myenum.MyEnumDicKey;
import com.saki.work.myenum.MyEnumRedisKey;
import com.saki.work.system.module.dto.DicDTO;
import com.saki.work.system.module.dto.SystemDTO;
import com.saki.work.system.service.DicService;
import com.saki.work.system.service.RedisService;
import com.saki.work.system.service.SystemService;
import com.saki.work.system.service.UserInfoService;
import com.saki.work.utils.VerifyCodeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;

@Log4j2
@Service("systemService")
public class SystemServiceImpl implements SystemService {
//    @Resource
//    private FastDFSClient fastDFSClient;
    @Resource
    private RedisService redisService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private DicService dicService;

    @Override
    public SystemDTO uploadSingle(SystemDTO systemDTO) {
//        String fdfsRelativePath = null;
//        UserInfoDTO loginUserInfoDTO = this.userInfoService.getLoginUserInfoDTO();
//        try {
//            String extName = systemDTO.getFile().getOriginalFilename().substring(systemDTO.getFile().getOriginalFilename().lastIndexOf(".") + 1);
//            fdfsRelativePath = this.fastDFSClient.uploadFile(systemDTO.getFile().getBytes(), extName);
//            if (fdfsRelativePath == null) {
//                // 上传失败
//                throw new BaseBusinessException(MyEnumMessage.UPLOAD_FILE_ERROR);
//            }
//
//            log.warn(MessageFormat.format("用户名{0},用户id{1},文件大小{2}byte,文件相对路径{3}", loginUserInfoDTO.getNick(), loginUserInfoDTO.getId(), systemDTO.getFile().getSize(), fdfsRelativePath));
//            this.addFileUploadRedis(fdfsRelativePath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        SystemDTO returnSystemDTO = new SystemDTO();
//        returnSystemDTO.setFdfsRelativePath(fdfsRelativePath);
        return returnSystemDTO;
    }

    @Override
    public SystemDTO uploadArray(SystemDTO systemDTO) {
        ArrayList<String> fdfsRelativePath = new ArrayList<>();
        for (MultipartFile multipartFile : systemDTO.getFileList()) {
            SystemDTO singleSystemDTO = new SystemDTO().builder()
                    .file(multipartFile)
                    .build();
            SystemDTO urlSystemDTO = this.uploadSingle(singleSystemDTO);
            if (null != urlSystemDTO) {
                fdfsRelativePath.add(urlSystemDTO.getFdfsRelativePath());
            }
        }
        return new SystemDTO().builder()
                .fdfsRelativePathList(fdfsRelativePath)
                .build();
    }

    @Override
    public ByteArrayOutputStream getCodeImg(String uuid) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String code = VerifyCodeUtil.drawImage(output);
        String redisKey = MessageFormat.format(MyEnumRedisKey.TYPE_STRING_KEY_SYSTEM_VERIFY_IMG_CODE_0_1.type, uuid, code);
        this.redisUtil.setValueTimeout(redisKey, code, RedisUtil.twoMinutesExpireTime);
        return output;
    }

    @Override
    public SystemDTO versionIOS() {
        DicDTO dicDTO = this.dicService.getByPK(MyEnumDicKey.VERSION_IOS.key);
        SystemDTO systemDTO = new SystemDTO();
        systemDTO.setVersion(dicDTO.getDescribe());
        return systemDTO;
    }

    @Override
    public SystemDTO versionAndroid() {
        DicDTO dicDTO = this.dicService.getByPK(MyEnumDicKey.VERSION_IOS.key);
        SystemDTO systemDTO = new SystemDTO();
        systemDTO.setVersion(dicDTO.getDescribe());
        return systemDTO;
    }

    @Override
    public ByteArrayOutputStream getImage(String path) {
        ByteArrayOutputStream baos = null;
        try {
            File imageFile = new File(path);
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return baos;
    }
}
