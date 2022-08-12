package com.lvmoney.frame.core.util;


import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.exception.CommonException;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.core.service.ITree;
import com.lvmoney.frame.core.vo.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class TreeUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(TreeUtil.class);

    /**
     * 集合转树结构
     *
     * @param collection:
     * @param clazz:
     * @throws
     * @return: java.util.Collection<T>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:38
     */
    public static <T> Collection<T> toTree(Collection<T> collection, Class<T> clazz) {
        return toTree(collection, null, null, null, clazz);
    }

    /**
     * 集合转树结构,注意,使用此方法,则集合元素必须继承ITree接口
     *
     * @param collection:
     * @throws
     * @return: java.util.Collection<T>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:38
     */
    public static <T extends ITree> Collection<T> toTree(Collection<T> collection) {
        try {
            if (collection == null || collection.isEmpty()) {
                // 如果目标集合为空,直接返回一个空树
                return null;
            }
            // 找出所有的根节点
            Collection<T> roots = null;
            if (collection.getClass().isAssignableFrom(Set.class)) {
                roots = new HashSet<>();
            } else {
                roots = new ArrayList<>();
            }
            for (T tree : collection) {
                Object o = ITree.class.getMethod("getParent").invoke(tree);
                if (o instanceof String) {
                    if (StringUtil.isEmpty((String) o)) {
                        roots.add(tree);
                    }
                } else if (o == null) {
                    roots.add(tree);
                }
            }
            // 从目标集合移除所有的根节点
            collection.removeAll(roots);
            // 为根节点添加孩子节点
            for (T tree : roots) {
                addChild(tree, collection);
            }
            return roots;
        } catch (Exception e) {
            LOGGER.error("集合转树结构报错:{}", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 集合转树结构
     *
     * @param collection:
     * @param id:
     * @param parent:
     * @param children:
     * @param clazz:
     * @throws
     * @return: java.util.Collection<T>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:38
     */
    public static <T> Collection<T> toTree(Collection<T> collection, String id, String parent, String children, Class<T> clazz) {
        try {
            if (collection == null || collection.isEmpty()) {
                // 如果目标集合为空,直接返回一个空树
                return null;
            }
            if (StringUtil.isEmpty(id)) {
                // 如果被依赖字段名称为空则默认为id
                id = "id";
            }
            if (StringUtil.isEmpty(parent)) {
                // 如果依赖字段为空则默认为parent
                parent = "parent";
            }
            if (StringUtil.isEmpty(children)) {
                // 如果子节点集合属性名称为空则默认为children
                children = "children";
            }
            // 初始化根节点集合
            Collection<T> roots = null;
            if (collection.getClass().isAssignableFrom(Set.class)) {
                // 如果目标节点是一个set集合,则初始化根节点集合为hashset
                roots = new HashSet<>();
            } else {
                // 否则初始化为Arraylist,
                roots = new ArrayList<>();
            }
            // 这里集合初始化只分2中,要么是hashset,要么ArrayList,因为这两种最常用,其他不常用的摒弃
            //获取id字段
            Field idField = getIdField(parent, clazz);
            //获取父字段
            Field parentField = getParentField(parent, clazz);
            // 获取孩子字段
            Field childrenField = getChildrenField(children, clazz);
            // 设置为可访问
            idField.setAccessible(true);
            parentField.setAccessible(true);
            childrenField.setAccessible(true);
            // 找出所有的根节点
            for (T c : collection) {
                Object o = parentField.get(c);
                if (o instanceof String) {
                    if (StringUtil.isEmpty((String) o)) {
                        // 如果父节点为空则说明是根节点,添加到根节点集合
                        roots.add(c);
                    }
                } else {
                    if (o == null) {
                        roots.add(c);
                    }
                }
            }
            // 从目标集合移除所有根节点
            collection.removeAll(roots);
            for (T c : roots) {
                // 遍历根节点,依次添加子节点
                addChild(c, collection, idField, parentField, childrenField);
            }
            // 关闭可访问
            idField.setAccessible(false);
            parentField.setAccessible(false);
            childrenField.setAccessible(false);
            return roots;
        } catch (Exception e) {
            LOGGER.error("集合转树结构报错:{}", e);
            throw new BusinessException(CommonException.Proxy.TREE_CONVERSION_ERROR);
        }
    }

    /**
     * 获得 fileId
     *
     * @param parent:
     * @param clazz:
     * @throws
     * @return: java.lang.reflect.Field
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:39
     */
    private static Field getIdField(String parent, Class clazz) {
        Field parentField = null;
        try {
            // 获取被依赖字段
            parentField = clazz.getDeclaredField(parent);
        } catch (NoSuchFieldException e1) {
            try {
                parentField = clazz.getSuperclass().getDeclaredField(parent);
            } catch (NoSuchFieldException e) {
                LOGGER.error("获取idField字段报错:{}", e);
                throw new BusinessException(CommonException.Proxy.TREE_CONVERSION_ERROR);
            }
        }
        return parentField;
    }

    /**
     * 获得父 field
     *
     * @param parent:
     * @param clazz:
     * @throws
     * @return: java.lang.reflect.Field
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:39
     */
    private static Field getParentField(String parent, Class clazz) {
        Field parentField = null;
        try {
            // 获取被依赖字段
            parentField = clazz.getDeclaredField(parent);
        } catch (NoSuchFieldException e1) {
            try {
                parentField = clazz.getSuperclass().getDeclaredField(parent);
            } catch (NoSuchFieldException e) {
                LOGGER.error("获取parentField字段报错:{}", e);
                throw new BusinessException(CommonException.Proxy.TREE_CONVERSION_ERROR);
            }
        }
        return parentField;
    }

    /**
     * 获得子field
     *
     * @param children:
     * @param clazz:
     * @throws
     * @return: java.lang.reflect.Field
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:39
     */
    private static Field getChildrenField(String children, Class clazz) {
        Field childrenField = null;
        try {
            childrenField = clazz.getDeclaredField(children);
        } catch (NoSuchFieldException e1) {
            try {
                childrenField = clazz.getSuperclass().getDeclaredField(children);
            } catch (NoSuchFieldException e) {
                LOGGER.error("获取childrenField字段报错:{}", e);
                throw new BusinessException(CommonException.Proxy.TREE_CONVERSION_ERROR);
            }
        }
        return childrenField;
    }

    /**
     * 新增child
     *
     * @param tree:
     * @param collection:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:40
     */
    public static <T extends ITree> void addChild(T tree, Collection<T> collection) {
        try {
            Object id = ITree.class.getMethod("getId").invoke(tree);
            Collection<T> children = (Collection<T>) ITree.class.getMethod("getChildren").invoke(tree);
            for (T cc : collection) {
                Object o = ITree.class.getMethod("getParent").invoke(cc);
                if (id.equals(o)) {
                    // 如果当前节点的被依赖值和目标节点的被依赖值相等,则说明,当前节点是目标节点的子节点
                    if (children == null) {
                        // 如果目标节点的孩子集合为null,初始化目标节点的孩子集合
                        if (collection.getClass().isAssignableFrom(Set.class)) {
                            // 如果目标集合是一个set集合,则初始化目标节点的孩子节点集合为set
                            children = new HashSet<>();
                        } else {
                            // 否则初始化为list
                            children = new ArrayList<>();
                        }
                    }
                    // 将当前节点添加到目标节点的孩子节点
                    children.add(cc);
                    // 重设目标节点的孩子节点集合,这里必须重设,因为如果目标节点的孩子节点是null的话,这样是没有地址的,就会造成数据丢失,所以必须重设,如果目标节点所在类的孩子节点初始化为一个空集合,而不是null,则可以不需要这一步,因为java一切皆指针
                    ITree.class.getMethod("setChildren", Collection.class).invoke(tree, children);
                    // 递归添加孩子节点
                    addChild(cc, collection);
                }
            }
        } catch (Exception e) {
            LOGGER.error("添加子节点报错{}", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 为目标节点添加孩子节点,此方法为私有,不能为公开,否则类修改信息无法恢复,后面有公开方法,其专门为目标节点添加子节点
     *
     * @param c:
     * @param collection:
     * @param idField:
     * @param parentField:
     * @param childrenField:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:40
     */
    private static <T> void addChild(T c, Collection<T> collection, Field idField, Field parentField, Field childrenField) throws IllegalAccessException {
        // 获取目标节点的被依赖值
        Object id = idField.get(c);
        // 获取目标节点的孩子列表
        Collection<T> children = (Collection<T>) childrenField.get(c);
        // 遍历目标集合
        for (T cc : collection) {
            // 获取当前节点的依赖值
            Object o = parentField.get(cc);
            if (id.equals(o)) {
                // 如果当前节点的被依赖值和目标节点的被依赖值相等,则说明,当前节点是目标节点的子节点
                if (children == null) {
                    // 如果目标节点的孩子集合为null,初始化目标节点的孩子集合
                    if (collection.getClass().isAssignableFrom(Set.class)) {
                        // 如果目标集合是一个set集合,则初始化目标节点的孩子节点集合为set
                        children = new HashSet<>();
                    } else {
                        // 否则初始化为list
                        children = new ArrayList<>();
                    }
                }
                // 将当前节点添加到目标节点的孩子节点
                children.add(cc);
                // 重设目标节点的孩子节点集合,这里必须重设,因为如果目标节点的孩子节点是null的话,这样是没有地址的,就会造成数据丢失,所以必须重设,如果目标节点所在类的孩子节点初始化为一个空集合,而不是null,则可以不需要这一步,因为java一切皆指针
                childrenField.set(c, children);
                // 递归添加孩子节点
                addChild(cc, collection, idField, parentField, childrenField);
            }
        }
        // 特别说明:大家可以看到此递归没有明显出口,其出口就是是否当前节点的依赖值和目标节点的被依赖值一样,一样就递归,不一样进不了if,自然出递归
        // 此工具类自我感觉是最简单的,最实用的工具类,我看网上许多人写的,都是云的雾的,本来也想借鉴,但是实在没一个能看的感觉思路清晰,没办法,自己动手造轮子
    }

    /**
     * 为目标节点添加孩子
     *
     * @param c          目标节点
     * @param collection 目标集合
     * @param id         被依赖字段名
     * @param parent     依赖字段名
     * @param children   孩子节点字段名
     * @param clazz      集合元素所在类别
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:40
     */
    public static <T> void addChild(T c, Collection<T> collection, String id, String parent, String children, Class<T> clazz) {
        try {
            if (collection == null || collection.isEmpty()) {
                // 如果目标集合为空,直接返回一个空树
                return;
            }
            if (StringUtil.isEmpty(id)) {
                // 如果被依赖字段名称为空则默认为id
                id = "id";
            }
            if (StringUtil.isEmpty(parent)) {
                // 如果依赖字段为空则默认为parent
                parent = "parent";
            }
            if (StringUtil.isEmpty(children)) {
                // 如果子节点集合属性名称为空则默认为children
                children = "children";
            }
            Field idField = null;
            try {
                // 获取依赖字段
                idField = clazz.getDeclaredField(id);
            } catch (NoSuchFieldException e1) {
                idField = clazz.getSuperclass().getDeclaredField(id);
            }
            Field parentField = null;
            try {
                // 获取被依赖字段
                parentField = clazz.getDeclaredField(parent);
            } catch (NoSuchFieldException e1) {
                parentField = clazz.getSuperclass().getDeclaredField(parent);
            }
            // 获取孩子字段
            Field childrenField = null;
            try {
                childrenField = clazz.getDeclaredField(children);
            } catch (NoSuchFieldException e1) {
                childrenField = clazz.getSuperclass().getDeclaredField(children);
            }
            // 设置为可访问
            idField.setAccessible(true);
            parentField.setAccessible(true);
            childrenField.setAccessible(true);
            addChild(c, collection, idField, parentField, childrenField);
            // 关闭可访问
            idField.setAccessible(false);
            parentField.setAccessible(false);
            childrenField.setAccessible(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *

     */
    /**
     * 为目标节点添加孩子
     *
     * @param c          目标节点
     * @param collection 目标集合
     * @param clazz      集合元素所在类型
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:41
     */
    public static <T> void addChild(T c, Collection<T> collection, Class<T> clazz) {
        addChild(c, collection, null, null, null, clazz);
    }

    public static void main(String[] args) {
        List<Menu> list = new ArrayList<>();
        list.add(new Menu(1, null));
        list.add(new Menu(2, null));
        list.add(new Menu(3, null));
        list.add(new Menu(4, 1));
        list.add(new Menu(5, 1));
        list.add(new Menu(6, 1));
        list.add(new Menu(7, 2));
        list.add(new Menu(8, 2));
        list.add(new Menu(9, 3));
        list.add(new Menu(10, 4));
        list.add(new Menu(11, 7));
        list.add(new Menu(12, 5));
        list.add(new Menu(13, 10));
        list.add(new Menu(14, 8));
        list.add(new Menu(15, 11));
        list.add(new Menu(16, 12));
        list.add(new Menu(17, 13));
        Collection<Menu> menus = TreeUtil.toTree(list, null, null, null, Menu.class);
        System.out.println(JsonUtil.t2JsonString(menus));
    }
}
