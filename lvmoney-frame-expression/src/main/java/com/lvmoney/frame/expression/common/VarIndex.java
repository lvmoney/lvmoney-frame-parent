package com.lvmoney.frame.expression.common;
/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.common
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.expression.tokenizer.Tokenizer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @describe：用于处理表达式对历史数据的引用<br> 像这样的表达式：a1#1#10~40
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class VarIndex {

    public static final String varIndxSplit = String.valueOf(Tokenizer.varIndxSplit);

    public static final String indxRange = String.valueOf(Tokenizer.indxRange);
    /**
     * 引用的工艺参数编码
     */
    private String code;
    /**
     * 历史数据索引
     */
    private List<Integer[]> indxList = new LinkedList<Integer[]>();
    /**
     * 历史数据代号转换成值后的字符串，（多个值逗号隔开）
     */
    private StringBuilder value = new StringBuilder();
    /**
     * 最小索引
     */
    private Integer min;
    /**
     * 最大索引
     */
    private Integer max;

    public VarIndex(String var) {

        String[] s = var.split(varIndxSplit);
        this.code = s[0].trim();

        for (int i = 1; i < s.length; i++) {
            String a = s[i].trim();
            if (a.contains(indxRange)) {
                String[] ss = a.split(indxRange);
                Integer min = Integer.valueOf(ss[0].trim());
                Integer max = Integer.valueOf(ss[1].trim());

                this.add(min, max);
            } else {
                Integer v = Integer.valueOf(a);
                this.add(v, v);
            }
        }
    }

    public VarIndex build(List<Map<String, Object>> list) {
        this.value.setLength(0);
        for (Integer[] minMax : this.indxList) {
            this.build(minMax[0], minMax[1], list);
        }
        return this;
    }

    private void build(int min, int max, List<Map<String, Object>> list) {

        for (int i = min - this.min; i <= max - this.min; i++) {
            String s = list.get(i).get(this.code).toString();
            this.value.append(s).append(BaseConstant.CHAR_COMMA);
        }
    }

    public String getValue() {
        return removeLastStr(this.value.toString(), BaseConstant.CHAR_COMMA);
    }

    private static String removeLastStr(String orgStr, String lastStr) {
        if (orgStr.endsWith(lastStr)) {
            orgStr = orgStr.substring(0, orgStr.lastIndexOf(lastStr));
        }
        return orgStr;
    }

    public void add(Integer min, Integer max) {
        if (this.min == null || this.min.intValue() > min.intValue()) {
            this.min = min;
        }
        if (this.max == null || this.max.intValue() < max.intValue()) {
            this.max = max;
        }
        this.indxList.add(new Integer[]{min, max});
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Integer[]> getIndxList() {
        return indxList;
    }

    public void setIndxList(List<Integer[]> indxList) {
        this.indxList = indxList;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
