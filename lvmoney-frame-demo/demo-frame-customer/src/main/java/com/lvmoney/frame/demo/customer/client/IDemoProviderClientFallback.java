package com.lvmoney.frame.demo.customer.client;

import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.dispatch.feign.util.FrameThrowableUtil;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
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

            @Override
            public ApiResult<String> test(String name) {
                return null;
            }
        };
    }
}
