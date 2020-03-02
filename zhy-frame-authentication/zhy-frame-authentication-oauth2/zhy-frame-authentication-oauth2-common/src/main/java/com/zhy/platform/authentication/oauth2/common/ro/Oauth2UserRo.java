package com.zhy.platform.authentication.oauth2.common.ro;

import com.zhy.platform.authentication.oauth2.common.vo.UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Data
@NoArgsConstructor
public class Oauth2UserRo implements Serializable {
    private static final long serialVersionUID = -339395465817487373L;
    /**
     *
     */


    /**
     * 失效时间
     */
    private Long expire;
    /**
     * 把oauth2的user信息统一放到redis中
     */
    private Map<String, UserInfo> data;

}
