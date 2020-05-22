package com.zhy.frame.ipfs.node.controller;/**
 * 描述:
 * 包名:com.zhy.frame.ipfs.node.function
 * 版本信息: 版本1.0
 * 日期:2020/5/6
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.ipfs.common.service.IpfsCommonService;
import com.zhy.frame.ipfs.common.vo.FileByteOutVo;
import com.zhy.frame.ipfs.common.vo.FileQueryVo;
import com.zhy.frame.ipfs.common.vo.IpfsSaveVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zhy.frame.base.core.constant.BaseConstant.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/6 11:52
 */
@RestController
@RequestMapping("ipfs")
public class IpfsController {

    @Autowired
    IpfsCommonService ipfsCommonService;


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload2(MultipartFile file) throws IOException {
        return JsonUtil.t2JsonString(ipfsCommonService.save(new IpfsSaveVo(file)));
    }


    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download2(@RequestParam(name = "hash") String hash, @RequestParam(name = "name") String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        FileQueryVo fileQueryVo = new FileQueryVo();
        fileQueryVo.setHash(hash);
        String fileName = name;
        FileByteOutVo fileByteOutVo = ipfsCommonService.getByHash(fileQueryVo);
        if (request.getHeader(USER_AGENT).toUpperCase().contains(MSIE) ||
                request.getHeader(USER_AGENT).toUpperCase().contains(TRIDENT)
                || request.getHeader(USER_AGENT).toUpperCase().contains(EDGE)) {
            fileName = java.net.URLEncoder.encode(fileName, BaseConstant.CHARACTER_ENCODE_UTF8_UPPER);
        } else {
            //非IE浏览器的处理：
            fileName = new String(fileName.getBytes(BaseConstant.CHARACTER_ENCODE_UTF8_UPPER), BaseConstant.CHARACTER_ENCODE_ISO88591);
        }
        // 通知浏览器进行文件下载

        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        response.getOutputStream().write(fileByteOutVo.getFileByte());
    }


}
