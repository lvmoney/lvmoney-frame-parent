/**
 * 描述:
 * 包名:com.zhy.router.function
 * 版本信息: 版本1.0
 * 日期:2018年12月29日  下午5:02:46
 * Copyright xxxx科技有限公司
 */

package com.zhy.frame.demo.mongdb.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.oss.common.vo.FileBaseOutVo;
import com.zhy.frame.oss.common.vo.FileBaseVo;
import com.zhy.frame.oss.common.vo.FileByteOutVo;
import com.zhy.frame.oss.common.vo.FileQueryVo;
import com.zhy.frame.oss.gridfs.mo.MongodbTestMo;
import com.zhy.frame.oss.gridfs.service.BaseGridFsService;
import com.zhy.frame.oss.gridfs.service.BaseMongoService;
import com.zhy.frame.oss.gridfs.vo.BaseMongoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 下午5:02:46
 * @RestController
 * @RequestMapping("mongo")
 */
@RestController
public class MongoController {
    @Autowired
    BaseMongoService baseMongoService;

    @Autowired
    BaseGridFsService baseGridFsService;
    @Value("${file.size.max:1000000}")
    String fileMaxSize;
    /**
     * 请求头User-Agent
     */
    private static final String USER_AGENT = "User-Agent";
    /**
     * MSIE
     */
    private static final String MSIE = "MSIE";
    /**
     * TRIDENT
     */
    private static final String TRIDENT = "TRIDENT";
    /**
     * EDGE
     */
    private static final String EDGE = "EDGE";

    @RequestMapping(value = "save", method = RequestMethod.GET)
    public void save() {
        MongodbTestMo mo = new MongodbTestMo();
        mo.setMid("123");
        mo.setName("MongodbTestModel");
        mo.setAge("22");
        BaseMongoVo bVo = new BaseMongoVo();
        bVo.setData(mo);
        baseMongoService.save(bVo);
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public FileBaseOutVo uploadFile(@RequestParam("file") MultipartFile multiportFile) throws Exception {
        FileBaseVo grd = new FileBaseVo();
        DBObject metaData = new BasicDBObject();
        metaData.put("createdDate", new Date());
        grd.setFile(multiportFile);
        grd.setData(metaData);
        grd.setMaxSize(Long.parseLong(fileMaxSize));
        return baseGridFsService.save(grd);
    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    @ResponseBody
    public void downLoadFile(@RequestParam(name = "file_id") String fileId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FileQueryVo fileQueryVo = new FileQueryVo();
        fileQueryVo.setFileId(fileId);
        FileByteOutVo fileByteOutVo = baseGridFsService.getByFileId(fileQueryVo);
        String fileName = fileByteOutVo.getFileName();
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
