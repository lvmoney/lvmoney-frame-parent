package com.lvmoney.frame.blockchain.webase.wallet.api.surface;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.wallet.api.surface
 * 版本信息: 版本1.0
 * 日期:2021/6/26
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.wallet.api.ao.HandleWithSignAo;
import com.lvmoney.frame.blockchain.webase.wallet.api.constant.WalletConstant;
import com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/26 17:43
 */
public interface ICoin {
    /**
     * 通过此接口对合约进行调用，前置根据调用的合约方法是否是“constant”方法区分返回信息，“constant”方法为查询，返回要查询的信息。非“constant”方法为发送数据上链，返回块hash、块高、交易hash等信息。
     * <p>
     * 当合约方法为非“constant”方法，要发送数据上链时，此接口需结合WeBASE-Sign使用。通过调用WeBASE-Sign服务的签名接口让相关用户对数据进行签名，拿回签名数据再发送上链。需要调用此接口时，工程配置文件application.yml中的配置”keyServer”需配置WeBASE-Sign服务的ip和端口，并保证WeBASE-Sign服务正常和存在相关用户。
     * 有参数返回，直接返回参数值，例如总金额
     * @param handleWithSignAo:
     * @throws
     * @return: HandleWithSignVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/23 20:48
     */
    @PostMapping(value = WalletConstant.URL_FRONT_TRANS_HANDLE_WITH_SIGN)
    List coin(@RequestBody HandleWithSignAo handleWithSignAo);

    /**
     * 通过此接口对合约进行调用，前置根据调用的合约方法是否是“constant”方法区分返回信息，“constant”方法为查询，返回要查询的信息。非“constant”方法为发送数据上链，返回块hash、块高、交易hash等信息。
     * <p>
     * 当合约方法为非“constant”方法，要发送数据上链时，此接口需结合WeBASE-Sign使用。通过调用WeBASE-Sign服务的签名接口让相关用户对数据进行签名，拿回签名数据再发送上链。需要调用此接口时，工程配置文件application.yml中的配置”keyServer”需配置WeBASE-Sign服务的ip和端口，并保证WeBASE-Sign服务正常和存在相关用户。
     * 没有参数返回时，会返回交易全量的数据
     * @param handleWithSignAo:
     * @throws
     * @return: HandleWithSignVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/23 20:48
     */
    @PostMapping(value = WalletConstant.URL_FRONT_TRANS_HANDLE_WITH_SIGN)
    HandleWithSignVo handleWithSign(@RequestBody HandleWithSignAo handleWithSignAo);
}
