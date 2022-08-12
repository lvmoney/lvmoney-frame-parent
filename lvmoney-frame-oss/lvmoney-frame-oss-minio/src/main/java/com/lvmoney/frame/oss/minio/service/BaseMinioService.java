package com.lvmoney.frame.oss.minio.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.oss.minio.service
 * 版本信息: 版本1.0
 * 日期:2021/2/8
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.oss.common.service.OssService;
import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/2/8 16:56
 */
public abstract class BaseMinioService implements OssService {


    /**
     * 判断存储桶是否存在
     *
     * @param bucketName: 存储桶名称
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/2/8 17:33
     */
    public abstract boolean bucketIsExist(String bucketName);

    /**
     * 创建存储桶
     *
     * @param bucketName:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/2/9 9:34
     */
    public abstract boolean createBucket(String bucketName);

    /**
     * 列出所有存储桶名称
     *
     * @throws
     * @return: java.util.List<java.lang.String>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/2/9 9:35
     */
    public abstract List<String> getBucketNames();

    /**
     * 列出所有存储桶
     *
     * @throws
     * @return: java.util.List<io.minio.messages.Bucket>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/2/9 9:36
     */
    public abstract List<Bucket> getBuckets();

    /**
     * 删除存储桶，存储桶不存在直接返回true
     *
     * @param bucketName:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/2/9 9:37
     */
    public abstract boolean deleteBucket(String bucketName);

    /**
     * 列出存储桶中的所有对象名称,桶不存在返回null
     *
     * @param bucketName:
     * @throws
     * @return: java.util.List<java.lang.String>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/2/9 9:38
     */
    public abstract List<String> getObjectNames(String bucketName);

    /**
     * 列出存储桶中的所有对象,若桶不存在直接返回null
     *
     * @param bucketName:
     * @throws
     * @return: java.lang.Iterable<io.minio.Result < io.minio.messages.Item>>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/2/9 9:40
     */
    public abstract Iterable<Result<Item>> getObjects(String bucketName);
}
