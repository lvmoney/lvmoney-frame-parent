package com.lvmoney.frame.ai.orc.base;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.orc.client
 * 版本信息: 版本1.0
 * 日期:2022/3/2
 * Copyright XXXXXX科技有限公司
 */


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/3/2 20:24
 */
public class TesseractTest {
    private ITesseract tesseract;

    @Before
    public void init() {
        tesseract = new Tesseract();
        tesseract.setLanguage("eng"); // 默认就是eng，你可以选择其他lang
        tesseract.setDatapath("E:\\work\\lvmoney\\code\\lvmoney-frame-parent\\lvmoney-frame-ai\\lvmoney-frame-ai-ocr\\lvmoney-frame-ai-ocr-client\\src\\main\\resources\\traineddata");
        System.out.println("tesseract init done...");
    }

    @Test
    public void testOcr() throws IOException, TesseractException {
        File file=new File("D:\\face\\test200.png");
        BufferedImage image = ImageIO.read(file);
        String ocr = tesseract.doOCR(image);
        System.out.println("ocr result : " + ocr);
    }
}
