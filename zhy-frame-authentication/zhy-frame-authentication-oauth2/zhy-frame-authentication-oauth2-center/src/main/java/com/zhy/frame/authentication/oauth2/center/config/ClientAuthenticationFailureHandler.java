package com.zhy.frame.authentication.oauth2.center.config;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhy.frame.authentication.common.exception.AuthorityException;
import com.zhy.frame.authentication.oauth2.center.exception.CustomOauthException;
import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Component
public class ClientAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientAuthenticationFailureHandler.class);
    private String failureUrl = "/signIn";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException {
        boolean isAjax = "XMLHttpRequest".equals(request
                .getHeader("X-Requested-With")) || "apiLogin".equals(request
                .getHeader("api-login"));
        if (isAjax) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            try {
                ApiResult resultData = new ApiResult();
                resultData.setCode(AuthorityException.Proxy.AUTHENTICATION_EXCEPTION.getCode());
                resultData.setMsg(AuthorityException.Proxy.AUTHENTICATION_EXCEPTION.getDescription());
                resultData.setDate(new Date());
                resultData.setSuccess(false);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(response.getOutputStream(),
                        JsonEncoding.UTF8);
                objectMapper.writeValue(jsonGenerator, resultData);
            } catch (Exception ex) {
                LOGGER.error("不能够写入json信息:{}", ex);
                throw new CustomOauthException(AuthorityException.Proxy.DENIED_JSON_NOT_WRITE.getDescription());
            }
        } else {
            String encodedMessage = "";
            try {
                encodedMessage = URLEncoder.encode(exception.getMessage(), BaseConstant.CHARACTER_ENCODE_UTF8_UPPER);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("不支持的的编码错误:{}", e);
                throw new CustomOauthException(AuthorityException.Proxy.UNSUPPORTED_ENCODING_EXCEPTION.getDescription());
            }
            response.sendRedirect(failureUrl + "?authentication_error=true&error=" + encodedMessage);
            /*super.onAuthenticationFailure(request, response, exception);*/
        }
    }
}