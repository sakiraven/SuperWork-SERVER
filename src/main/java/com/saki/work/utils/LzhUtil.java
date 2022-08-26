package com.saki.work.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class LzhUtil {
    /**
     * 生成精确到分钟的时间戳
     *
     * @return
     */
    public static String getDate(String timeformat) {
        Date date = new Date();
        long timestamp = date.getTime();//时间戳
        SimpleDateFormat format = new SimpleDateFormat(timeformat);
        String timeText = format.format(timestamp);//获得带格式的字符串
        return timeText;
    }

    //自动生成名字（中文）
    public static String getRandomJianHan(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        return ret;
    }

    /**
     * 上传
     *
     * @param httpServletRequest
     * @param file
     * @param mdkir
     * @return
     * @throws Exception
     */
    public static String upLoad(HttpServletRequest httpServletRequest,
                                MultipartFile file,
                                String mdkir) throws Exception {
        String realPath = httpServletRequest.getSession().getServletContext().
                getRealPath("/WEB-INF/" + mdkir + "/");
        //System.out.println(file.getOriginalFilename());
        //String fiex = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = LzhUtil.getDate("yyyy年MM月dd日HH时mm分ss秒") + file.getOriginalFilename();
        File dir = new File(realPath, fileName);
        dir.mkdirs();
        file.transferTo(dir);
        System.out.println(realPath);
        return mdkir + "/" + fileName;
    }


   /* public static void down(HttpServletResponse response,
                     HttpServletRequest request,
                     String workFileName) throws Exception {

        String realPath = request.getSession().getServletContext().
                getRealPath("/WEB-INF/" + workFileName + "/");
        // 读到流中
        InputStream inStream = new FileInputStream(realPath);//文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(workFileName, "UTF-8"));
        // 循环取出流中的数据
        byte[] b = new byte[200];
        int len;

        while ((len = inStream.read(b)) > 0){
            response.getOutputStream().write(b, 0, len);
        }
        inStream.close();

    }*/

    /**
     * 下载
     *
     * @param response
     * @param request
     * @param file_name
     * @throws Exception
     */
    public static void down(String file_name, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getServletContext().getRealPath("/WEB-INF/");
        File file = new File(path + "/" + file_name);
        System.out.println(path + "/" + file_name);
        // 获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(file));
        // 假如以中文名下载的话
        // 转码，免得文件名中文乱码
        file_name = URLEncoder.encode(file_name, "UTF-8");
        // 设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + file_name);
        // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while ((len = bis.read()) != -1) {
            out.write(len);
            out.flush();
        }
        out.close();
        bis.close();

    }

    /**
     * 删除单个文件
     *
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + path + "成功！");
            } else {
                System.out.println("删除单个文件" + path + "失败！");
            }
        } else {
            System.out.println("删除单个文件失败：" + path + "不存在！");
        }
    }

    /**
     * 删除末尾的字符
     *
     * @param value
     * @param chars
     * @return
     */
    public static String deleteLastChars(String value, String chars) {
        if (StringUtils.isNotBlank(value)) {
            int lastIndex = value.lastIndexOf(chars);
            value = value.substring(0, lastIndex);
        }
        return value;
    }

    /**
     * 获取调用此方法的向上两级方法名
     *
     * @return
     */
    public static String getMethodNameByUpTwoLevel() {
        return Thread.currentThread().getStackTrace()[4].getMethodName();
    }

    /**
     * 获取调用此方法的向上一级方法名
     *
     * @return
     */
    public static String getMethodNameByUpOneLevel() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    /**
     * 获取调用此方法的向上两级类名
     *
     * @return
     */
    public static String getClassNameByUpTwoLevel() {
        return Thread.currentThread().getStackTrace()[4].getMethodName();
    }

    /**
     * 获取调用此方法的向上一级类名
     *
     * @return
     */
    public static String getClassNameByUpOneLevel() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    //生成随机用户名，数字和字母组成,
    public static String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    public static void main(String[] args) {
        System.out.println();
    }

    /**
     * 获取与目标时间的剩余时间字符串
     *
     * @param expirationTime
     * @return
     */
    public static String dateToRemainingString(Date expirationTime) {
        Long time = expirationTime.getTime() - System.currentTimeMillis();
        String timeStr = null;
        long hour = 0;
        long minute = 0;
        long second = 0;
        long millisecond = 0;
        if (time <= 0) {
            return "00:00:00";
        } else {
            second = time / 1000;
            minute = second / 60;
            millisecond = time % 1000;
            if (second < 60) {
                timeStr = "00:00:" + unitFormat(second);
            } else if (minute < 60) {
                second = second % 60;
                timeStr = "00:" + unitFormat(minute) + ":" + unitFormat(second);
            } else {// 数字>=3600 000的时候
                hour = minute / 60;
                minute = minute % 60;
                second = second - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    private static String unitFormat(long i) {// 时分秒的格式转换
        String retStr = null;
        if (i >= 0 && i < 10) {
            retStr = "0" + Long.toString(i);
        } else {
            retStr = "" + i;
        }

        return retStr;
    }

    private static String unitFormat2(long i) {// 毫秒的格式转换
        String retStr = null;
        if (i >= 0 && i < 10) {
            retStr = "00" + Long.toString(i);
        } else if (i >= 10 && i < 100) {
            retStr = "0" + Long.toString(i);
        } else {
            retStr = "" + i;
        }
        return retStr;
    }

    /**
     * 获取文件系统名称
     *
     * @return
     */
    private String getOSName() {
        return System.getProperty("os.name");
    }

    /**
     * 获得当天零时零分零秒
     *
     * @return
     */
    public static Date dateGetTodayZero() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


    /**
     * 下载图片
     *
     * @param urlString
     * @return 相对路径
     */
    public static String downloadPicture(String saveDirPath, String urlString) throws Exception {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.50.148", 7890));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        saveDirPath = saveDirPath + simpleDateFormat.format(date);
        File file = new File(saveDirPath);
        if (!file.exists()){
            file.mkdirs();
        }

        String imgName = UUID.randomUUID().toString() + ".png";

        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(urlString).openConnection(proxy);
        BufferedInputStream bis = new BufferedInputStream(urlConnection.getInputStream());
//        DataInputStream dataInputStream = new DataInputStream(url.openStream());


        String imageName = saveDirPath + "/" + imgName;
        File dirFile = new File(saveDirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int length;

        while ((length = bis.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        byte[] context = output.toByteArray();
        fileOutputStream.write(output.toByteArray());
//        dataInputStream.close();
        bis.close();
        fileOutputStream.close();

        return imageName;
    }
}
