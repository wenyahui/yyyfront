/*
 * 文件名：QrcodeUtil.java
 * 版权：Copyright by www.taohuakeji.com
 * 描述：
 * 修改人：ying
 * 修改时间：2017-5-5
 */

package com.bootdo.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * 二维码工具类
 * @author ying
 * @version 2017-5-5
 * @see QrcodeUtil
 * @since
 */

public class QrcodeUtil
{
    public static String createQrcode(String dir, String _text) {
        String qrcodeFilePath = "";
        try {
            int qrcodeWidth = 300;
            int qrcodeHeight = 300;
            String qrcodeFormat = "png";
            HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(_text, BarcodeFormat.QR_CODE, qrcodeWidth,
                    qrcodeHeight, hints);

            BufferedImage image = new BufferedImage(qrcodeWidth, qrcodeHeight, BufferedImage.TYPE_INT_RGB);
            File qrcodeFile = new File(dir + "/" + UUID.randomUUID().toString() + "." + qrcodeFormat);
            ImageIO.write(image, qrcodeFormat, qrcodeFile);
            MatrixToImageWriter.writeToPath(bitMatrix, qrcodeFormat, qrcodeFile.toPath());
            qrcodeFilePath = qrcodeFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qrcodeFilePath;
    }

    public static void createQrcode(String text,HttpServletResponse response) throws IOException{
        ServletOutputStream stream = null;
        try {

            int width = 325;//图片的宽度
            int height = 325;//高度
            stream = response.getOutputStream();
            HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 1+"");
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix m = writer.encode(text, BarcodeFormat.QR_CODE, height, width,hints);
            MatrixToImageWriter.writeToStream(m, "png", stream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
    public static String decodeQr(String filePath) {
        String retStr = "";
        if ("".equalsIgnoreCase(filePath) && filePath.length() == 0) {
            return "图片路径为空!";
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(filePath));
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap bitmap = new BinaryBitmap(binarizer);
            HashMap<DecodeHintType, Object> hintTypeObjectHashMap = new HashMap<>();
            hintTypeObjectHashMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(bitmap, hintTypeObjectHashMap);
            retStr = result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retStr;
    }
    
    public static void main(String[] args)
    {
        createQrcode("D:\\","https://www.baidu.com/");
    }
}
