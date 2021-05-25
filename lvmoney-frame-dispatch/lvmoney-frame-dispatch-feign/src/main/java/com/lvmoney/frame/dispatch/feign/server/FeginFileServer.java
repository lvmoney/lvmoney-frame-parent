package com.lvmoney.frame.dispatch.feign.server;

import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.dispatch.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@FeignClient(name = "feginFile", url = "http://localhost:8093", configuration = FeignConfig.class)
public interface FeginFileServer {
    /**
     * 通过fegin的文件上传
     *
     * @param multiportFile:文件上传MultipartFile对象
     * @throws
     * @return: com.lvmoney.k8s.feign.vo.resp.CommonVo
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:34
     */
    @RequestMapping(value = "/mongo/uploadFile", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResult uploadFile(@RequestPart("file") MultipartFile multiportFile);
}
