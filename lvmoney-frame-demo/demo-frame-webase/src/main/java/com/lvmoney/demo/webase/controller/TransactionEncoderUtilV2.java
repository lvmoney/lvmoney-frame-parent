package com.lvmoney.demo.webase.controller;

import com.webank.weid.config.FiscoConfig;
import com.webank.weid.service.BaseService;
import com.webank.weid.util.DataToolUtils;
import org.fisco.bcos.web3j.crypto.ExtendedRawTransaction;
import org.fisco.bcos.web3j.crypto.Sign;
import org.fisco.bcos.web3j.crypto.Sign.SignatureData;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.NodeVersion;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.rlp.RlpType;
import org.fisco.bcos.web3j.utils.Numeric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;

import static com.webank.weid.service.impl.CredentialPojoServiceImpl.generateSalt;

public class TransactionEncoderUtilV2 {

    private static Logger logger = LoggerFactory.getLogger(TransactionEncoderUtilV2.class);


    public static SignatureData simpleSignatureDeserialization(
            byte[] serializedSignatureData,
            SignType signType
    ) {
        if (65 != serializedSignatureData.length) {
            return null;
        }
        // Determine signature type
        Byte javav = serializedSignatureData[0];
        Byte lwcv = serializedSignatureData[64];
        byte[] r = new byte[32];
        byte[] s = new byte[32];
        SignatureData signatureData = null;
        if (signType == SignType.RSV) {
            // this is the signature from java client
            logger.info("Java Client signature checked.");
            System.arraycopy(serializedSignatureData, 1, r, 0, 32);
            System.arraycopy(serializedSignatureData, 33, s, 0, 32);
            signatureData = new SignatureData(javav, r, s);
        } else if (signType == SignType.VSR) {
            // this is the standard raw ecdsa sig method (go version client uses this)
            logger.info("Standard Client signature checked.");
            lwcv = (byte) (lwcv.intValue() + 27);
            System.arraycopy(serializedSignatureData, 0, r, 0, 32);
            System.arraycopy(serializedSignatureData, 32, s, 0, 32);
            signatureData = new SignatureData(lwcv, r, s);
        }
        return signatureData;
    }

    public static byte[] encode(
            ExtendedRawTransaction rawTransaction, Sign.SignatureData signatureData) {
        List<RlpType> values = asRlpValues(rawTransaction, signatureData);
        org.fisco.bcos.web3j.rlp.RlpList rlpList = new org.fisco.bcos.web3j.rlp.RlpList(values);
        return org.fisco.bcos.web3j.rlp.RlpEncoder.encode(rlpList);
    }


