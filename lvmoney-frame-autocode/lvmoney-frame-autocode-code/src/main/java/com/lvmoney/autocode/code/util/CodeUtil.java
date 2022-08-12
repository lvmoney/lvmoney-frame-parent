package com.lvmoney.autocode.code.util;/**
 * 描述:
 * 包名:com.lvmoney.autocode.code.util
 * 版本信息: 版本1.0
 * 日期:2020/6/19
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.autocode.code.enums.ClassTypeEnum;
import com.lvmoney.autocode.code.vo.ClassVo;
import com.lvmoney.autocode.code.vo.CodeVo;
import com.lvmoney.frame.autocode.common.enums.CatalogueEnum;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.core.util.DateUtil;
import com.lvmoney.frame.core.util.FileUtil;
import com.lvmoney.frame.core.util.ModuleUtil;
import com.lvmoney.frame.core.util.StringUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.lvmoney.autocode.code.constant.CodeConstant.*;
import static com.lvmoney.frame.autocode.common.enums.AutocodeConstant.DEFAULT_JAVA_BASE;
import static com.lvmoney.frame.autocode.common.enums.AutocodeConstant.DEFAULT_PACKAGE_COM;
import static com.lvmoney.frame.base.core.constant.BaseConstant.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/19 9:09
 */
public class CodeUtil {
    /**
     * 创建 api，apiserver，controller
     *
     * @param codeVo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/22 17:12
     */
    public static void create(CodeVo codeVo) {
        createApi(codeVo);
        createApiServer(codeVo);
        createController(codeVo);
    }

    /**
     * 创建api
     *
     * @param codeVo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/22 16:12
     */
    public static void createApi(CodeVo codeVo) {
        ClassVo classVo = new ClassVo();
        String basePath = getBasePath(codeVo);
        classVo.setCompany(codeVo.getCompany());
        classVo.setAuthor(codeVo.getAuthor());
        String interfacePkg = DEFAULT_PACKAGE_COM + DECIMAL_POINT + codeVo.getOrg() + DECIMAL_POINT + codeVo.getArtifact() + DECIMAL_POINT + codeVo.getSys() + DECIMAL_POINT + CatalogueEnum.SURFACE.getValue();
        String interfacePath = basePath + CatalogueEnum.SURFACE.getValue();
        interfacePath = interfacePath.replaceAll(REPLACE_DECIMAL_POINT, BACKSLASH);
        String interfaceName = DEFAULT_API_INTERFACE_PREFIX + StringUtil.upperFirstCode(codeVo.getName());
        classVo.setPkg(interfacePkg);
        classVo.setType(ClassTypeEnum.INTERFACE);
        classVo.setName(interfaceName);
        createClass(interfacePath, interfaceName, buildClass(classVo), codeVo.isCover());
    }

    /**
     * 创建应用控制器
     *
     * @param codeVo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/22 16:13
     */
    public static void createController(CodeVo codeVo) {
        ClassVo classVo = new ClassVo();
        String basePath = getBasePath(codeVo);
        classVo.setCompany(codeVo.getCompany());
        classVo.setAuthor(codeVo.getAuthor());
        classVo.setAnnotations(new ArrayList() {{
            add(CONTROLLER_ANNOTATIONS);
        }});
        classVo.setImportClass(new ArrayList() {{
            add(CONTROLLER_IMPORT);
        }});
        String controllerPkg = DEFAULT_PACKAGE_COM + DECIMAL_POINT + codeVo.getOrg() + DECIMAL_POINT + codeVo.getArtifact() + DECIMAL_POINT + codeVo.getSys() + DECIMAL_POINT + CatalogueEnum.APPLY_CONTROLLER.getValue();
        String controllerPath = basePath + CatalogueEnum.APPLY_CONTROLLER.getValue();
        controllerPath = controllerPath.replaceAll(REPLACE_DECIMAL_POINT, BACKSLASH);
        String controllerName = StringUtil.upperFirstCode(codeVo.getName()) + DEFAULT_CONTROLLER_SUFFIX;
        classVo.setPkg(controllerPkg);
        classVo.setType(ClassTypeEnum.CLASS);
        classVo.setName(controllerName);
        createClass(controllerPath, controllerName, buildClass(classVo), codeVo.isCover());
    }

    /**
     * 创建apiserver
     *
     * @param codeVo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/22 16:12
     */
    public static void createApiServer(CodeVo codeVo) {
        ClassVo classVo = new ClassVo();
        String basePath = getBasePath(codeVo);
        classVo.setCompany(codeVo.getCompany());
        classVo.setAuthor(codeVo.getAuthor());
        classVo.setAnnotations(new ArrayList() {{
            add(CONTROLLER_ANNOTATIONS);
        }});
        String implInterface = DEFAULT_API_INTERFACE_PREFIX + StringUtil.upperFirstCode(codeVo.getName());
        String importImpl = DEFAULT_PACKAGE_COM + DECIMAL_POINT + codeVo.getOrg() + DECIMAL_POINT + codeVo.getArtifact() + DECIMAL_POINT + codeVo.getSys() + DECIMAL_POINT + CatalogueEnum.SURFACE.getValue() + DECIMAL_POINT + implInterface;
        classVo.setImportClass(new ArrayList() {{
            add(CONTROLLER_IMPORT);
            add(importImpl);
        }});
        classVo.setImplInterface(new ArrayList() {{
            add(implInterface);
        }});
        String apiServerPkg = DEFAULT_PACKAGE_COM + DECIMAL_POINT + codeVo.getOrg() + DECIMAL_POINT + codeVo.getArtifact() + DECIMAL_POINT + codeVo.getSys() + DECIMAL_POINT + CatalogueEnum.APISERVER_CONTROLLER.getValue();
        String apiServerPath = basePath + CatalogueEnum.APISERVER_CONTROLLER.getValue();
        apiServerPath = apiServerPath.replaceAll(REPLACE_DECIMAL_POINT, BACKSLASH);
        String apiServerName = DEFAULT_API_INTERFACE_PREFIX + StringUtil.upperFirstCode(codeVo.getName()) + DEFAULT_CONTROLLER_SUFFIX;
        classVo.setPkg(apiServerPkg);
        classVo.setType(ClassTypeEnum.CLASS);
        classVo.setName(apiServerName);
        createClass(apiServerPath, apiServerName, buildClass(classVo), codeVo.isCover());

    }

