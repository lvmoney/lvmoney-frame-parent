package com.lvmoney.frame.blockchain.webase.front.api.surface;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.front.api.surface
 * 版本信息: 版本1.0
 * 日期:2021/6/23
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.front.api.ao.DecodeAo;
import com.lvmoney.frame.blockchain.webase.front.api.ao.HandleWithSignAo;
import com.lvmoney.frame.blockchain.webase.front.api.constant.FrontConstant;
import com.lvmoney.frame.blockchain.webase.front.api.vo.*;
import org.springframework.web.bind.annotation.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/23 15:02
 */
public interface IFront {

    /**
     * 1、会在webasesign库tb_user表中生成用户数据
     * 2、在webase管理页面私钥管理模块无法查看到用户信息
     * 3、若想在webase管理页面私钥管理页面查看用户信息需将对应数据拷贝到webbasenodemanager库表tb_user中(节点管理接口：http://192.168.0.35:5001/WeBASE-Node-Manager/user/userInfo能完成该操作，但是该操作需用用户登录（必须图形验证码校验）获得token，通过接口直接获得token难完成)，通过自定义接口操作数据库表复制数据即可
     * 4、完成3的操作后可在私钥管理模块查看用户信息也可以在交易信息模块 from处查看到对应的signUserId
     *
     * @param appId:appId
     * @param signUserId:signUserId
     * @param type:0-本地用户；1-本地随机；2-外部用户
     * @param userName:用户名
     * @param returnPrivateKey:是否返回私钥，true:返回，false：不返回
     * @throws
     * @return: PrivateKeyVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/23 17:49
     */
    @GetMapping(value = FrontConstant.URL_FRONT_PRIVATE_KEY)
    PrivateKeyVo privateKey(@RequestParam("appId") String appId, @RequestParam("signUserId") String signUserId, @RequestParam("type") int type, @RequestParam("userName") String userName, @RequestParam("returnPrivateKey") boolean returnPrivateKey);

    /**
     * 根据交易hash获取交易信息
     *
     * @param groupId:
     * @param transHash:
     * @throws
     * @return: Web3TransactionVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/23 15:53
     */
    @GetMapping(value = FrontConstant.URL_FRONT_WEB3_TRANSACTION)
    Web3TransactionVo web3Transaction(@PathVariable("groupId") int groupId, @PathVariable("transHash") String transHash);


    /**
     * 根据交易hash获取交易信息
     *
     * @param groupId:
     * @param blockHash:
     * @throws
     * @return: Web3TransactionVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/23 15:53
     */
    @GetMapping(value = FrontConstant.URL_FRONT_WEB3_BLOCK_BY_HASH)
    Web3BlockByHashVo web3blockByHash(@PathVariable("groupId") int groupId, @PathVariable("blockHash") String blockHash);

    /**
     * 通过此接口对合约进行调用，前置根据调用的合约方法是否是“constant”方法区分返回信息，“constant”方法为查询，返回要查询的信息。非“constant”方法为发送数据上链，返回块hash、块高、交易hash等信息。
     * <p>
     * 当合约方法为非“constant”方法，要发送数据上链时，此接口需结合WeBASE-Sign使用。通过调用WeBASE-Sign服务的签名接口让相关用户对数据进行签名，拿回签名数据再发送上链。需要调用此接口时，工程配置文件application.yml中的配置”keyServer”需配置WeBASE-Sign服务的ip和端口，并保证WeBASE-Sign服务正常和存在相关用户。
     *
     * @param handleWithSignAo:
     * @throws
     * @return: HandleWithSignVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/23 20:48
     */
    @PostMapping(value = FrontConstant.URL_FRONT_TRANS_HANDLE_WITH_SIGN)
    HandleWithSignVo handleWithSign(@RequestBody HandleWithSignAo handleWithSignAo);

    /**
     * 根据合约ABI，解析交易回执中返回的input/output值
     *
     * @param decodeAo:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/24 10:07
     */
    @PostMapping(value = FrontConstant.URL_FRONT_TOOL_DECODE)
    String toolDecode(@RequestBody DecodeAo decodeAo);
}
