package com.lvmoney.frame.dispatch.feign.server;

import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.dispatch.feign.config.FeignConfig;
import com.lvmoney.frame.dispatch.feign.vo.req.LoginVoReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @describe：请求第三方ip:port
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 * @RequestMapping(produces="application/json;charset=UTF-8")
 */
@FeignClient(name = "fegin", url = "http://localhost:8071", configuration = FeignConfig.class)
public interface FeginConfigApi {

    /**
     * 类似postman表单的形式提交，接收参数实体不用@RequsetBody修饰
     *
     * @param loginVoReq: 登录请求实体
     * @throws
     * @return: com.lvmoney.k8s.feign.vo.resp.CommonVo
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:32
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    ApiResult login(LoginVoReq loginVoReq);

    /**
     * 类似postmanjson形式提交，接收参数实体用@RequsetBody修饰
     *
     * @param loginVoReq: 登录请求实体
     * @throws
     * @return: com.lvmoney.k8s.feign.vo.resp.CommonVo
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:32
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ApiResult login2(LoginVoReq loginVoReq);

    /**
     * rest 风格
     *
     * @param id: 请求id
     * @throws
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:33
     */
    @RequestMapping(value = "/patient/{id}", method = RequestMethod.PUT)
    Map<String, Object> updatePatientInfo(@PathVariable(value = "id") String id);


    /**
     * 测试
     *
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:34
     */
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    String jaeger();

}
