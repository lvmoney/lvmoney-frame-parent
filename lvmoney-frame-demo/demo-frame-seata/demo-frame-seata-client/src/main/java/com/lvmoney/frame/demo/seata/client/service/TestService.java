package com.lvmoney.frame.demo.seata.client.service;


import com.lvmoney.frame.demo.seata.client.vo.req.UpdateReqVo;
import com.lvmoney.frame.demo.seata.client.vo.req.UserReqVo;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface TestService {
    boolean seataService(UpdateReqVo updateReqVo);

    int save(UserReqVo userReqVo);
}
