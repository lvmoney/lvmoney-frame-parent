package com.zhy.frame.oss.gridfs.service.impl;/**
 * 描述:
 * 包名:com.zhy.frame.oss.gridfs.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/11/21
 * Copyright 四川******科技有限公司
 */


import com.mongodb.Block;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.exception.CommonException;
import com.zhy.frame.oss.common.annation.OssService;
import com.zhy.frame.oss.common.vo.FileBaseOutVo;
import com.zhy.frame.oss.common.vo.FileBaseVo;
import com.zhy.frame.oss.common.vo.FileByteOutVo;
import com.zhy.frame.oss.common.vo.FileQueryVo;
import com.zhy.frame.oss.gridfs.service.BaseGridFsService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.zhy.frame.oss.common.constant.OssType.OSS_GRIDFS;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/21 18:20
 */
@OssService(OSS_GRIDFS)
public class BaseGridFsServiceImpl extends BaseGridFsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseGridFsServiceImpl.class);
    @Autowired
    GridFsTemplate gridFsTemplate;
    @Autowired
    GridFSBucket gridFsBucket;
    /**
     * mongo 默认主键id名称
     */
    private static final String MONGO_KEY_ID = "_id";

    @Override
    public FileBaseOutVo save(FileBaseVo fileBaseVo) {
        long maxSize = fileBaseVo.getMaxSize();
        MultipartFile file = fileBaseVo.getFile();
        long fileSize = file.getSize();
        if (maxSize < file.getSize()) {
            throw new BusinessException(CommonException.Proxy.GRIDFS_FILE_SIZE);
        }
        String baseFileName = fileBaseVo.getFileName();
        String fileName = StringUtils.isBlank(baseFileName) ? file.getOriginalFilename() : baseFileName;
        // 获得文件类型
        String contentType = file.getContentType();
        // 获得文件输入流
        InputStream ins;
        try {
            ins = file.getInputStream();
            // 将文件存储到mongodb中,mongodb 将会返回这个文件的具体信息
            DBObject dbObj = (DBObject) fileBaseVo.getData();

            ObjectId objectId = gridFsTemplate.store(ins, fileName, contentType, dbObj);
            FileBaseOutVo result = new FileBaseOutVo();
            result.setFileName(fileName);
            result.setFileType(contentType);
            result.setFileId(objectId.toString());
            result.setSize(fileSize);
            return result;
        } catch (IOException e) {
            LOGGER.error("文件名为:{},文件类型为:{},保存文件报错:{}", fileName, contentType, e.getMessage());
            throw new BusinessException(CommonException.Proxy.GRIDFS_SAVE_ERROR);
        }

    }

    @Override
    public List<FileBaseOutVo> batchSave(List<FileBaseVo> fileBaseVos) {
        List<FileBaseOutVo> result = new ArrayList<>();
        fileBaseVos.forEach(baseGridFsService -> {
            FileBaseOutVo baseGridFsOutVo = this.save(baseGridFsService);
            result.add(baseGridFsOutVo);
        });

        return result;
    }

    @Override
    public FileByteOutVo getByFileId(FileQueryVo fileQueryVo) {
        String fileId = fileQueryVo.getFileId();
        Query query = Query.query(Criteria.where(MONGO_KEY_ID).is(fileId));
        GridFSFile gridFsFile = gridFsTemplate.findOne(query);
        if (gridFsFile == null) {
            throw new BusinessException(CommonException.Proxy.GRIDFS_QUERY_FILE_NOT_EXSIT);
        }
        String fileName = gridFsFile.getFilename();
        // 打开下载流对象
        GridFSDownloadStream gridFs = gridFsBucket.openDownloadStream(gridFsFile.getObjectId());
        // 创建gridFsSource，用于获取流对象
        GridFsResource gridFsResource = new GridFsResource(gridFsFile, gridFs);
        try {
            FileByteOutVo result = new FileByteOutVo();
            result.setFileByte(IOUtils.toByteArray(gridFsResource.getInputStream()));
            result.setFileName(fileName);
            return result;
        } catch (IllegalStateException | IOException e) {
            LOGGER.error("通过_id{}获得文件报错：{}", fileId, e.getMessage());
            throw new BusinessException(CommonException.Proxy.GRIDFS_QUERY_FILE_ERROR);
        }
    }

    @Override
    public List<FileByteOutVo> batchGetByFileId(List<FileQueryVo> fileQueryVos) {
        Criteria criteria = null;
        for (int i = 0; i < fileQueryVos.size(); i++) {
            String fileId = fileQueryVos.get(i).getFileId();
            if (i == 0) {
                criteria = Criteria.where(MONGO_KEY_ID).is(fileId);
            } else {
                criteria.and(MONGO_KEY_ID).is(fileId);
            }
        }
        Query query = Query.query(criteria);
        GridFSFindIterable gridFsFiles = gridFsTemplate.find(query);
        List<FileByteOutVo> result = new ArrayList<>();
        gridFsFiles.forEach((Block<? super GridFSFile>) gridFsFile -> {
            String fileId = gridFsFile.getObjectId().toString();
            FileByteOutVo baseGridFsByteOutVo = new FileByteOutVo();
            GridFSDownloadStream gridFs = gridFsBucket.openDownloadStream(gridFsFile.getObjectId());
            // 创建gridFsSource，用于获取流对象
            GridFsResource gridFsResource = new GridFsResource(gridFsFile, gridFs);
            try {
                String fileName = gridFsFile.getFilename();
                baseGridFsByteOutVo.setFileByte(IOUtils.toByteArray(gridFsResource.getInputStream()));
                baseGridFsByteOutVo.setFileName(fileName);
                result.add(baseGridFsByteOutVo);
            } catch (IllegalStateException | IOException e) {
                LOGGER.error("通过_id{}获得文件报错：{}", fileId, e.getMessage());
                throw new BusinessException(CommonException.Proxy.GRIDFS_QUERY_FILE_ERROR);
            }
        });
        return result;
    }

    @Override
    public void deleteByFileId(FileQueryVo fileQueryVo) {
        gridFsTemplate.delete(new Query().addCriteria(Criteria.where(MONGO_KEY_ID).is(fileQueryVo.getFileId())));

    }
}
