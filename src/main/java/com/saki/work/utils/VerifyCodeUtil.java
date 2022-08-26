package com.saki.work.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class VerifyCodeUtil {

    private static final Random random = new Random();
    private static final String[] fontNames = {"宋体", "华文楷体", "黑体", "Georgia", "微软雅黑", "楷体_GB2312"};

    public static String drawImage(ByteArrayOutputStream output) {
        String code = "";
        int width = 65;
        int height = 25;

        //创建图片缓冲区
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        Graphics2D g = bi.createGraphics();

        //设置背景颜色
        g.setBackground(new Color(255, 255, 255));
        g.clearRect(0, 0, width, height);

        StringBuilder stringBuilder = new StringBuilder();
        //这里只画入四个字符
        for (int i = 0; i < 6; i++) {
            String s = randomChar() + "";      //随机生成字符，因为只有画字符串的方法，没有画字符的方法，所以需要将字符变成字符串再画
            stringBuilder.append(s);           //添加到StringBuilder里面
            float x = i * 1.0F * width / 6;   //定义字符的x坐标
            g.setFont(randomFont());           //设置字体，随机
            g.setColor(randomColor());         //设置颜色，随机
            g.drawString(s, x, height - 5);
        }
        code = stringBuilder.toString();//获取验证码字符串

        //定义干扰线
        //定义干扰线的数量（3-5条）int num = random.nextInt(max)%(max-min+1) + min;
        int num = random.nextInt(5) % 3 + 3;
        Graphics2D graphics = (Graphics2D) bi.getGraphics();
        for (int i = 0; i < num; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            graphics.setColor(randomColor());
            graphics.drawLine(x1, y1, x2, y2);
        }
        // 释放图形上下文
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;//为了方便取值，直接返回code，



    }

    //随机字体
    private static Font randomFont() {
        int index = random.nextInt(fontNames.length);
        String fontName = fontNames[index];
        int style = random.nextInt(4);         //随机获取4种字体的样式
        int size = random.nextInt(20) % 6 + 15;    //随机获取字体的大小(10-20之间的值)
        return new Font(fontName, style, size);
    }

    //随机颜色
    private static Color randomColor() {
        int r = random.nextInt(225);
        int g = random.nextInt(225);
        int b = random.nextInt(225);
        return new Color(r, g, b);
    }


    //随机字符
    private static char randomChar() {
        //A-Z,a-z,0-9,可剔除一些难辨认的字母与数字
        String str = "0123456789";

        return str.charAt(random.nextInt(str.length()));
    }

}
