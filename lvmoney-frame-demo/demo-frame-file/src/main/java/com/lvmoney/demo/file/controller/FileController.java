package com.lvmoney.demo.file.controller;/**
 * 描述:
 * 包名:com.lvmoney.demo.file.controller
 * 版本信息: 版本1.0
 * 日期:2020/8/19
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.demo.file.vo.Long2StringVo;
import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.office.excel.util.ExcelUtil;
import com.lvmoney.frame.office.excel.vo.PersonExportVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/8/19 13:35
 */
@RestController
public class FileController {
    @PostMapping(value = "frame/file/add")
    public ApiResult<String> testJwt(@RequestParam("file") MultipartFile multipartFile) {

        return ApiResult.success("file");
    }

    @PostMapping(value = "frame/file/error")
    public ApiResult<Long2StringVo> error() {
        Long2StringVo long2StringVo = new Long2StringVo();
        long2StringVo.setAge(200L);
        long2StringVo.setSex(400L);
        return ApiResult.success("file");
    }

        @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response) {
        long start = System.currentTimeMillis();
        List<PersonExportVo> personList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PersonExportVo personVo = new PersonExportVo();
            personVo.setName("张三" + i);
            personVo.setUsername("张三" + i);
            personVo.setPhoneNumber("18888888888");
            personVo.setImageUrl("");
            personList.add(personVo);
        }
        ExcelUtil.exportExcel(personList, "员工信息表", "员工信息", PersonExportVo.class, "员工信息.xls", response);
    }
}
