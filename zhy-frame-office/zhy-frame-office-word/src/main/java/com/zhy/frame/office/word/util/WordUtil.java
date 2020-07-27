package com.zhy.frame.office.word.util;


import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.NumbericRenderData;
import com.deepoove.poi.template.ElementTemplate;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.core.util.FileUtil;
import com.zhy.frame.core.util.MapUtil;
import com.zhy.frame.core.util.SnowflakeIdFactoryUtil;
import com.zhy.frame.office.common.constant.OfficeConstant;
import com.zhy.frame.office.common.exception.OfficeException;
import com.zhy.frame.office.word.enums.WTemplateEnum;
import com.zhy.frame.office.word.vo.*;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhy.frame.base.core.constant.BaseConstant.*;
import static com.zhy.frame.office.common.constant.OfficeConstant.*;

/**
 * @describe：word操作工具类。html转pdf的效果不好，word转html效果一般
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class WordUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(WordUtil.class);

    public static void main(String[] args) throws Exception {
//        docx2Html("D:\\data\\activiti.docx", "D:\\data", "1");
//        doc2Html("D:\\data\\DDD.doc", "D:\\data", "2");
    }

    /**
     * doc 转html
     *
     * @param source:     源文件整个路径
     * @param targetPath: 目标文件目录
     * @param targetName: 目标文件的名字
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/24 10:59
     */

    public static String doc2Html(String source, String targetPath, String targetName) {
        String targetFile = targetPath + BACKSLASH + targetName + HTML_SUFFIX;
        String imagePathStr = targetPath + BACKSLASH + DEFAULT_WORD_IMAGE_BASE_PATH + BACKSLASH;
        File file = new File(imagePathStr);
        if (!file.exists()) {
            file.mkdirs();
        }
        HWPFDocument wordDocument = null;
        try {
            wordDocument = new HWPFDocument(new FileInputStream(source));
        } catch (IOException e) {
            LOGGER.error("获得word文件：{}报错:{}", source, e);
        }
        Document document = null;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            LOGGER.error("构造word文件：{}报错:{}", source, e);
        }
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
        //保存图片，并返回图片的相对路径
        wordToHtmlConverter.setPicturesManager((content, pictureType, name, width, height) -> {
            try (FileOutputStream out = new FileOutputStream(imagePathStr + name)) {
                out.write(content);
            } catch (Exception e) {
                LOGGER.error("存储word文件：{}的图片：{}，报错:{}", source, imagePathStr + name, e);
            }
            return DEFAULT_WORD_IMAGE_BASE_PATH + BACKSLASH + name;
        });
        wordToHtmlConverter.processDocument(wordDocument);
        org.w3c.dom.Document htmlDocument = wordToHtmlConverter.getDocument();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(new File(targetFile));
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = null;
        try {
            serializer = tf.newTransformer();
        } catch (TransformerConfigurationException e) {
            LOGGER.error("doc转换html报错:{}", e);
        }
        serializer.setOutputProperty(OutputKeys.ENCODING, CHARACTER_ENCODE_UTF8_LOWER);
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        try {
            serializer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            LOGGER.error("doc转换html报错:{}", e);
        }
        return targetFile;
    }

    /**
     * docx 转html
     *
     * @param source:     源文件整个路径
     * @param targetPath: 目标文件目录
     * @param targetName: 目标文件的名字
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/24 9:23
     */
    public static String docx2Html(String source, String targetPath, String targetName) {
        OutputStreamWriter outputStreamWriter = null;
        String targetFile = targetPath + BACKSLASH + targetName + HTML_SUFFIX;
        try {
            XWPFDocument document = null;
            try {
                document = new XWPFDocument(new FileInputStream(source));
            } catch (IOException e) {
                LOGGER.error("获得word文件：{}报错:{}", source, e);
            }
            XHTMLOptions options = XHTMLOptions.create();
            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File(targetPath + BACKSLASH + DEFAULT_WORD_IMAGE_BASE_PATH)));
            // html中图片的路径
            options.URIResolver(new BasicURIResolver(DEFAULT_WORD_IMAGE_BASE_PATH));
            try {
                outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFile), CHARACTER_ENCODE_UTF8_LOWER);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            try {
                xhtmlConverter.convert(document, outputStreamWriter, options);
            } catch (IOException e) {
                LOGGER.error("存储word文件：{}的图片报错:{}", source, e);
            }
        } finally {
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    LOGGER.error("关闭outputStreamWriter报错:{}", e);
                }
            }
        }
        return targetFile;
    }

    /**
     * 根据模板文件生成word
     *
     * @param templateVo: 模板实体
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:01
     */
    public static boolean template2Word(WordTemplateVo templateVo) {
        String source = templateVo.getSource();
        String target = templateVo.getTarget();
        List<WordStringVo> str = templateVo.getStr();
        List<WordNumbericVo> numberic = templateVo.getNumberic();
        List<WordPictureVo> picture = templateVo.getPicture();
        List<WordTablesVo> table = templateVo.getTable();
        Map<String, Object> datas = new HashMap<String, Object>(BaseConstant.MAP_DEFAULT_SIZE) {
            {
                if (str != null) {
                    str.forEach(v -> {
                        put(v.getKey(), v.getValue());
                    });
                }
                if (numberic != null) {
                    numberic.forEach(v -> {
                        put(v.getKey(), new NumbericRenderData(v.getBody()));
                    });
                }
                if (table != null) {
                    table.forEach(v -> {
                        put(v.getKey(), new MiniTableRenderData(v.getTableHeads(), v.getTablebodys(), v.getDataDesc(), v.getWidth()));
                    });
                }
                if (picture != null) {
                    picture.forEach(v -> {
                        put(v.getKey(), v.getValue());
                    });
                }

            }
        };
        XWPFTemplate template = XWPFTemplate.compile(source)
                .render(datas);
        OutputStream out;
        try {
            out = new FileOutputStream(target);
            template.write(out);
            out.flush();
            out.close();
            template.close();
            return true;
        } catch (FileNotFoundException e) {
            LOGGER.error("word模板文件不存在:{}", e.getMessage());
            throw new BusinessException(OfficeException.Proxy.TEMPLATE_NOT_EXIST);
        } catch (IOException e) {
            LOGGER.error("word模板文件转换成word文件失败:{}", e.getMessage());
            throw new BusinessException(OfficeException.Proxy.TEMPLATE_2_WORD_ERROR);
        }
    }

    /**
     * 根据模板文件生成word
     *
     * @param templateVo: 流文件模板实体
     * @throws
     * @return: com.lvmoney.office.vo.BaseWordByteVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:01
     */
    public static BaseWordByteVo template2Word(WordTemplateByteVo templateVo) {
        byte[] source = templateVo.getSource();
        List<WordStringVo> str = templateVo.getStr();
        List<WordNumbericVo> numberic = templateVo.getNumberic();
        List<WordPictureVo> picture = templateVo.getPicture();
        List<WordTablesVo> table = templateVo.getTable();
        Map<String, Object> datas = new HashMap<String, Object>(MapUtil.initMapSize(16)) {
            {
                if (str != null) {
                    str.forEach(v -> {
                        put(v.getKey(), v.getValue());
                    });
                }
                if (numberic != null) {
                    numberic.forEach(v -> {
                        put(v.getKey(), new NumbericRenderData(v.getBody()));
                    });
                }
                if (table != null) {
                    table.forEach(v -> {
                        put(v.getKey(), new MiniTableRenderData(v.getTableHeads(), v.getTablebodys(), v.getDataDesc(), v.getWidth()));
                    });
                }
                if (picture != null) {
                    picture.forEach(v -> {
                        put(v.getKey(), v.getValue());
                    });
                }

            }
        };
        XWPFTemplate template = XWPFTemplate.compile(FileUtil.byte2Input(source))
                .render(datas);
        OutputStream out;
        SnowflakeIdFactoryUtil idWorker = new SnowflakeIdFactoryUtil(1, 2);
        String tempName = String.valueOf(idWorker.nextId());
        String temp = OfficeConstant.TEMP_FILE_PATH + "/" + tempName + OfficeConstant.TEMP_SUFFIX;
        BaseWordByteVo result = new BaseWordByteVo();
        try {
            out = new FileOutputStream(temp);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (FileNotFoundException e) {
            LOGGER.error("word模板文件不存在:{}", e.getMessage());
            throw new BusinessException(OfficeException.Proxy.TEMPLATE_NOT_EXIST);
        } catch (IOException e) {
            LOGGER.error("word模板文件转换成word文件失败:{}", e.getMessage());
            throw new BusinessException(OfficeException.Proxy.TEMPLATE_2_WORD_ERROR);
        } finally {
            File file = new File(temp);
            InputStream input = null;
            try {
                input = new FileInputStream(file);
                result.setFile(IOUtils.toByteArray(input));
                result.setFileName(file.getName());
            } catch (IOException e) {
                LOGGER.error("模板文件从零时目录中操作报错{}", e.getMessage());
                throw new BusinessException(OfficeException.Proxy.TEMPLATE_2_WORD_ERROR);
            } finally {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error("模板文件从零时目录中操作报错{}", e.getMessage());
                }

            }
            file.delete();
        }
        return result;
    }

    /**
     * 获取模板文件需要填充的字段值
     *
     * @param wSourceVo: 对象
     * @throws
     * @return: java.util.List<com.lvmoney.office.vo.WordTemplateParams>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:02
     */
    public static List<WordTemplateParams> getTemplateParams(WordSourceVo wSourceVo) {
        List<WordTemplateParams> result = new ArrayList<>();
        XWPFTemplate template = XWPFTemplate.compile(wSourceVo.getSource());
        List<ElementTemplate> elementTemplates = template.getElementTemplates();
        elementTemplates.forEach(v -> {
            WTemplateEnum wTemplateEnum = WTemplateEnum.getByValue(String.valueOf(v.getSign()));
            if (wTemplateEnum == null) {
                throw new BusinessException(OfficeException.Proxy.TEMPLATE_ELEMENT_NOT_SUPPORT);
            }
            WordTemplateParams wordTemplateParams = new WordTemplateParams();
            wordTemplateParams.setParamName(v.getTagName());
            wordTemplateParams.setTemplateParam(v.getSource());
            wordTemplateParams.setWTemplateEnum(wTemplateEnum);
            result.add(wordTemplateParams);
        });
        return result;
    }

}
