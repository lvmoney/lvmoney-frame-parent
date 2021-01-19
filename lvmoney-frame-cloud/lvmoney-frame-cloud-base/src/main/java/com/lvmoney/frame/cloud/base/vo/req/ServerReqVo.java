package com.lvmoney.frame.cloud.base.vo.req;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.vo.req
 * 版本信息: 版本1.0
 * 日期:2019/8/18
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @describe：通过k8s获得某个服务的基本信息 1、结合整个istio服务来看，一组服务用一个namespace，
 * 2、这个namespace里面的每一个服务都有一个服务名称
 * 3、最好全局唯一一个服务名称
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/18 16:12
 */
@Data
public class ServerReqVo implements Serializable {
    private static final long serialVersionUID = 2150751791395167040L;
    /**
     * 命名空间
     */
    private String namespace;
    /**
     * label名，一般一个server就一个labelName:app
     */
    private String labelName;
    /**
     * label值，一般一个server就一个labelvalue：服务的名字
     */
    private Set<String> labelValue;
}
