package com.saki.work.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author lzh
 * @date 2020/9/14 - 10:16
 */
public class FileUtils {
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 复制文件
     *
     * @param srcPathStr
     * @param desPathStr
     * @return true 成功
     */
    public static boolean copy(String srcPathStr, String desPathStr) {
        //获取源文件的名称
        logger.info("源文件:" + srcPathStr);
        logger.info("目标文件地址:" + desPathStr);
        FileInputStream fileInputStream = null;//创建输入流对象
        FileOutputStream fileOutputStream = null; //创建输出流对象
        try {
            if (!new File(srcPathStr).exists()) {
                return false;
            }
            fileInputStream = new FileInputStream(srcPathStr);//创建输入流对象
            fileOutputStream = new FileOutputStream(desPathStr); //创建输出流对象
            byte datas[] = new byte[1024 * 8];//创建搬运工具
            int len = 0;//创建长度
            while ((len = fileInputStream.read(datas)) != -1)//循环读取数据
            {
                fileOutputStream.write(datas, 0, len);
            }
        } catch (Exception e) {
            logger.info("复制文件失败", e);
            return false;
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();//释放资源
                }
                if (fileInputStream != null) {
                    fileInputStream.close();//释放资源
                }
            } catch (Exception ex) {
                logger.info("关闭流异常", ex);
            }
        }
        return true;
    }

    /**
     * 读取文本
     *
     * @param fileName
     * @return
     */
    public static String readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    /**
     * 文件夹下只删一层文件  例如C:\Users\Clown-MZ\Desktop\fsdownload\20200805 20200805下不能有文件夹
     *
     * @param fileGroupPath
     */
    public static void deleteDirectory(String fileGroupPath) {
        File file = new File(fileGroupPath);
        if (file.isFile()) {
            file.delete();//清理文件
        } else {
            File list[] = file.listFiles();
            if (list != null) {
                for (File f : list) {
                    deleteDirectory(f.getPath());
                }
                file.delete();//清理目录
            }
        }
    }
}
