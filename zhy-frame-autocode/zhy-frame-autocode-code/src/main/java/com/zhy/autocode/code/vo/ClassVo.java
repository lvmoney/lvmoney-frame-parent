package com.zhy.autocode.code.vo;/**
 * 描述:
 * 包名:com.zhy.autocode.code.vo
 * 版本信息: 版本1.0
 * 日期:2020/6/19
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.autocode.code.enums.ClassTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/19 9:13
 */
@Data
public class ClassVo implements Serializable {
    /**
     * 类名
     */
    private String name;
    /**
     * 类型
     */
    private ClassTypeEnum type;
    /**
     * import class
     */
    private List<String> importClass;
    /**
     * 包名
     */
    private String pkg;
    /**
     * 需要的注解
     */
    private List<String> annotations;
    /**
     * extends 的类
     */
    private String parentClass;
    /**
     * implement 的接口
     */
    private List<String> implInterface;

    /**
     * 作者
     */
    private String author;

    /**
     * 公司
     */
    private String company;
}
