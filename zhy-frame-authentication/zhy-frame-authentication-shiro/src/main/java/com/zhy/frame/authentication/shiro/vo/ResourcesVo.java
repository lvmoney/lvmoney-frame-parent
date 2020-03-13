package com.zhy.frame.authentication.shiro.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ResourcesVo implements Serializable {
    /**
     *
     */


    private static final long serialVersionUID = -8621955278670702634L;

    private Integer id;

    /**
     * 资源名称
     */
    private String userName;

    /**
     * 资源url
     */
    private String resUrl;

    /**
     * 资源类型   1:菜单    2：按钮
     */
    private Integer userType;

    /**
     * 父资源
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer userSort;

}