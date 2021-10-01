package com.lvmoney.frame.base.core.util;/**
 * 描述:
 * 包名:com.lvmoney.platform.pay.wechat.util
 * 版本信息: 版本1.0
 * 日期:2021/7/21
 * Copyright XXXXXX科技有限公司
 */


import cn.hutool.core.util.StrUtil;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.json.JsonXMLOutputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/21 11:19
 */
public class XmlUtil {
    /**
     * 空格换行正则表达式
     */
    private static final String PATTERN_EMPTY = "\\s*|\t|\r|\n";
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtil.class);
    /**
     * 默认截取长度
     */
    private static final Integer DEFAULT_SUBSTRING_INDEX = 38;

    /**
     * 后缀 xml
     */
    private static final String DEFAULT_XML_SUFFIX = "xml";

    /**
     * xml转json
     *
     * @param xmlString:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/21 11:31
     */
    public static String xml2Json(String xmlString) {
        JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true).autoPrimitive(true).prettyPrint(true).build();
        try (StringReader input = new StringReader(xmlString);
             StringWriter output = new StringWriter()) {
            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(input);
            XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);
            writer.add(reader);
            return output.toString();
        } catch (Exception e) {
            LOGGER.error("xml转json报错:{}", e);
            return null;
        }

    }

    /**
     * json转xml
     *
     * @param jsonString:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/21 11:31
     */
    public static String json2Xml(String jsonString) {
        JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false).repairingNamespaces(false).build();
        try (StringReader input = new StringReader(jsonString);
             StringWriter output = new StringWriter()) {
            XMLEventReader reader = new JsonXMLInputFactory(config).createXMLEventReader(input);
            XMLEventWriter writer = XMLOutputFactory.newInstance().createXMLEventWriter(output);
            writer = new PrettyXMLEventWriter(writer);
            writer.add(reader);
            if (output.toString().length() >= DEFAULT_SUBSTRING_INDEX) {
                return output.toString().substring(DEFAULT_SUBSTRING_INDEX + 1);
            }
            return output.toString();
        } catch (Exception e) {
            LOGGER.error("json转报错:{}", e);
            return null;
        }

    }

    /**
     * 去掉xml中的换行和空格
     *
     * @param jsonString:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/21 11:32
     */
    public static String json2XmlReplaceBlank(String jsonString) {
        String str = json2Xml(jsonString);
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile(PATTERN_EMPTY);
            Matcher m = p.matcher(str);
            dest = m.replaceAll(BaseConstant.EMPTY);
        }
        return dest;
    }

    /**
     * XML格式字符串转换为Map
     *
     * @param xmlStr:
     * @throws
     * @return: java.util.Map<java.lang.String, java.lang.String>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/19 16:35
     */
    public static Map<String, String> xml2Map(String xmlStr) {
        Map<String, String> map = new TreeMap<>();
        if (StrUtil.isBlank(xmlStr)) {
            return null;
        } else {
            Document document = cn.hutool.core.util.XmlUtil.parseXml(xmlStr);
            Element element = document.getDocumentElement();
            if (element != null) {
                NodeList nodeList = element.getChildNodes();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    String nodeName = node.getNodeName();
                    String nodeText = node.getTextContent();
                    map.put(nodeName, nodeText);
                }
            }
        }
        return map;

    }

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param map:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/19 16:32
     */
    public static String map2Xml(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                //从 DOM 工厂中获得 DOM 解析器
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.newDocument();
                Element root = document.createElement(DEFAULT_XML_SUFFIX);
                document.appendChild(root);
                map.entrySet().forEach(item -> {
                    String key = item.getKey();
                    String object = item.getValue();
                    String value = null;
                    if (object != null) {
                        value = object;
                    }
                    Element element = document.createElement(key);
                    element.appendChild(document.createCDATASection(value));
                    root.appendChild(element);
                });
                return cn.hutool.core.util.XmlUtil.toStr(document);
            } catch (ParserConfigurationException e) {
                LOGGER.error("map转 xml 失败:{}", e);
                return null;
            }
        }
        return null;
    }

}
