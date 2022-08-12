package com.lvmoney.frame.sync.uniformity.db.ro;/**
 * 描述:
 * 包名:com.lvmoney.frame.sync.uniformity.db.ro
 * 版本信息: 版本1.0
 * 日期:2022/1/6
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/1/6 14:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectDurationRo implements Serializable {

    private static final long serialVersionUID = 2615820904391345396L;
    /**
     * 表用到的sharding 策略
     */
    private String tableLogicTable;
    /**
     * 分类
     */
    private String classify;
    /**
     * 库用到的sharding 策略
     */
    private String dbLogicTable;
    /**
     * 上一次结束时间
     */
    private LocalDateTime lastSyncDate;

    /**
     * 新的开始时间
     */
    private LocalDateTime latelySyncDate;

    private Long expired;
}
