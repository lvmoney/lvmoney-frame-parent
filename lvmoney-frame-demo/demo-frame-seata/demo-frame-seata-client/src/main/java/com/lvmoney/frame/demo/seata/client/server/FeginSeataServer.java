package com.lvmoney.frame.demo.seata.client.server;

import com.lvmoney.frame.demo.seata.client.vo.req.UserReqVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@FeignClient(name = "fegin1", url = "http://localhost:8070")
public interface FeginSeataServer {

    /**
     * 分布式事务，save测试远程方法
     *
     * @param user: 用户
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:36
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    int save(UserReqVo user);

    /**
     * 分布式事务，update测试远程方法
     *
     * @param id: id
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:36
     */
    @RequestMapping(value = "/updateStage/{id}", method = RequestMethod.PUT)
    int update(@PathVariable(value = "id") String id);
}
