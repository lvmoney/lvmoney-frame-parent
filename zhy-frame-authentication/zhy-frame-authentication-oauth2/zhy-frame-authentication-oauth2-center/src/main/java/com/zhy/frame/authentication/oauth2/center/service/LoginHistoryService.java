package com.zhy.frame.authentication.oauth2.center.service;

import com.zhy.frame.authentication.oauth2.center.vo.JsonObjects;
import com.zhy.frame.authentication.oauth2.center.vo.LoginHistoryVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface LoginHistoryService {
    /**
     * 分页获得历史信息
     *
     * @param username:  用户名
     * @param pageNum:   第几页
     * @param pageSize:  分页大小
     * @param sortField: 排序字段
     * @param sortOrder: 排序类型
     * @throws
     * @return: com.zhy.frame.authentication.oauth2.center.vo.JsonObjects<com.zhy.frame.authentication.oauth2.center.vo.LoginHistoryVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:51
     */
    JsonObjects<LoginHistoryVo> listByUsername(String username, int pageNum,
                                               int pageSize,
                                               String sortField,
                                               String sortOrder);

    /**
     * 创建历史信息
     *
     * @param loginHistoryVo: 历史信息实体
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:52
     */
    void asyncCreate(LoginHistoryVo loginHistoryVo);

}
