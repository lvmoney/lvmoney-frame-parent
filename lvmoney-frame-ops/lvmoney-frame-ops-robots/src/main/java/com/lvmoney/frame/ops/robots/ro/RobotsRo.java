package com.lvmoney.frame.ops.robots.ro;/**
 * 描述:
 * 包名:com.lvmoney.robots.ro
 * 版本信息: 版本1.0
 * 日期:2019/10/29
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ops.robots.vo.Robots;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/29 16:09
 */
@Data
public class RobotsRo implements Serializable {
    private static final long serialVersionUID = 2888030780915746867L;
    private List<Robots> robots;
    private Long expired;
}
