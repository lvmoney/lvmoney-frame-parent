package com.lvmoney.frame.demo.seata.client.vo.req;

import lombok.Data;

import java.io.Serializable;


/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Data
public class UpdateReqVo implements Serializable {
    private String SUserId;
    private String AUserId;
}
