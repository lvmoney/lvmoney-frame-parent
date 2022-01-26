package com.lvmoney.frame.sync.uniformity.unique.service;/**
 * 描述:
 * 包名:com.chinapopin.platform.overall.unique.service
 * 版本信息: 版本1.0
 * 日期:2020/10/9
 * Copyright XXXXXX科技有限公司
 */



import com.lvmoney.frame.sync.uniformity.unique.bo.UniqueCodeGetBo;
import com.lvmoney.frame.sync.uniformity.unique.dto.UniqueCodeGetDto;
import com.lvmoney.frame.sync.uniformity.unique.ro.UniqueCodeGetRo;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/9 9:13
 */
public interface UniqueCodeService {

    /**
     * 通过redis key获得唯一code
     * 使用lua脚本，保证原则性的+1
     *
     * @param uniqueCodeGetDto:
     * @throws
     * @return: com.chinapopin.platform.overall.unique.bo.UniqueCodeGetBo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/10/10 16:21
     */

    List<UniqueCodeGetBo> getByKey(UniqueCodeGetDto uniqueCodeGetDto);

    /**
     * 每天凌晨初始化指定系统唯一code从0开始
     *
     * @param uniqueCodeGetRo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/10/10 14:51
     */
    void save2Redis(UniqueCodeGetRo uniqueCodeGetRo);

    /**
     * 获得redis的key
     *
     * @param clientId:
     * @throws
     * @return: java.lang.Integer
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/10/30 18:55
     */

    int getKey(String clientId);


}
