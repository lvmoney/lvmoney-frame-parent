package com.lvmoney.frame.core.service;

import java.io.Serializable;
import java.util.Collection;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface ITree {
    /**
     * 获取被依赖节点
     *
     * @throws
     * @return: java.io.Serializable
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:51
     */
    Serializable getId();

    /**
     * 设置被依赖节点
     *
     * @param id:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:51
     */
    void setId(Serializable id);

    /**
     * 获取依赖节点
     *
     * @throws
     * @return: java.io.Serializable
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:51
     */
    Serializable getParent();

    /**
     * 设置依赖节点
     *
     * @param parent:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:51
     */
    void setParent(Serializable parent);

    /**
     * 获取孩子列表
     *
     * @throws
     * @return: java.util.Collection<? extends com.lvmoney.common.service.ITree>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:51
     */
    Collection<? extends ITree> getChildren();

    /**
     * 设置孩子列表
     *
     * @param children:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:51
     */
    void setChildren(Collection<? extends ITree> children);
}
