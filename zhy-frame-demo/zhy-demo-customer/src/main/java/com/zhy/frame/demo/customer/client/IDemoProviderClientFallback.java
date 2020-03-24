package com.zhy.frame.demo.customer.client;

import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.dispatch.feign.util.FrameThrowableUtil;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Component
public class IDemoProviderClientFallback implements FallbackFactory<IDemoProviderClient> {
    @Override
    public IDemoProviderClient create(Throwable cause) {

        return new IDemoProviderClient() {
            @Override
            public ApiResult<String> fallback(String name) {
                BusinessException businessException = FrameThrowableUtil.throwable2BusinessException(cause);
                return ApiResult.error(businessException.getCode(), businessException.getMessage());
            }

            @Override
            public ApiResult<String> hystrix(String name) {
                return null;
            }
        };
    }
}
