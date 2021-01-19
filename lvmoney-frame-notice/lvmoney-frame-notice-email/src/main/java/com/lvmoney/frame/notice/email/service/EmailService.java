package com.lvmoney.frame.notice.email.service;/**
 * 描述:
 * 包名:com.lvmoney.email.service
 * 版本信息: 版本1.0
 * 日期:2019/11/22
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.notice.email.vo.EmailVo;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/22 11:41
 */
public interface EmailService {
    /**
     * 发送普通的邮件
     *
     * @param emailVo:
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/22 14:03
     */
    void sendHtml(EmailVo emailVo);

    /**
     * 发送html类型邮件
     *
     * @param emailVo:
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/22 14:03
     */
    void sendSimple(EmailVo emailVo);
}
