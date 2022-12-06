package com.lvmoney.frame.office.aspose.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.office.aspose.util
 * 版本信息: 版本1.0
 * 日期:2022/3/4
 * Copyright XXXXXX科技有限公司
 */


import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import com.lvmoney.frame.core.util.FileUtil;
import org.apache.commons.io.IOUtils;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/3/4 16:54
 */
public class AsposeUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsposeUtil.class);

    /**
     * word转pdf
     *
     * @param sourceFile:
     * @param targetFile:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/3/4 17:06
     */
    public static void word2pdf(String sourceFile, String targetFile) {

        File pdfFile = new File(targetFile);
        try (FileOutputStream os = new FileOutputStream(pdfFile);) {
            //sourcerFile是将要被转化的word文档
            Document doc = new Document(sourceFile);
            //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            LOGGER.error("word:{}转pdf:{}报错:{}", sourceFile, targetFile, e);
        }
    }

    /**
     * word转pdf
     *
     * @param sourceFile:
     * @param targetFile:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/3/4 17:26
     */
    public static void word2pdf(byte[] sourceFile, String targetFile) {
        File pdfFile = new File(targetFile);
        try (FileOutputStream os = new FileOutputStream(pdfFile);) {
            InputStream inputStream = FileUtil.byte2Input(sourceFile);
            //sourcerFile是将要被转化的word文档
            Document doc = new Document(inputStream);
            //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            LOGGER.error("word:{}转pdf:{}报错:{}", sourceFile, targetFile, e);
        }
    }

    /**
     * @param inputStream:
     * @throws
     * @return: byte[]
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/8/2 9:52
     */
    public static byte[] word2pdf(InputStream inputStream) {
        try {
            OutputStream outputStream = new ByteArrayOutputStream();
            Document doc = new Document(inputStream);
            doc.save(outputStream, SaveFormat.PDF);
            ByteArrayInputStream parse = FileUtil.parse(outputStream);
            byte[] result = IOUtils.toByteArray(parse);
            return result;
        } catch (Exception e) {
            LOGGER.error("word文件流转换成pdf二进制流失败:{}", e);
            return null;
        }
    }


    public static void main(String[] args) throws IOException {
        String inputJpgPath = "D:/data/word/test.docx";
        String outputWebpPath = "D:/data/word/test4_.pdf";
        File file = new File(inputJpgPath);
        InputStream stream = new FileInputStream(file);
        File outFile = new File(outputWebpPath);
        OutputStream os = new FileOutputStream(outFile);
        BufferedOutputStream bos = new BufferedOutputStream(os);
        byte[] isToByte = word2pdf(stream);
        bos.write(isToByte);


    }

}
