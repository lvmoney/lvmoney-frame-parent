/**
 * 描述:
 * 包名:com.lvmoney.mongo.service
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  上午9:22:39
 * Copyright 成都三合力通科技有限公司
 */

package com.lvmoney.frame.oss.gridfs.service;


import com.lvmoney.frame.oss.gridfs.vo.BaseMongoCollective;
import com.lvmoney.frame.oss.gridfs.vo.BaseMongoVo;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2019年1月10日 上午9:22:39
 */

public interface BaseMongoService {
    /**
     * 保存数据对象，集合为数据对象中
     *
     * @param baseVo: data mongo的mo
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:34
     */
    void save(BaseMongoVo baseVo);

    /**
     * 指定集合保存数据对象
     *
     * @param baseVo: data mongo的mo collectionName 集合名
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:34
     */

    void saveByCollectionName(BaseMongoVo baseVo);

    /**
     * 根据数据对象中的id删除数据，集合为数据对象中
     *
     * @param baseVo: data 数据对象
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:34
     */
    void remove(BaseMongoVo baseVo);

    /**
     * 指定集合 根据数据对象中的id删除数据
     *
     * @param baseVo: data 数据对象 collectionName 集合名
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:35
     */
    void removeByCollectionName(BaseMongoVo baseVo);

    /**
     * 根据key，value到指定集合删除数据
     *
     * @param baseVo: baseVo key 键 data 值 collectionName 集合名
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:35
     */
    void removeById(BaseMongoVo baseVo);

    /**
     * 指定集合 修改数据，且仅修改找到的第一条数据
     *
     * @param baseCollection: key 修改条件 key keys 修改内容 key数组 data 修改条件 value datas 修改内容
     *                        value数组 collectionName 集合名
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:35
     */
    void updateFirst(BaseMongoCollective baseCollection);

    /**
     * 指定集合 修改数据，且修改所找到的所有数据
     *
     * @param baseCollection: key 修改条件 key keys 修改内容 key数组 data 修改条件 value datas 修改内容
     *                        value数组 collectionName 集合名
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:36
     */
    void updateMulti(BaseMongoCollective baseCollection);

    /**
     * 根据条件查询出所有结果集 集合为数据对象中@Document 注解所配置的collection
     *
     * @param baseCollection: data 数据对象 keys 查询条件 key datas 查询条件 value
     * @return: java.util.List<?>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:36
     */
    List<?> find(BaseMongoCollective baseCollection);

    /**
     * 指定集合 根据条件查询出所有结果集
     *
     * @param baseCollection: keys 查询条件 key data 数据对象 datas 查询条件 value collectionName 集合名
     * @return: java.util.List<?>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:37
     */
    List<?> findByCollectionName(BaseMongoCollective baseCollection);

    /**
     * 指定集合 根据条件查询出所有结果集 并排倒序
     *
     * @param baseCollection: keys 查询条件 key data 数据对象 datas 查询条件
     *                        value collectionName 集合名 sort 排序字段 sortType 排序类型
     * @return: java.util.List<?>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:37
     */
    List<?> sortFindByCollectionName(BaseMongoCollective baseCollection);

    /**
     * 根据条件查询出符合的第一条数据 集合为数据对象中 @Document 注解所配置的collection
     *
     * @param baseCollection: keys 查询条件 key data 数据对象 datas 查询条件
     * @return: java.lang.Object
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:38
     */
    Object findOne(BaseMongoCollective baseCollection);

    /**
     * 指定集合 根据条件查询出符合的第一条数据
     *
     * @param baseCollection: keys 查询条件 key data 数据对象 datas 查询条件 value
     * @return: java.lang.Object
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:38
     */
    Object findFirstOne(BaseMongoCollective baseCollection);

    /**
     * 查询出所有结果集 集合为数据对象中 @Document 注解所配置的collection
     *
     * @param baseVo: 基础实体
     * @return: java.util.List<?>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:38
     */
    List<?> findAll(BaseMongoVo baseVo);

    /**
     * 指定集合 查询出所有结果集
     *
     * @param baseVo: 基础实体
     * @return: java.util.List<?>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:38
     */
    List<?> findAllByCollectionName(BaseMongoVo baseVo);
}
