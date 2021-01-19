package com.lvmoney.frame.cloud.base.controller;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.function
 * 版本信息: 版本1.0
 * 日期:2019/8/21
 * Copyright XXXXXX科技有限公司
 */


import ch.ethz.ssh2.Connection;
import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.cloud.base.util.ExecCmdResult;
import com.lvmoney.frame.cloud.base.util.Ssh2Util;
import com.lvmoney.frame.cloud.base.vo.req.SshExecReqVo;
import com.lvmoney.frame.cloud.base.vo.req.SshUploadFileReqVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lvmoney.frame.base.core.constant.BaseConstant.FILE_SEPARATOR;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/21 15:55
 */
@RestController
@RequestMapping(value = "/ssh")
public class SshController {
    /**
     * @describe:文件上传到linux指定目录
     * @param: [sshUploadFileReqVo]
     * @return: com.lvmoney.common.util.vo.ResultData<java.lang.Boolean>
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/21 16:29
     */
    @GetMapping(value = "/upload")
    public ApiResult<Boolean> upload(SshUploadFileReqVo sshUploadFileReqVo) {
        Ssh2Util.upload(this.getConnection(sshUploadFileReqVo.getHost(),
                sshUploadFileReqVo.getPort(),
                sshUploadFileReqVo.getUsername(),
                sshUploadFileReqVo.getPassword()),
                sshUploadFileReqVo.getToPath(), sshUploadFileReqVo.getFromPath() + FILE_SEPARATOR + sshUploadFileReqVo.getFromName());
        return ApiResult.success(true);
    }

    /**
     * @describe:在linux执行特定的命令行
     * @param: [sshExecReqVo]
     * @return: com.lvmoney.common.util.vo.ResultData<java.lang.Boolean>
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/21 16:30
     */
    @GetMapping(value = "/exec")
    public ApiResult<Boolean> exec(SshExecReqVo sshExecReqVo) {
        ExecCmdResult execCmdResult = Ssh2Util.execCommand(this.getConnection(sshExecReqVo.getHost(),
                sshExecReqVo.getPort(),
                sshExecReqVo.getUsername(),
                sshExecReqVo.getPassword()),
                sshExecReqVo.getExec());
        return ApiResult.success(execCmdResult.isSuccess());
    }

    private Connection getConnection(String host, int port, String username, String password) {
        return Ssh2Util.openConnection(host, port, username, password);
    }

}
