package com.zhy.frame.captcha.server.service.impl;/**
 * 描述:
 * 包名:com.zhy.captcha.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/2/17
 * Copyright 四川******科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.revengemission.commons.captcha.core.VerificationCodeUtil;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.cache.common.annation.CacheImpl;
import com.zhy.frame.cache.common.service.CacheCommonService;
import com.zhy.frame.captcha.common.exception.CaptchaException;
import com.zhy.frame.captcha.common.ro.ValidateCodeRo;
import com.zhy.frame.captcha.common.service.CaptchaService;
import com.zhy.frame.captcha.common.vo.ValidateCodeVo;
import com.zhy.frame.captcha.common.vo.ValidateResultVo;
import com.zhy.frame.core.util.FileUtil;
import com.zhy.frame.core.util.SnowflakeIdFactoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import static com.zhy.frame.cache.common.constant.CacheConstant.CACHE_REDIS;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaServiceImpl.class);

    private static final int COLOR_255 = 255;
    private static final int COLOR_155 = 255;
    @Value("${customer.valid.expire:18000}")
    String expire;
    @CacheImpl(CACHE_REDIS)
    CacheCommonService baseRedisService;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    /**
     * @describe: 渲染随机背景颜色
     * @param: [fc, bc]
     * @return: java.awt.Color
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:02
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > COLOR_255) {
            fc = COLOR_255;
        }
        if (bc > COLOR_255) {
            bc = COLOR_255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * @describe: 渲染固定背景颜色
     * @param: []
     * @return: java.awt.Color
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:02
     */
    private Color getBgColor() {
        return new Color(200, 200, 200);
    }


    /**
     * @describe: 画验证码图形
     * @param: [isDrawLine, validCodeSize, fc, bc, fontType]
     * @return: com.zhy.captcha.vo.ValidateCodeVo
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:02
     */
    private ValidateCodeVo drawImg(boolean isDrawLine, int validCodeSize, int fc, int bc, String fontType) {
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
//        if(validCodeSize<4||validCodeSize>8){//验证码的长度在4~到8个字符
//
//        }
        // 在内存中创建图象
        int width = 120, height = 18;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(fc, bc));
        g.fillRect(0, 0, width, height);
      /*  设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));*/
        g.setFont(new Font(fontType, Font.PLAIN, 20));
      /*  画边框
       g.drawRect(0,0,width-1,height-1);*/

        /**随机产生155条干扰线，使图象中的认证码不易被其它程序探测到*/

        if (isDrawLine) {
            g.setColor(getRandColor(120, 140));
            for (int i = 0; i < COLOR_155; i++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                int xl = random.nextInt(12);
                int yl = random.nextInt(12);
                g.drawLine(x, y, x + xl, y + yl);
            }
        }

        // 取随机产生的认证码(8位数字和字母混合)
        String sRand = "";
        String verCode = "";
        char[] seds = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (int i = 0; i < validCodeSize; i++) {
            int index = random.nextInt(seds.length);
            char cc = seds[index];
            if ((i + 1) % 2 == 0) {
                verCode += cc;
                g.setColor(new Color(255, 0, 0));
            } else {
                g.setColor(new Color(0, 0, 0));
            }
            sRand += cc;
            // 将认证码随机打印不同的颜色显示出来
            //g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(cc + "", 13 * i + 6, 16);
        }
        // 图象生效
        g.dispose();
        validateCodeVo.setBufferedImage(image);
        validateCodeVo.setValue(sRand);
        return validateCodeVo;
    }


    @Override
    public ValidateResultVo encodeBase64ImgCode(boolean is2Redis, boolean isDrawLine, int validCodeSize, int fc, int bc, String fontType) {
        ValidateCodeVo validateCodeVo = drawImg(isDrawLine, validCodeSize, fc, bc, fontType);
        BufferedImage codeImg = validateCodeVo.getBufferedImage();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            boolean flag = ImageIO.write(codeImg, "JPEG", out);
        } catch (IOException e) {
            LOGGER.error("生成验证码报错:{}", e.getMessage());
            throw new BusinessException(CaptchaException.Proxy.VALID_CODE_ERROR);
        }
        ValidateResultVo validateResultVo = new ValidateResultVo();
        if (is2Redis) {
            SnowflakeIdFactoryUtil idWorker = new SnowflakeIdFactoryUtil(1, 2);
            ValidateCodeRo validateCodeRo = new ValidateCodeRo();
            String serialNumber = String.valueOf(idWorker.nextId());
            validateResultVo.setSerialNumber(serialNumber);
            validateCodeRo.setValue(validateCodeVo.getValue());
            validateCodeRo.setSerialNumber(serialNumber);
            validateCodeRo.setExpire(Long.parseLong(expire));
            this.saveValidaCode2Cache(validateCodeRo);
        }
        byte[] b = out.toByteArray();
        validateResultVo.setCode(FileUtil.file2Base64(b));
        validateResultVo.setValue(validateCodeVo.getValue());
        return validateResultVo;
    }


    @Override
    public ValidateResultVo encodeBase64ImgCode() {
        String createText = defaultKaptcha.createText();
        // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
        BufferedImage challenge = defaultKaptcha.createImage(createText);
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(challenge, "jpg", jpegOutputStream);
            byte[] b = jpegOutputStream.toByteArray();
            ValidateResultVo validateResultVo = new ValidateResultVo();
            validateResultVo.setCode(FileUtil.file2Base64(b));
            validateResultVo.setValue(createText);
            SnowflakeIdFactoryUtil idWorker = new SnowflakeIdFactoryUtil(1, 2);
            ValidateCodeRo validateCodeRo = new ValidateCodeRo();
            String serialNumber = String.valueOf(idWorker.nextId());
            validateResultVo.setSerialNumber(serialNumber);
            validateCodeRo.setValue(createText);
            validateCodeRo.setSerialNumber(serialNumber);
            validateCodeRo.setExpire(Long.parseLong(expire));
            this.saveValidaCode2Cache(validateCodeRo);
            return validateResultVo;
        } catch (IOException e) {
            LOGGER.error("生成验证码报错:{}", e.getMessage());
            throw new BusinessException(CaptchaException.Proxy.VALID_CODE_ERROR);
        }
    }


    @Override
    public ValidateResultVo getCaptcha(int width, int height, int length) {
        ValidateResultVo validateResultVo = new ValidateResultVo();
        String captcha = VerificationCodeUtil.generateVerificationCode(length, null);
        validateResultVo.setValue(captcha);
        try {
            String base64EncodedGraph = VerificationCodeUtil.outputImage(width, height, captcha);
            validateResultVo.setCode(base64EncodedGraph);
        } catch (IOException e) {
            LOGGER.error("获得验证码报错:{}", e.getMessage());
        }
        SnowflakeIdFactoryUtil idWorker = new SnowflakeIdFactoryUtil(1, 2);
        String serialNumber = String.valueOf(idWorker.nextId());
        validateResultVo.setSerialNumber(serialNumber);
        ValidateCodeRo validateCodeRo = new ValidateCodeRo();
        BeanUtils.copyProperties(validateResultVo, validateCodeRo);
        validateCodeRo.setExpire(Long.parseLong(expire));
        saveValidaCode2Cache(validateCodeRo);
        return validateResultVo;
    }


    @Override
    public ValidateCodeRo getValidate(String serialNumber) {
        Object obj = baseRedisService.getByKey(serialNumber);
        ValidateCodeRo validateCodeRo = JSON.parseObject(obj.toString(), new TypeReference<ValidateCodeRo>() {
        });
        return validateCodeRo;
    }


    @Override
    public void deleteValidate(String serialNumber) {
        baseRedisService.deleteByKey(serialNumber);
    }


    @Override
    public void saveValidaCode2Cache(ValidateCodeRo validateCodeRo) {
        baseRedisService.setString(validateCodeRo.getSerialNumber(), JsonUtil.t2JsonString(validateCodeRo), validateCodeRo.getExpire());
    }

}
