
package com.lvmoney.frame.authentication.oauth2.center.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CommonVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    /**
     * 创建时间
     */
    private Date dateCreated;
    /**
     * 修改时间
     */
    private Date lastModified;
    /**
     * 状态
     */
    private Integer recordStatus;
    /**
     * 更该次数/每次修改+1
     */
    private Integer version;
    private String remarks;
    private String additionalData;
}
