package com.saki.work.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author lzh
 * @date 2020/2/2 - 20:13
 */
public class ImgTargetUtil {
    /**
     * 为图片添加图片水印
     *
     * @param watermarkUrl 原图
     * @param source       水印图片
     * @param output       制作完成的图片
     * @return
     * @throws IOException
     */
    public static String markImgMark(String watermarkUrl, String source, String output) throws IOException {
        String result = "添加图片水印出错";
        File file = new File(source);
        Image img = ImageIO.read(file);
        int width = img.getWidth(null);//水印宽度
        int height = img.getHeight(null);//水印高
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        ImageIcon imgIcon = new ImageIcon(watermarkUrl);
        Image con = imgIcon.getImage();
        float clarity = 0.6f;//透明度
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, clarity));
        g.drawImage(con, 10, 10, null);//水印的位置
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        g.dispose();
        File sf = new File(output);
        ImageIO.write(bi, "jpg", sf); // 保存图片
        System.out.println("添加图片水印成功");
        return result;
    }

    /**
     * 设置文字水印
     *
     * @param sourceImg 源图片路径
     * @param targetImg 保存的图片路径
     * @param watermark 水印内容
     * @param color     水印颜色
     * @throws IOException
     */
    public static void addWatermark(String sourceImg, String targetImg, String watermark, Color color) throws IOException {
        File srcImgFile = new File(sourceImg);
        Image srcImg = ImageIO.read(srcImgFile);
        int srcImgWidth = srcImg.getWidth(null);
        int srcImgHeight = srcImg.getHeight(null);
        Double fontSizeDou = (srcImgWidth * 0.03);
        Integer fontSizeInt = fontSizeDou.intValue();
        Font font = new Font("04b_08", Font.PLAIN, fontSizeInt);//字体
        BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufImg.createGraphics();
        g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
        g.setColor(color);
        g.setFont(font);
        //设置水印的坐标
        int x = srcImgWidth - (g.getFontMetrics(g.getFont()).charsWidth(watermark.toCharArray(), 0, watermark.length()) + 15);
        int y = srcImgHeight - 25;
        g.drawString(watermark, x, y);  //加水印
        g.dispose();
        // 输出图片
        FileOutputStream outImgStream = new FileOutputStream(targetImg);
        ImageIO.write(bufImg, "jpg", outImgStream);
        System.out.println("添加水印完成");
        outImgStream.flush();
        outImgStream.close();
    }
}