    /**
     * 在指定的路径创建class文件
     *
     * @param path:
     * @param name:
     * @param res:
     * @param cover:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/22 16:10
     */
    public static void createClass(String path, String name, String res, boolean cover) {
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
        String fileName = path + BACKSLASH + name + JAVA_FILE_SUFFIX;
        if (cover) {
            FileUtil.delFile(new File(fileName));
        }
        FileUtil.largeWrite(fileName, true, res);
    }

    /**
     * 构造class的内容
     *
     * @param classVo:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/22 16:10
     */
    public static String buildClass(ClassVo classVo) {
        StringBuilder sb = new StringBuilder();
        sb.append(PACKAGE + PLACEHOLDER_BLANK_SPACE + classVo.getPkg() + SEMICOLON);
        String title = MessageFormat.format(DEFAULT_CLASS_TITLE, classVo.getPkg(), LocalDate.now().toString(), classVo.getCompany());
        sb.append(title);
        sb.append(PLACEHOLDER_WARP_SPACE);
        if (ObjectUtils.isNotEmpty(classVo.getImportClass())) {
            classVo.getImportClass().parallelStream().forEach(e -> {
                sb.append(IMPORT + PLACEHOLDER_BLANK_SPACE + e + SEMICOLON);
                sb.append(PLACEHOLDER_WARP_SPACE);
            });

        }
        sb.append(PLACEHOLDER_WARP_SPACE);
        String describe = MessageFormat.format(DEFAULT_CLASS_DESCRIBE, classVo.getAuthor(), classVo.getCompany(), DateUtil.format(LocalDateTime.now(), BaseConstant.API_RESULT_DATA_DATE_FORMAT));

        sb.append(describe);
        sb.append(PLACEHOLDER_WARP_SPACE);

        if (ObjectUtils.isNotEmpty(classVo.getAnnotations())) {
            classVo.getAnnotations().parallelStream().forEach(e -> {
                sb.append(ANNOTATIONS + e);
                sb.append(PLACEHOLDER_WARP_SPACE);
            });
        }
        String clazz = PUBLIC + PLACEHOLDER_BLANK_SPACE + classVo.getType().getValue() + PLACEHOLDER_BLANK_SPACE + classVo.getName();
        if (classVo.getType().equals(ClassTypeEnum.INTERFACE)) {
            sb.append(clazz + BRACE_LEFT);
        } else {
            if (ObjectUtils.isNotEmpty(classVo.getImplInterface())) {
                StringBuilder temp = new StringBuilder();
                classVo.getImplInterface().forEach(e -> {
                    temp.append(e);
                    temp.append(CHAR_COMMA);
                });
                clazz = clazz + PLACEHOLDER_BLANK_SPACE + IMPLEMENTS + PLACEHOLDER_BLANK_SPACE + temp.substring(0, temp.length() - 1);
            }
            if (StringUtils.isNotEmpty(classVo.getParentClass())) {
                clazz = clazz + PLACEHOLDER_BLANK_SPACE + EXTENDS + classVo.getParentClass();
            }
            sb.append(clazz + BRACE_LEFT);
        }
        sb.append(PLACEHOLDER_WARP_SPACE);
        sb.append(BRACE_RIGHT);
        System.out.println(sb);
        return sb.toString();
    }

    /**
     * 获得class的基础包
     *
     * @param codeVo:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/22 16:12
     */
    private static String getBasePath(CodeVo codeVo) {
        String path = codeVo.getPath();
        if (StringUtils.isEmpty(path)) {
            path = ModuleUtil.getModuleRootPath();
        }
        String basePath = DEFAULT_JAVA_BASE + BACKSLASH + DEFAULT_PACKAGE_COM + BACKSLASH + codeVo.getOrg() + BACKSLASH + codeVo.getArtifact() + BACKSLASH + codeVo.getSys() + BACKSLASH;
        basePath = path + basePath.replaceAll(REPLACE_DECIMAL_POINT, BACKSLASH);
        return basePath;
    }

    public static void main(String[] args) {
        CodeVo codeVo = new CodeVo();
        codeVo.setArtifact("autocode");
        codeVo.setAuthor("lvmoney");
        codeVo.setCompany("XXXXX科技有限公司");
        codeVo.setName("user");
        codeVo.setOrg("lvmoney");
        codeVo.setSys("code");
        createApiServer(codeVo);
    }
}
