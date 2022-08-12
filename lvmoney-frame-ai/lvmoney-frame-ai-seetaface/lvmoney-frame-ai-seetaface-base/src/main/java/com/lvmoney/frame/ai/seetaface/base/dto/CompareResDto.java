package com.lvmoney.frame.ai.seetaface.base.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.base.vo
 * 版本信息: 版本1.0
 * 日期:2022/2/11
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.oss.common.vo.FileBaseVo;
import com.lvmoney.frame.oss.common.vo.FileQueryVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/2/11 10:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompareResDto implements Serializable {
    private static final long serialVersionUID = -3886894283769356579L;
    /**
     * 源图片源
     */
    private FileQueryVo res;

    /**
     * 对比图片流
     */
    private FileBaseVo target;

    /**
     * 是否记录对比记录
     */
    private Boolean record;
}
