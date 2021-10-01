package com.lvmoney.frame.core.util;

import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.core.vo.EcdsaVo;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static com.lvmoney.frame.base.core.constant.BaseConstant.EEC_FACTORY_TYPE;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class EcdsaUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(EcdsaUtil.class);
    private static final String PROVIDER = "BC";

    /**
     * 通过密钥加密
     *
     * @param resouce:
     * @param factoryType:
     * @param privateKey:
     * @param signType:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/19 16:03
     */
    public static String getEcdsaSign(String resouce, String factoryType, String privateKey,
                                      String signType) {
        try {
            PrivateKey pri = buildEcPrivateKey(privateKey, factoryType);
            Signature signature = Signature.getInstance(signType);
            signature.initSign(pri);
            signature.update(resouce.getBytes());
            byte[] result = signature.sign();
            return Hex.encodeHexString(result);
        } catch (Exception e) {
            LOGGER.error("通过密钥加密:{}", e);
            return "";
        }
    }


    /**
     * 公钥校验
     *
     * @param res         明文
     * @param sign        私钥加密后的密文
     * @param factoryType 工厂类型
     * @param publicKey   公钥
     * @param signType    加密类型
     * @return 2018年10月10日下午1:47:03
     * @author: lvmoney /XXXXXX科技有限公司
     */
    public static boolean verifyEcdsa(String res, String sign, String factoryType, String publicKey,
                                      String signType) {
        try {
            PublicKey pubKey = bulidEcPublicKey(publicKey, factoryType);
            Signature signature = Signature.getInstance(signType);
            signature.initVerify(pubKey);
            signature.update(res.getBytes());
            byte[] result = StringUtil.hexToByte(sign);
            boolean bool = signature.verify(result);
            return bool;
        } catch (Exception e) {
            LOGGER.error("Ecdsa公钥校验:{}", e);
            return false;
        }
    }


    /**
     * 获取私钥
     *
     * @param content
     * @param algorithm
     * @return
     * @throws Exception
     */
    public static PrivateKey buildEcPrivateKey(String content, String algorithm) throws Exception {
        byte[] asBytes = Base64Util.decode(content);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(asBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePrivate(spec);
    }


    /**
     * 获取公钥
     *
     * @param content   文件存储的key
     * @param algorithm factory Type
     * @return
     * @throws Exception
     */
    public static PublicKey bulidEcPublicKey(String content, String algorithm) throws Exception {
        byte[] asBytes = Base64Util.decode(content);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(asBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return (ECPublicKey) keyFactory.generatePublic(spec);
    }

    /**
     * @param factoryType
     * @return 2018年10月10日下午2:09:26
     * @describe:获得密钥和公钥
     * @author: lvmoney /XXXXXX科技有限公司
     */
    public static EcdsaVo getSecretKey(String factoryType) {
        EcdsaVo result = new EcdsaVo();
        try {
            KeyPairGenerator keyPairGenerator;
            keyPairGenerator = KeyPairGenerator.getInstance(factoryType);
            keyPairGenerator.initialize(256);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey pubKey = keyPair.getPublic();
            PrivateKey priKey = keyPair.getPrivate();
            String pubContent = Base64Util.encode(pubKey.getEncoded());
            String priContent = Base64Util.encode(priKey.getEncoded());
            result.setPublicKey(pubContent);
            result.setPrivateKey(priContent);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Ecdsa获得公钥密钥报错:{}", e);
        }

        return result;
    }

    /**
     * 加密
     *
     * @param res:
     * @param publicKey:
     * @param factoryType:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:25
     */
    public static String encrypt(String res, String publicKey, String factoryType) {
        Cipher cipher;
        try {
            PublicKey pubKey = bulidEcPublicKey(publicKey, factoryType);
            byte[] content = res.getBytes();
            cipher = Cipher.getInstance("ECIES", PROVIDER);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return Hex.encodeHexString(cipher.doFinal(content));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Ecdsa通过公钥加密报错:{}", e);

        } catch (NoSuchProviderException e) {
            LOGGER.error("Ecdsa通过公钥加密报错:{}", e);

        } catch (NoSuchPaddingException e) {
            LOGGER.error("Ecdsa通过公钥加密报错:{}", e);

        } catch (InvalidKeyException e) {
            LOGGER.error("Ecdsa通过公钥加密报错:{}", e);

        } catch (IllegalBlockSizeException e) {
            LOGGER.error("Ecdsa通过公钥加密报错:{}", e);

        } catch (BadPaddingException e) {
            LOGGER.error("Ecdsa通过公钥加密报错:{}", e);

        } catch (Exception e) {
            LOGGER.error("Ecdsa通过公钥加密报错:{}", e);

        }
        return null;

    }

    public static void main(String[] args) {
        EcdsaVo ecdsaVo = EcdsaUtil.getSecretKey(EEC_FACTORY_TYPE);
        System.out.println(JsonUtil.t2JsonString(ecdsaVo));
    }


}
