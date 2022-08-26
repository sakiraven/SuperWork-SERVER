package com.saki.work.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtil {

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]";
    public static final String GB_CODE = "[^_IOZSVa-z\\W]{2}\\d{6}[^_IOZSVa-z\\W]{10}";

    public static final String TIME_ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_M_M_D = "yyyy-MM-dd";
    public static final String TIME_Y_M_D_H_M = "yyyy-MM-dd HH:mm";

    /**
     * InputStream to byte[]
     *
     * @param ips
     * @return
     */
    public static byte[] stream2byteArray(InputStream ips) {
        byte[] buff = null;
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        int tmp = 0;
        try {
            while ((tmp = ips.read()) != -1) {
                bops.write(tmp);
            }
            buff = bops.toByteArray();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (bops != null)
                    bops.close();
            } catch (IOException e) {
            }
            try {
                if (ips != null)
                    ips.close();
            } catch (IOException e) {
            }
        }
        return buff;
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);//[0,62)

            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    public static boolean isGBCode(String idCard) {
        return Pattern.matches(GB_CODE, idCard);
    }

    public static boolean isEarly(String start, String end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date stime = sdf.parse(start);
            Date etime = sdf.parse(end);

            long startlong = stime.getTime();
            long endlong = etime.getTime();

            if (startlong > endlong) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    static String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    private final static Pattern emailer = Pattern.compile(str);

    private final static SimpleDateFormat dateFormater = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat dateFormater2 = new SimpleDateFormat(
            "yyyy-MM-dd");

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 以友好的方式显示时间
     *
     * @param time
     * @return
     */
    public static String friendly_time(Date time) {
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater2.format(cal.getTime());
        String paramDate = dateFormater2.format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days <= 10) {
            ftime = days + "天前";
        } else if (days > 10) {
            ftime = dateFormater2.format(time);
        }
        return ftime;
    }

    private static final NavigableMap<Long, String> suffixes = new TreeMap<> ();
    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }

    public static String numberFormat(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return numberFormat(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + numberFormat(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    /**
     * 判断给定字符串时间是否为今日
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.format(today);
            String timeDate = dateFormater2.format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 字符串转布尔值
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    // 字符串替换
    public static String replace(String strSource, String strFrom, String strTo) {
        if (strSource == null) {
            return null;
        }
        int i = 0;
        if ((i = strSource.indexOf(strFrom, i)) >= 0) {
            char[] cSrc = strSource.toCharArray();
            char[] cTo = strTo.toCharArray();
            int len = strFrom.length();
            StringBuffer buf = new StringBuffer(cSrc.length);
            buf.append(cSrc, 0, i).append(cTo);
            i += len;
            int j = i;
            while ((i = strSource.indexOf(strFrom, i)) > 0) {
                buf.append(cSrc, j, i - j).append(cTo);
                i += len;
                j = i;
            }
            buf.append(cSrc, j, cSrc.length - j);
            return buf.toString();
        }
        return strSource;
    }

    /**
     * 判断是否是手机号码
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;

        String expression = "^((1[3,4,5,7,8,9]{1}[0-9]{1})+\\d{8})$";

        CharSequence inputStr = phoneNumber;

        Pattern pattern = Pattern.compile(expression);

        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * 描述：标准化日期时间类型的数据，不足两位的补0.
     *
     * @param dateTime 预格式的时间字符串，如:2012-3-2 12:2:20
     * @return String 格式化好的时间字符串，如:2012-03-20 12:02:20
     */
    public static String dateTimeFormat(String dateTime) {
        StringBuilder sb = new StringBuilder();
        try {
            if (isEmpty(dateTime)) {
                return null;
            }
            String[] dateAndTime = dateTime.split(" ");
            if (dateAndTime.length > 0) {
                for (String str : dateAndTime) {
                    if (str.indexOf("-") != -1) {
                        String[] date = str.split("-");
                        for (int i = 0; i < date.length; i++) {
                            String str1 = date[i];
                            sb.append(strFormat2(str1));
                            if (i < date.length - 1) {
                                sb.append("-");
                            }
                        }
                    } else if (str.indexOf(":") != -1) {
                        sb.append(" ");
                        String[] date = str.split(":");
                        for (int i = 0; i < date.length; i++) {
                            String str1 = date[i];
                            sb.append(strFormat2(str1));
                            if (i < date.length - 1) {
                                sb.append(":");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }

    /**
     * 描述：不足2个字符的在前面补“0”.
     *
     * @param str 指定的字符串
     * @return 至少2个字符的字符串
     */
    public static String strFormat2(String str) {
        try {
            if (str.length() <= 1) {
                str = "0" + str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    public static boolean isLetter(char c) {
        // char c = fstrData.charAt(0);
        if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isChinese(String c) {
        Boolean is_chinese = false;
        for (int i = 0; i < c.length(); i++) {
            String test = c.substring(i, i + 1);
            System.out.println(test);
            if (test.matches("[\\u4E00-\\u9FA5]+")) {
                is_chinese = true;
            } else {
                is_chinese = false;
                break;
            }
        }

        return is_chinese;
    }

    /**
     * 提取出城市或者县
     *
     * @param city
     * @param district
     * @return
     */
    public static String extractLocation(final String city, final String district) {
        return district.contains("县") ? district.substring(0, district.length() - 1) : city.substring(0, city.length() - 1);
    }

    /**
     * Md5 32位 or 16位 加密
     *
     * @param plainText
     * @return 32位加密
     */
    public static String Md5(String plainText) {
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    /**
     * 手机号正则判断
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isPhoneNumber(String str) throws PatternSyntaxException {
        if (str != null) {
            String pattern = "(13\\d|14[579]|15[^4\\D]|17[^49\\D]|18\\d)\\d{8}";

            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(str);
            return m.matches();
        } else {
            return false;
        }
    }

    /**
     * 时间戳
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

    /**
     * 距离0点剩余秒数
     *
     * @return
     */
    public static Long getDistance12ClockReSecond() {
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // System.out.println("当前时间：" + df.format(calendar.getTime()));
        // 将日期调整为次日零时，即当前日期加一天，时、分、秒、毫秒都置零。
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        //  System.out.println("次日零时：" + df.format(calendar.getTime()));
        //  System.out.println("从现在到凌晨还剩余：" + (calendar.getTimeInMillis() - System.currentTimeMillis()) / 1000 + " s");
        long distance12Second = (calendar.getTimeInMillis() - System.currentTimeMillis()) / 1000;
        return distance12Second;
    }

    /**
     * 获取resources路径
     *
     * @return
     */
    public static String getUrl(String url) {
        String uploadShortTimePath = StringUtil.class.getClassLoader().getResource(url).getPath();
        uploadShortTimePath = uploadShortTimePath.substring(uploadShortTimePath.indexOf("/") + 1, uploadShortTimePath.length());
        return uploadShortTimePath;
    }
    /*public static String getUrl(String url) {
        return "//home/tomcat-jar/resources/" + url;
    }*/

    /**
     * 保留x位小数
     *
     * @param value 值
     * @param size  保留x位小数
     * @return
     */
    public static Integer keepDecimalPlaces(double value, Integer size) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(size, RoundingMode.HALF_UP);
        return bd.intValue();
    }

    /**
     * 获取两个日期之间的所有日期（yyyy-MM-dd）
     *
     * @param begin
     * @param end
     * @return
     * @Description
     * @author XuJD
     * @date 2017-4-1
     */
    public static List<Date> getBetweenDates(Date begin, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(begin);
//        Calendar tempEnd = Calendar.getInstance();
//        tempStart.add(Calendar.DAY_OF_YEAR, 1);
//        tempEnd.setTime(end);
//        while (tempStart.before(tempEnd)) {
//            result.add(tempStart.getTime());
//            tempStart.add(Calendar.DAY_OF_YEAR, 1);
//        }
        while (begin.getTime() <= end.getTime()) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            begin = tempStart.getTime();
        }
        return result;
    }

    /**
     * 获取两个日期之间的所有日期（yyyy-MM-dd）
     *
     * @param begin
     * @param end
     * @return
     * @Description
     * @author XuJD
     * @date 2017-4-1
     */
    public static List<String> getBetweenDates(Date begin, Date end, String format) {
        List<String> result = new ArrayList<String>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(begin);
//        Calendar tempEnd = Calendar.getInstance();
//        tempStart.add(Calendar.DAY_OF_YEAR, 1);
//        tempEnd.setTime(end);
//        while (tempStart.before(tempEnd)) {
//            result.add(tempStart.getTime());
//            tempStart.add(Calendar.DAY_OF_YEAR, 1);
//        }
        while (begin.getTime() <= end.getTime()) {
            result.add(new SimpleDateFormat(format).format(tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            begin = tempStart.getTime();
        }
        return result;
    }

    /**
     * 获取两个日期之间的所有日期（yyyy-MM-dd）
     *
     * @param begin
     * @param end
     * @return
     * @Description
     * @author XuJD
     * @date 2017-4-1
     */
    public static List<String> getBetweenDatesDesc(Date begin, Date end, String format) {
        List<String> dateFormatList = new ArrayList<String>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(begin);
//        Calendar tempEnd = Calendar.getInstance();
//        tempStart.add(Calendar.DAY_OF_YEAR, 1);
//        tempEnd.setTime(end);
//        while (tempStart.before(tempEnd)) {
//            result.add(tempStart.getTime());
//            tempStart.add(Calendar.DAY_OF_YEAR, 1);
//        }
        while (begin.getTime() <= end.getTime()) {
            dateFormatList.add(new SimpleDateFormat(format).format(tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            begin = tempStart.getTime();
        }

        Collections.reverse(dateFormatList);
        return dateFormatList;
    }
}
