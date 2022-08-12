/**
 * 描述:
 * 包名:com.lvmoney.frame.oss.common.service
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  上午11:54:23
 * Copyright XXXXXX科技有限公司
 */

package com.lvmoney.frame.oss.common.service;

import com.lvmoney.frame.oss.common.vo.FileBaseOutVo;
import com.lvmoney.frame.oss.common.vo.FileBaseVo;
import com.lvmoney.frame.oss.common.vo.FileByteOutVo;
import com.lvmoney.frame.oss.common.vo.FileQueryVo;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2019年1月10日 上午11:54:23
 */

public interface OssService {
    /**
     * 保存文件
     *
     * @param fileBaseVo: 基础文件存储实体
     * @return: com.lvmoney.mongo.vo.FileBaseOutVo
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:31
     */
    FileBaseOutVo save(FileBaseVo fileBaseVo);

    /**
     * 批量保存
     *
     * @param fileBaseVos:
     * @throws
     * @return: java.util.List<com.lvmoney.frame.oss.common.vo.FileBaseOutVo>
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/21 18:15
     */
    List<FileBaseOutVo> batchSave(List<FileBaseVo> fileBaseVos);

    /**
     * 获取单个文件
     *
     * @param fileQueryVo:
     * @throws
     * @return: com.lvmoney.frame.oss.common.vo.FileByteOutVo
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/21 18:17
     */
    FileByteOutVo getByFileId(FileQueryVo fileQueryVo);

    /**
     * 批量获得文件
     *
     * @param fileQueryVos:
     * @throws
     * @return: java.util.List<com.lvmoney.frame.oss.common.vo.FileByteOutVo>
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/21 18:17
     */
    List<FileByteOutVo> batchGetByFileId(List<FileQueryVo> fileQueryVos);


    /**
     * 删除指定文件
     *
     * @param fileQueryVo:
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/21 18:18
     */
    void deleteByFileId(FileQueryVo fileQueryVo);

}
