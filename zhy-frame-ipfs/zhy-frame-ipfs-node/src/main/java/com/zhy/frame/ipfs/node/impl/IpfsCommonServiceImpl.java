package com.zhy.frame.ipfs.node.impl;/**
 * 描述:
 * 包名:com.zhy.frame.ipfs.node.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/5/6
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.core.util.FileUtil;
import com.zhy.frame.ipfs.common.exception.IpfsException;
import com.zhy.frame.ipfs.common.service.IpfsCommonService;
import com.zhy.frame.ipfs.common.vo.FileByteOutVo;
import com.zhy.frame.ipfs.common.vo.FileQueryVo;
import com.zhy.frame.ipfs.common.vo.IpfsOutVo;
import com.zhy.frame.ipfs.common.vo.IpfsSaveVo;
import com.zhy.frame.ipfs.node.config.IpfsBuilder;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/6 18:07
 */
@Service
public class IpfsCommonServiceImpl implements IpfsCommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpfsCommonServiceImpl.class);

    @Autowired
    IpfsBuilder ipfsBuilder;

    @Override
    public IpfsOutVo save(IpfsSaveVo ipfsSaveVo) {
        MultipartFile multipartFile = ipfsSaveVo.getFile();
        File inputFile = FileUtil.multipartFile2File(multipartFile);
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(inputFile);
        MerkleNode merkleNode = null;
        try {
            merkleNode = ipfsBuilder.build().add(file).get(0);
        } catch (IOException e) {
            LOGGER.error("保存文件到ipfs报错:{}", e);
            throw new BusinessException(IpfsException.Proxy.IPFS_SAVE_ERROR);
        }
        String hash = merkleNode.hash.toString();
        IpfsOutVo ipfsOutVo = new IpfsOutVo();
        ipfsOutVo.setHash(hash);
        ipfsOutVo.setFileName(file.getName().get());
        ipfsOutVo.setLength(multipartFile.getSize());
        return ipfsOutVo;
    }

    @Override
    public FileByteOutVo getByHash(FileQueryVo fileQueryVo) {
        Multihash filePointer = Multihash.fromBase58(fileQueryVo.getHash());
        byte[] data = new byte[0];
        filePointer.getType();
        try {
            data = ipfsBuilder.build().cat(filePointer);
        } catch (IOException e) {
            LOGGER.error("从ipfs服务器下载获得文件报错:{}", e);
            throw new BusinessException(IpfsException.Proxy.IPFS_GET_ERROR);
        }
        return new FileByteOutVo(data);
    }
}
