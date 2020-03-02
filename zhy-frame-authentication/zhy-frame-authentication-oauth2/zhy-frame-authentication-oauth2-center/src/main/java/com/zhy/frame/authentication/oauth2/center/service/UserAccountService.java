package com.zhy.frame.authentication.oauth2.center.service;

import com.zhy.frame.authentication.oauth2.center.vo.JsonObjects;
import com.zhy.frame.authentication.oauth2.center.vo.UserAccountVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface UserAccountService {
    /**
     * 分页获得用户信息
     *
     * @param username:  用户名
     * @param pageNum:   第几页
     * @param pageSize:  页大小
     * @param sortField: 排序字段
     * @param sortOrder: 排序类型
     * @throws
     * @return: com.zhy.frame.authentication.oauth2.center.vo.JsonObjects<com.zhy.frame.authentication.oauth2.center.vo.UserAccountVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:52
     */
    JsonObjects<UserAccountVo> listByUsername(String username,
                                              int pageNum,
                                              int pageSize,
                                              String sortField,
                                              String sortOrder);

    /**
     * 获取用户信息
     *
     * @param username: 用户名
     * @throws
     * @return: com.zhy.frame.authentication.oauth2.center.vo.UserAccountVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:53
     */
    UserAccountVo findByUsername(String username);

    /**
     * 登录成功操作
     *
     * @param username: 用户名
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:53
     */
    void loginSuccess(String username);

    /**
     * 登录失败操作
     *
     * @param username: 用户名
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:54
     */
    void loginFailure(String username);

    /**
     * 创建账号
     *
     * @param userAccountVo: 用户实体
     * @throws
     * @return: com.zhy.frame.authentication.oauth2.center.vo.UserAccountVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:54
     */
    UserAccountVo create(UserAccountVo userAccountVo);

    /**
     * 通过id检索用户
     *
     * @param id: id
     * @throws
     * @return: com.zhy.frame.authentication.oauth2.center.vo.UserAccountVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:54
     */
    UserAccountVo retrieveById(long id);

    /**
     * 更新用户
     *
     * @param userAccountVo: 用户实体
     * @throws
     * @return: com.zhy.frame.authentication.oauth2.center.vo.UserAccountVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:55
     */
    UserAccountVo updateById(UserAccountVo userAccountVo);

    /**
     * 更新状态
     *
     * @param id:           id
     * @param recordStatus: 状态
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:55
     */
    void updateRecordStatus(long id, int recordStatus);
}
