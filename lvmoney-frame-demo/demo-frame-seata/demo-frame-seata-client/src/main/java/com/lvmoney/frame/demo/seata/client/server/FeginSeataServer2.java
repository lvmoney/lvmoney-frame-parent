package com.lvmoney.frame.demo.seata.client.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@FeignClient(name = "fegin2", url = "http://localhost:8072")
public interface FeginSeataServer2 {
    /**
     * 分布式事务更新测试
     *
     * @param id: id
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:37
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    int update(@PathVariable(value = "id") String id);
}
