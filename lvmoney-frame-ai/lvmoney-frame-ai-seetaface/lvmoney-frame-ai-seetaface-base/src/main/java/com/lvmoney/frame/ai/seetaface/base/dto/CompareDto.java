package com.lvmoney.frame.ai.seetaface.base.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.base.vo
 * 版本信息: 版本1.0
 * 日期:2022/2/10
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.oss.common.vo.FileBaseVo;
import com.lvmoney.frame.oss.common.vo.FileByteOutVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/2/10 11:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompareDto implements Serializable {
    private static final long serialVersionUID = -6117451810333462905L;
    /**
     * 源图片流
     */
    private FileBaseVo res;
    /**
     * 对比图片流
     */
    private FileBaseVo target;
    /**
     * 是否记录对比记录
     */
    private Boolean record;
}