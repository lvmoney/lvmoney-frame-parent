package com.zhy.frame.authentication.oauth2.center.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhy.frame.authentication.oauth2.center.exception.Oauth2Exception;
import com.zhy.frame.base.core.api.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Component
public class ClientAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientAccessDeniedHandler.class);
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response, AccessDeniedException e)
            throws IOException {
        LOGGER.error("客户端授权失败报错:{}", e.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        ApiResult resultData = new ApiResult();
        resultData.setCode(Oauth2Exception.Proxy.OAUTH2_ACCESS_DENIED_ERROR.getCode());
        resultData.setDate(new Date());
        resultData.setSuccess(false);
        resultData.setMsg(Oauth2Exception.Proxy.OAUTH2_ACCESS_DENIED_ERROR.getDescription());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(resultData));
    }
}
