package com.lvmoney.frame.blockchain.webase.trans.api;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.trans.api
 * 版本信息: 版本1.0
 * 日期:2021/6/24
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.trans.ao.CallAo;
import com.lvmoney.frame.blockchain.webase.trans.ao.SendAo;
import com.lvmoney.frame.blockchain.webase.trans.constant.TransConstant;
import com.lvmoney.frame.blockchain.webase.trans.vo.TransResult;
import org.springframework.web.bind.annotation.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/24 19:13
 */
public interface ITrans {
    /**
     * 调用此接口发送无状态交易请求，交易服务子系统会将交易请求信息缓存到数据库，通过轮询服务向节点发送交易请求，确保交易成功上链。当部署业务流水号为空时（即不是调用交易子系统部署合约），合约地址和abi不能为空。
     *
     * @param sendAo:
     * @throws
     * @return: com.lvmoney.frame.blockchain.webase.trans.vo.TransResult
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/24 19:16
     */
    @PostMapping(value = TransConstant.URL_TRANS_SEND)
    TransResult transSend(@RequestBody SendAo sendAo);

    /**
     * 调用此接口同步从节点查询交易信息。当部署业务流水号为空时（即不是调用交易子系统部署合约），合约地址和abi不能为空。
     *
     * @param callAo:
     * @throws
     * @return: com.lvmoney.frame.blockchain.webase.trans.vo.TransResult
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/24 19:21
     */
    @PostMapping(value = TransConstant.URL_TRANS_CALL)
    TransResult transCall(@RequestBody CallAo callAo);
}
