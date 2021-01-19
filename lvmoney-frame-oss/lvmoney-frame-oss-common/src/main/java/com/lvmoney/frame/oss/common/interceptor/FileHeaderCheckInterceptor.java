package com.lvmoney.frame.oss.common.interceptor;/**
 * 描述:
 * 包名:com.lvmoney.demo.file.interceptor
 * 版本信息: 版本1.0
 * 日期:2020/8/19
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.base.core.util.SupportUtil;
import com.lvmoney.frame.base.interceptor.annotations.InterceptorBean;
import com.lvmoney.frame.oss.common.exception.OssException;
import com.lvmoney.frame.oss.common.prop.FileTypeProp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.lvmoney.frame.oss.common.constant.OssType.FILE_HEADER_LENGTH;
import static com.lvmoney.frame.oss.common.exception.OssException.Proxy.FILE_TYPE_NOT_REQUIRED;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/8/19 14:59
 */
@InterceptorBean
public class FileHeaderCheckInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileHeaderCheckInterceptor.class);

    @Autowired
    FileTypeProp fileTypeProp;

    @Value("${frame.fileType.support:false}")
    String frameSupport;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (!SupportUtil.support(frameSupport)) {
            throw new BusinessException(OssException.Proxy.FILE_TYPE_SUPPORT_ERROR);
        } else if (BaseConstant.SUPPORT_FALSE.equals(frameSupport)) {
            return super.preHandle(request, response, handler);
        }
        // 判断是否为文件上传请求
        if (request != null && request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> files = multipartRequest.getFileMap();
            Iterator<String> iterator = files.keySet().iterator();
            while (iterator.hasNext()) {
                String formKey = iterator.next();
                MultipartFile multipartFile = multipartRequest.getFile(formKey);
                String servletPath = request.getServletPath();
                byte[] file = multipartFile.getBytes();
                Map<String, String> fileSize = fileTypeProp.getFileSize();

                int fileSizeMax = ObjectUtils.isEmpty(fileSize.get(servletPath)) ? 0 : Integer.parseInt(fileSize.get(servletPath));
                if (fileSizeMax != 0 && file.length > Integer.parseInt(fileSize.get(servletPath))) {
                    throw new BusinessException(OssException.Proxy.FILE_SIZE_ERROR);
                }

                if (file.length > FILE_HEADER_LENGTH) {
                    //转成16进制
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < FILE_HEADER_LENGTH; i++) {
                        int v = file[i] & 0xFF;
                        String hv = Integer.toHexString(v);
                        if (hv.length() < 2) {
                            sb.append(0);
                        }
                        sb.append(hv);
                    }

                    boolean isFound = false;
                    String fileHead = sb.toString().toUpperCase();
                    Map<String, String> fileHeaderMap = fileTypeProp.getFileHeaderMap();
                    Map<String, String> urlFileTypeMap = fileTypeProp.getUrlFileTypeMap();

                    String urlFileType = urlFileTypeMap.get(servletPath);
                    if (StringUtils.isEmpty(urlFileType)) {
                        for (Map.Entry<String, String> entry : fileHeaderMap.entrySet()) {
                            if (fileHead.startsWith(entry.getValue())) {
                                isFound = true;
                                break;
                            }
                        }
                    } else {
                        String[] type = urlFileType.split(BaseConstant.CHAR_COMMA);
                        for (String s : type) {
                            String header = fileHeaderMap.get(s);
                            if (fileHead.startsWith(header)) {
                                isFound = true;
                                break;
                            }
                        }
                    }
                    String msg = StringUtils.isEmpty(urlFileType) ? FILE_TYPE_NOT_REQUIRED.getDescription() : String.format(FILE_TYPE_NOT_REQUIRED.getDescription() + ",被允许的文件类型:%s", urlFileType);
                    if (!isFound) {
                        responsePrint(response, FILE_TYPE_NOT_REQUIRED.getCode(), msg);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

    private void responsePrint(ServletResponse response, Integer status, String message) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(status);
        apiResult.setSuccess(false);
        apiResult.setMsg(message);
        apiResult.setDate(new Date());
        String json = JsonUtil.t2JsonString(apiResult);
        try {
            httpResponse.getWriter().print(json);
        } catch (IOException e) {
            LOGGER.error("其他错误处理response返回处理报错：{}", e);
        }
    }
}
