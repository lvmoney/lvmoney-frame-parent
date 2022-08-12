package com.lvmoney.frame.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {
    private static final long serialVersionUID = -5168382023173238742L;
    private Integer id;
    private Integer parent;
    private List<Menu> children;

    public Menu(Integer id, Integer parent) {
        this.id = id;
        this.parent = parent;
    }
}