    static List<org.fisco.bcos.web3j.rlp.RlpType> asRlpValues(
            ExtendedRawTransaction rawTransaction, Sign.SignatureData signatureData) {
        List<org.fisco.bcos.web3j.rlp.RlpType> result = new ArrayList<>();
        result.add(org.fisco.bcos.web3j.rlp.RlpString.create(rawTransaction.getRandomid()));
        result.add(org.fisco.bcos.web3j.rlp.RlpString.create(rawTransaction.getGasPrice()));
        result.add(org.fisco.bcos.web3j.rlp.RlpString.create(rawTransaction.getGasLimit()));
        result.add(org.fisco.bcos.web3j.rlp.RlpString.create(rawTransaction.getBlockLimit()));
        // an empty to address (contract creation) should not be encoded as a numeric 0 value
        String to = rawTransaction.getTo();
        if (to != null && to.length() > 0) {
            // addresses that start with zeros should be encoded with the zeros included, not
            // as numeric values
            result.add(org.fisco.bcos.web3j.rlp.RlpString.create(org.fisco.bcos.web3j.utils.Numeric.hexStringToByteArray(to)));
        } else {
            result.add(org.fisco.bcos.web3j.rlp.RlpString.create(""));
        }

        result.add(org.fisco.bcos.web3j.rlp.RlpString.create(rawTransaction.getValue()));

        // value field will already be hex encoded, so we need to convert into binary first
        byte[] data = org.fisco.bcos.web3j.utils.Numeric.hexStringToByteArray(rawTransaction.getData());
        result.add(org.fisco.bcos.web3j.rlp.RlpString.create(data));

        // add extra data!!!

        result.add(org.fisco.bcos.web3j.rlp.RlpString.create(rawTransaction.getFiscoChainId()));
        result.add(org.fisco.bcos.web3j.rlp.RlpString.create(rawTransaction.getGroupId()));
        if (rawTransaction.getExtraData() == null) {
            result.add(org.fisco.bcos.web3j.rlp.RlpString.create(""));
        } else {
            result.add(
                    org.fisco.bcos.web3j.rlp.RlpString.create(org.fisco.bcos.web3j.utils.Numeric.hexStringToByteArray(rawTransaction.getExtraData())));
        }
        if (signatureData != null) {
            if (org.fisco.bcos.web3j.crypto.EncryptType.encryptType == 1) {
                result.add(org.fisco.bcos.web3j.rlp.RlpString.create(org.fisco.bcos.web3j.utils.Bytes.trimLeadingZeroes(signatureData.getPub())));
                // logger.debug("RLP-Pub:{},RLP-PubLen:{}",Hex.toHexString(signatureData.getPub()),signatureData.getPub().length);
                result.add(org.fisco.bcos.web3j.rlp.RlpString.create(org.fisco.bcos.web3j.utils.Bytes.trimLeadingZeroes(signatureData.getR())));
                // logger.debug("RLP-R:{},RLP-RLen:{}",Hex.toHexString(signatureData.getR()),signatureData.getR().length);
                result.add(org.fisco.bcos.web3j.rlp.RlpString.create(org.fisco.bcos.web3j.utils.Bytes.trimLeadingZeroes(signatureData.getS())));
                // logger.debug("RLP-S:{},RLP-SLen:{}",Hex.toHexString(signatureData.getS()),signatureData.getS().length);
            } else {
                result.add(org.fisco.bcos.web3j.rlp.RlpString.create(signatureData.getV()));
                result.add(org.fisco.bcos.web3j.rlp.RlpString.create(org.fisco.bcos.web3j.utils.Bytes.trimLeadingZeroes(signatureData.getR())));
                result.add(org.fisco.bcos.web3j.rlp.RlpString.create(org.fisco.bcos.web3j.utils.Bytes.trimLeadingZeroes(signatureData.getS())));
            }
        }
        return result;
    }

    public static ExtendedRawTransaction buildRawTransaction(String nonce, String groupId, String data, String to, BigInteger blockLimit) {
        ExtendedRawTransaction rawTransaction =
                ExtendedRawTransaction.createTransaction(
                        new BigInteger(nonce),
                        new BigInteger("99999999999"),
                        new BigInteger("99999999999"),
                        blockLimit,
                        to, // to address
                        BigInteger.ZERO, // value to transfer
                        data,
                        getChainIdV2(), // chainId
                        new BigInteger(groupId), // groupId
                        null);
        return rawTransaction;
    }

    /**
     * Get the chainId for FISCO-BCOS v2.x chainId. Consumed by Restful API service.
     *
     * @return chainId in BigInt.
     */
    public static BigInteger getChainIdV2() {
        try {
//            NodeVersion.Version nodeVersion = ((org.fisco.bcos.web3j.protocol.Web3j) BaseService
//                    .getWeb3j()).getNodeVersion().send().getNodeVersion();
//            String chainId = nodeVersion.getChainID();
            return new BigInteger("1");
        } catch (Exception e) {
            return BigInteger.ONE;
        }
    }

    public static String createTxnHex(
            String encodedSig,
            String nonce,
            String to,
            String data,
            String blockLimit,
            SignType signType
    ) {
        SignatureData sigData = TransactionEncoderUtilV2
                .simpleSignatureDeserialization(DataToolUtils.base64Decode(encodedSig.getBytes()), signType);
        FiscoConfig fiscoConfig = new FiscoConfig();
        fiscoConfig.load();
        ExtendedRawTransaction rawTransaction = TransactionEncoderUtilV2.buildRawTransaction(nonce,
                "1", data, to, new BigInteger(blockLimit));
        byte[] encodedSignedTxn = TransactionEncoderUtilV2.encode(rawTransaction, sigData);
        return Numeric.toHexString(encodedSignedTxn);
    }

}
