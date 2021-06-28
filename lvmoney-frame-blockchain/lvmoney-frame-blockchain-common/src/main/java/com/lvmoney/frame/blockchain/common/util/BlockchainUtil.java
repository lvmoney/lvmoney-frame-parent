package com.lvmoney.frame.blockchain.common.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.common.util
 * 版本信息: 版本1.0
 * 日期:2021/6/21
 * Copyright XXXXXX科技有限公司
 */


import cn.hutool.core.util.ObjectUtil;

import static com.lvmoney.frame.base.core.constant.BaseConstant.DOUBLE_QUOTATION_MARKS;
import static com.lvmoney.frame.base.core.constant.BaseConstant.DOUBLE_QUOTATION_MARKS_ESCAPE;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/21 8:58
 */
public class BlockchainUtil {
    /**
     * 区块链调用接口某些字段需将json转换成string
     * 将引号替换从引号的转义符
     *
     * @param quotationMarks:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/21 9:11
     */
    public static String quotationMarksReplace(String quotationMarks) {
        if (ObjectUtil.isNotEmpty(quotationMarks) && quotationMarks.contains(DOUBLE_QUOTATION_MARKS)) {
            return quotationMarks.replaceAll(DOUBLE_QUOTATION_MARKS, DOUBLE_QUOTATION_MARKS_ESCAPE);
        } else {
            return quotationMarks;
        }

    }
}