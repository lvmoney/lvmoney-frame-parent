package com.lvmoney.demo.file.vo;/**
 * 描述:
 * 包名:com.lvmoney.demo.file.vo
 * 版本信息: 版本1.0
 * 日期:2020/10/12
 * Copyright 成都三合力通科技有限公司
 */


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/成都三合力通科技有限公司
 * @version:v1.0 2020/10/12 16:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Long2StringVo implements Serializable {
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long sex;
    private Long age;
}
