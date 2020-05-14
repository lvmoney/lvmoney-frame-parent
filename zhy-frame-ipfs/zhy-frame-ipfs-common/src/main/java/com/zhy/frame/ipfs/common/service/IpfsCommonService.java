package com.zhy.frame.ipfs.common.service;/**
 * 描述:
 * 包名:com.zhy.frame.ipfs.common.service
 * 版本信息: 版本1.0
 * 日期:2020/5/6
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.ipfs.common.vo.FileByteOutVo;
import com.zhy.frame.ipfs.common.vo.FileQueryVo;
import com.zhy.frame.ipfs.common.vo.IpfsOutVo;
import com.zhy.frame.ipfs.common.vo.IpfsSaveVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/6 18:09
 */
public interface IpfsCommonService {
    /**
     * 文件存储
     *
     * @param ipfsSaveVo:
     * @throws
     * @return: com.zhy.frame.ipfs.common.vo.IpfsOutVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/7 8:48
     */
    IpfsOutVo save(IpfsSaveVo ipfsSaveVo);

    /**
     * 获取文件
     *
     * @param fileQueryVo:
     * @throws
     * @return: com.zhy.frame.ipfs.common.vo.FileByteOutVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/7 8:57
     */
    FileByteOutVo getByHash(FileQueryVo fileQueryVo);

}
