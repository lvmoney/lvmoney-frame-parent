package com.lvmoney.frame.blockchain.webase.weidentity.api.surface;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.api.surface
 * 版本信息: 版本1.0
 * 日期:2021/6/30
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.weidentity.api.ao.EncodeAo;
import com.lvmoney.frame.blockchain.webase.weidentity.api.ao.RequestAo;
import com.lvmoney.frame.blockchain.webase.weidentity.api.constant.WeidConstant;
import com.lvmoney.frame.blockchain.webase.weidentity.api.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @describe：废物点心，接口文档和源代码不一致，查看了源代码，提交的数据和配置又不对应，暂时放弃封装，浪费了大量时间通过源代码获得接口文档的内容
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/30 19:35
 */
@Deprecated
public interface IRestService {
    /**
     * 第一次是轻客户端提供接口参数发给RestService服务端，后者进行组装、编码区块链原始交易串并返回
     *
     * @param requestAo:
     * @throws
     * @return: com.lvmoney.frame.blockchain.webase.weidentity.api.vo.ResponseVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/30 19:38
     */
    @PostMapping(value = WeidConstant.URL_WEID_API_ENCODE)
    ResponseVo encode(@RequestBody RequestAo<EncodeAo> requestAo);

    /**
     * 二次是轻客户端在本地使用自己的私钥，对原始交易串进行符合ECDSA的sha3签名，发给RestService服务端，后者打包并直接执行交易
     *
     * @param requestAo:
     * @throws
     * @return: com.lvmoney.frame.blockchain.webase.weidentity.api.vo.ResponseVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/30 19:38
     */
    @PostMapping(value = WeidConstant.URL_WEID_API_TRANSACT)
    ResponseVo transact(@RequestBody RequestAo requestAo);
}
