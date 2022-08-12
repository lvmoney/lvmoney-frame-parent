package com.lvmoney.frame.authentication.oauth2.center.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginHistoryVo extends CommonVo {
    /**
     *
     */
    private static final long serialVersionUID = -3503838536778480869L;
    private String clientId;
    private String username;
    private String ip;
    private String device;
}
