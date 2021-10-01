package com.lvmoney.demo.webase.controller;/**
 * 描述:
 * 包名:com.lvmoney.demo.webase.controller
 * 版本信息: 版本1.0
 * 日期:2021/6/30
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.demo.webase.feign.IRestServiceClient;
import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.blockchain.webase.weidentity.api.ao.EncodeAo;
import com.lvmoney.frame.blockchain.webase.weidentity.api.ao.RequestAo;
import com.lvmoney.frame.blockchain.webase.weidentity.api.ao.TransactionArg;
import com.lvmoney.frame.blockchain.webase.weidentity.api.constant.WeidConstant;
import com.lvmoney.frame.blockchain.webase.weidentity.api.vo.ResponseVo;
import com.lvmoney.frame.blockchain.webase.weidentity.common.ro.WeidRo;
import com.lvmoney.frame.blockchain.webase.weidentity.common.service.WeidService;
import com.lvmoney.frame.blockchain.webase.weidentity.common.util.WeidUtil;
import com.webank.weid.util.DataToolUtils;
import org.bouncycastle.util.encoders.Base64;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.crypto.Sign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/30 19:42
 */
@RestController
public class WeidController {
    @Autowired
    IRestServiceClient iWeIdClient;
    @Autowired
    WeidService weidService;
    private static final Logger LOGGER = LoggerFactory.getLogger(WeidController.class);

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
    public ApiResult<ResponseVo> encode(@RequestBody RequestAo<EncodeAo> requestAo) {
        try {
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();
            BigInteger publicKey = ecKeyPair.getPublicKey();
            BigInteger privateKey = ecKeyPair.getPrivateKey();
            BigInteger nonce = WeidUtil.getNonce();
            weidService.saveSignRo2redis(new WeidRo(String.valueOf(nonce), String.valueOf(privateKey), String.valueOf(publicKey)));
            requestAo.setFunctionName(WeidConstant.FUNC_CREATE_WE_ID);
            TransactionArg transactionArg = new TransactionArg();
            transactionArg.setNonce(String.valueOf(nonce));
            EncodeAo encodeAo = new EncodeAo();
            encodeAo.setPublicKey(String.valueOf(publicKey));
            requestAo.setFunctionArg(encodeAo);
            requestAo.setTransactionArg(transactionArg);
            System.out.println(JsonUtil.t2JsonString(requestAo));
            ResponseVo responseVo = iWeIdClient.encode(requestAo);
            return ApiResult.success(responseVo);
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return ApiResult.error(100, "error");

    }

    @PostMapping(value = WeidConstant.URL_WEID_API_TRANSACT)
    public ApiResult<ResponseVo> transact(@RequestBody RequestAo requestAo) {
        BigInteger nonce = new BigInteger(requestAo.getTransactionArg().getNonce());

        WeidRo weidRo = weidService.getByNonce(String.valueOf(nonce));
        requestAo.setFunctionName(WeidConstant.FUNC_CREATE_WE_ID);
        TransactionArg transactionArg = new TransactionArg();
        transactionArg.setNonce(String.valueOf(nonce));
        transactionArg.setData(requestAo.getTransactionArg().getData());
        String signedMessage = requestAo.getTransactionArg().getSignedMessage();
        try {
            String base64SignedMsg = getBase64SignedMsg(signedMessage, new BigInteger(weidRo.getPrivateKey()), new BigInteger(weidRo.getPublicKey()));
            transactionArg.setSignedMessage(base64SignedMsg);
            transactionArg.setBlockLimit(requestAo.getTransactionArg().getBlockLimit());
            transactionArg.setSignType("1");
            requestAo.setTransactionArg(transactionArg);
            String data = JsonUtil.removeDoubleQuotes(requestAo.getTransactionArg().getData());
            String txnHex = TransactionEncoderUtilV2
                    .createTxnHex(signedMessage, nonce.toString(), "0xd72019d2bdf18971ee8ea5690ea2be141c09a7a1", data, requestAo.getTransactionArg().getBlockLimit(), SignType.getSignTypeByCode(1));
            System.out.println("test");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return ApiResult.success(iWeIdClient.transact(requestAo));

    }


    /**
     * 获得加密后base64的值
     *
     * @param encodedTransaction:
     * @param privateKey:
     * @param publicKey:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/2 15:19
     */
    private String getBase64SignedMsg(String encodedTransaction, BigInteger privateKey, BigInteger publicKey) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        byte[] encodedTransactionClient = DataToolUtils
                .base64Decode(encodedTransaction.getBytes());
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        Sign.SignatureData clientSignedData = Sign.getSignInterface().signMessage(encodedTransactionClient, ecKeyPair);
        String base64SignedMsg = new String(
                DataToolUtils.base64Encode(WeidUtil.simpleSignatureSerialization(clientSignedData)));

        return base64SignedMsg;
    }

    @Test
    public void testClientSign() {
        BigInteger priv = new BigInteger("1113",
                10);
        ECKeyPair ecKeyPair = ECKeyPair.create(priv);
        String rawData = "+QGYiAoqvdSrq50MhRdIduf/hRdIduf/ggQglFl9kvCEDRPm7TPTzjYkiy3r5p5JgLkBZGvzCg0AAAAAAAAAAAAAAAASqNtWmYMdG8x4pGCkn3Z0a0trLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAXkT+MQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB5MTA4MTkzNTI4NjAyODUxMzgxMzc3NjA4NDQ0MjE3NDI4ODEwMjAzOTIxODc4OTEyMjk2ODE1MDI0MDQ5MjA3NDUwMjYwNzA1NTA0OTM4LzB4MTJhOGRiNTY5OTgzMWQxYmNjNzhhNDYwYTQ5Zjc2NzQ2YjRiNmIyYwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACjE1ODE1Nzk4MjUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQGA";
        byte[] encodedTransactionClient = Base64.decode(rawData.getBytes());
        Sign.SignatureData clientSignedData = Sign.getSignInterface().signMessage(encodedTransactionClient, ecKeyPair);
        String base64SignedMsg = new String(
                Base64.encode(WeidUtil.simpleSignatureSerialization(clientSignedData)));
        System.out.println(base64SignedMsg);
    }


}
