package com.lvmoney.frame.demo.seata.client.service.impl;

import com.lvmoney.frame.demo.seata.client.server.FeginSeataServer;
import com.lvmoney.frame.demo.seata.client.server.FeginSeataServer2;
import com.lvmoney.frame.demo.seata.client.service.TestService;
import com.lvmoney.frame.demo.seata.client.vo.req.UpdateReqVo;
import com.lvmoney.frame.demo.seata.client.vo.req.UserReqVo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    FeginSeataServer feginSeataServer;
    @Autowired
    FeginSeataServer2 feginSeataServer2;

    @Override
    @GlobalTransactional
    public boolean seataService(UpdateReqVo updateReqVo) {
        try {
            int v1 = feginSeataServer.update(updateReqVo.getSUserId());
            int v2 = feginSeataServer2.update(updateReqVo.getAUserId());
        } catch (Exception e) {
            return false;
        }
        throw new RuntimeException();
        // return true;

    }

    @Override
    @GlobalTransactional
    public int save(UserReqVo userReqVo) {
        return feginSeataServer.save(userReqVo);
    }
}
