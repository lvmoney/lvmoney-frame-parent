package com.lvmoney.demo.resubmit.ao;/**
 * 描述:
 * 包名:com.lvmoney.demo.resubmit.ao
 * 版本信息: 版本1.0
 * 日期:2020/5/14
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/14 9:53
 */
@Data
public class TestAo implements Serializable {
    /**
     * 合同编号
     */

    private String conCode;
    private MultipartFile file;
}
