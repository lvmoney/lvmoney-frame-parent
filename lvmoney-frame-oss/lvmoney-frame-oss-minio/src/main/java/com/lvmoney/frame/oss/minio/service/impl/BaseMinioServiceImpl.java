package com.lvmoney.frame.oss.minio.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.oss.minio.service.impl
 * 版本信息: 版本1.0
 * 日期:2021/2/8
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.oss.common.annotations.OssService;
import com.lvmoney.frame.oss.common.exception.OssException;
import com.lvmoney.frame.oss.common.vo.FileBaseOutVo;
import com.lvmoney.frame.oss.common.vo.FileBaseVo;
import com.lvmoney.frame.oss.common.vo.FileByteOutVo;
import com.lvmoney.frame.oss.common.vo.FileQueryVo;
import com.lvmoney.frame.oss.minio.dto.MinioDto;
import com.lvmoney.frame.oss.minio.prop.MinioProperties;
import com.lvmoney.frame.oss.minio.service.BaseMinioService;
import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.lvmoney.frame.oss.common.constant.OssType.OSS_MINIO;
import static com.lvmoney.frame.oss.minio.constant.MinioConstant.MINIIO_PART_DEFAULT_SIZE;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/2/8 16:58
 */
@OssService(OSS_MINIO)
public class BaseMinioServiceImpl extends BaseMinioService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseMinioServiceImpl.class);
    @Autowired
    private MinioClient minioClient;
    @Autowired
    MinioProperties minioProperties;

    @Override
    public FileBaseOutVo save(FileBaseVo fileBaseVo) {
        MultipartFile multipartFile = fileBaseVo.getFile();
        MinioDto minioDto = (MinioDto) fileBaseVo.getData();
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String filename = fileBaseVo.getFileName() + BaseConstant.DECIMAL_POINT + extension;
        String bucketName = minioDto.getBucketName();
        boolean flag = bucketIsExist(bucketName);
        if (!flag) {
            throw new BusinessException(OssException.Proxy.MINIIO_BUCKET_NOT_EXIST);
        }

        if (fileBaseVo.getMaxSize() > 0 && fileBaseVo.getMaxSize() < multipartFile.getSize()) {
            throw new BusinessException(OssException.Proxy.MINIIO_FILE_SIZE_MAX);
        }
        try (InputStream stream = multipartFile.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(filename)
                            .stream(stream, -1, MINIIO_PART_DEFAULT_SIZE)
                            .contentType(multipartFile.getContentType())
                            .build());
        } catch (Exception e) {
            LOGGER.error("上传文件:{},报错:{}", filename, e);
            throw new BusinessException(OssException.Proxy.MINIIO_FILE_SAVE_ERROR);
        }
        String fileId = bucketName;
        FileBaseOutVo fileBaseOutVo = new FileBaseOutVo();
        fileBaseOutVo.setFileId(fileId);
        fileBaseOutVo.setFileName(filename);
        fileBaseOutVo.setFileType(multipartFile.getContentType());
        fileBaseOutVo.setSize(multipartFile.getSize());
        return fileBaseOutVo;
    }

    @Override
    public List<FileBaseOutVo> batchSave(List<FileBaseVo> fileBaseVos) {
        List<FileBaseOutVo> result = new ArrayList<>();
        fileBaseVos.forEach(fileBaseVo -> {
            FileBaseOutVo baseGridFsOutVo = this.save(fileBaseVo);
            result.add(baseGridFsOutVo);
        });
        return result;
    }

    @Override
    public FileByteOutVo getByFileId(FileQueryVo fileQueryVo) {
        boolean flag = bucketIsExist(fileQueryVo.getFileId());
        if (!flag) {
            throw new BusinessException(OssException.Proxy.MINIIO_BUCKET_NOT_EXIST);
        }
        String bucketName = fileQueryVo.getFileId();
        String fileName = fileQueryVo.getName();
        try {
            minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(fileName).build());
            InputStream stream = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
            FileByteOutVo fileByteOutVo = new FileByteOutVo();
            fileByteOutVo.setFileByte(IOUtils.toByteArray(stream));
            fileByteOutVo.setFileName(fileName);
            return fileByteOutVo;
        } catch (Exception e) {
            LOGGER.error("下载文件:{},报错:{}", fileName, e);
            throw new BusinessException(OssException.Proxy.MINIIO_FILE_DOWNLOAD_ERROR);
        }
    }

    @Override
    public List<FileByteOutVo> batchGetByFileId(List<FileQueryVo> fileQueryVos) {
        List<FileByteOutVo> result = new ArrayList<>();
        fileQueryVos.forEach(fileBaseVo -> {
            FileByteOutVo fileByteOutVo = this.getByFileId(fileBaseVo);
            result.add(fileByteOutVo);
        });
        return result;
    }

    @Override
    public void deleteByFileId(FileQueryVo fileQueryVo) {
        String bucketName = fileQueryVo.getFileId();
        String filename = fileQueryVo.getName();
        boolean flag = bucketIsExist(bucketName);
        if (!flag) {
            return;
        }
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(filename).build());
        } catch (Exception e) {
            LOGGER.error("删除桶名:{},文件名:{},报错:{}", bucketName, filename, e);
            throw new BusinessException(OssException.Proxy.MINIIO_FILE_DELETE_ERROR);
        }
    }

    @Override
    public boolean bucketIsExist(String bucketName) {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            LOGGER.error("判断桶名:{}的存储桶是否存在报错:{}", bucketName, e);
            throw new BusinessException(OssException.Proxy.MINIIO_BUCKET_EXIST_ERROR);
        }
    }

    @Override
    public boolean createBucket(String bucketName) {
        boolean flag = bucketIsExist(bucketName);
        if (!flag) {
            try {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } catch (Exception e) {
                LOGGER.error("创建存储桶:{}报错:{}", bucketName, e);
                throw new BusinessException(OssException.Proxy.MINIIO_BUCKET_CREATE_ERROR);
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> getBucketNames() {
        List<Bucket> bucketList = getBuckets();
        List<String> bucketListName = new ArrayList();
        for (Bucket bucket : bucketList) {
            bucketListName.add(bucket.name());
        }
        return bucketListName;
    }

    @Override
    public List<Bucket> getBuckets() {
        try {
            return minioClient.listBuckets();
        } catch (Exception e) {
            LOGGER.error("获得存储桶列表报错:{}", e);
            throw new BusinessException(OssException.Proxy.MINIIO_BUCKET_GET_ERROR);
        }

    }

    @Override
    public boolean deleteBucket(String bucketName) {
        boolean flag = bucketIsExist(bucketName);
        if (!flag) {
            return true;
        }
        Iterable<Result<Item>> myObjects = getObjects(bucketName);
        for (Result<Item> result : myObjects) {
            try {
                Item item = result.get();
                if (item.size() > 0) {
                    throw new BusinessException(OssException.Proxy.MINIIO_BUCKET_DELETE_ERROR);
                }
                minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
                flag = bucketIsExist(bucketName);
            } catch (Exception e) {
                LOGGER.error("删除存储桶{}报错:{}", bucketName, e);
                throw new BusinessException(OssException.Proxy.MINIIO_BUCKET_DELETE_ERROR);
            }
        }
        return flag;
    }

    @Override
    public List<String> getObjectNames(String bucketName) {
        List<String> listObjectNames = new ArrayList<>();
        boolean flag = bucketIsExist(bucketName);
        if (!flag) {
            return null;
        }
        Iterable<Result<Item>> myObjects = getObjects(bucketName);
        for (Result<Item> result : myObjects) {
            try {
                Item item = result.get();
                listObjectNames.add(item.objectName());
            } catch (Exception e) {
                LOGGER.error("列出存储桶中的所有对象名称{}报错:{}", bucketName, e);
                throw new BusinessException(OssException.Proxy.MINIIO_BUCKET_OBJECT_NAME_ERROR);
            }

        }
        return listObjectNames;

    }

    @Override
    public Iterable<Result<Item>> getObjects(String bucketName) {
        boolean flag = bucketIsExist(bucketName);
        if (!flag) {
            return null;
        }
        return minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(bucketName)
                .recursive(false)
                .includeUserMetadata(false)
                .useApiVersion1(false)
                .build());

    }
}
