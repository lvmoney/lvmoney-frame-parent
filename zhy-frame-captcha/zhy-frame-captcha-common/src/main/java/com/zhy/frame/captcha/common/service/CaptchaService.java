package com.zhy.frame.captcha.common.service;/**
 * 描述:
 * 包名:com.zhy.frame.captcha.service
 * 版本信息: 版本1.0
 * 日期:2019/2/17
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.captcha.common.ro.ValidateCodeRo;
import com.zhy.frame.captcha.common.vo.ValidateResultVo;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public interface CaptchaService {
    /**
     * 保存验证码数据到redis中
     *
     * @param validateCodeRo: redis 实体
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:54
     */
    void saveValidaCode2Cache(ValidateCodeRo validateCodeRo);

    /**
     * @describe: 获得base64后的验证码，可配置验证码的大小等
     * @param: [is2Redis, isDrawLine, validCodeSize, fc, bc, fontType]
     * @return: com.zhy.captcha.vo.ValidateResultVo
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:03
     */
    /**
     * 获得base64后的验证码，可配置验证码的大小等
     *
     * @param is2Cache:      是否存入Cache
     * @param isDrawLine:    是否画线
     * @param validCodeSize: 验证码size
     * @param fc:            字体颜色
     * @param bc:            背景颜色
     * @param fontType:      字体类型
     * @return: com.zhy.captcha.vo.ValidateResultVo
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:55
     */
    ValidateResultVo encodeBase64ImgCode(boolean is2Cache, boolean isDrawLine, int validCodeSize, int fc, int bc, String fontType);

    /**
     * 获得默认base64后的验证码
     *
     * @return: com.zhy.captcha.vo.ValidateResultVo
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:56
     */
    ValidateResultVo encodeBase64ImgCode();

    /**
     * 获得验证码
     *
     * @param width:  宽
     * @param height: 高
     * @param length: 长度
     * @return: com.zhy.captcha.vo.ValidateResultVo
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:57
     */
    ValidateResultVo getCaptcha(int width, int height, int length);

    /**
     * 通过编号获得验证码
     *
     * @param serialNumber: redis中编号
     * @return: com.zhy.captcha.ro.ValidateCodeRo
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:57
     */
    ValidateCodeRo getValidate(String serialNumber);

    /**
     * 删除验证码
     *
     * @param serialNumber: 验证码编号
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:57
     */
    void deleteValidate(String serialNumber);

}
