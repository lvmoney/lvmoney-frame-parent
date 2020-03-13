package com.zhy.frame.notice.email.service.impl;/**
 * 描述:
 * 包名:com.zhy.email.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/11/22
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.notice.email.service.EmailService;
import com.zhy.frame.notice.email.vo.EmailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/22 11:44
 */
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Override
    public void sendHtml(EmailVo emailVo) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(mailFrom);
        msg.setTo(emailVo.getTo());
        msg.setSubject(emailVo.getTitle());
        msg.setText(emailVo.getContext());
        javaMailSender.send(msg);
    }

    @Override
    public void sendSimple(EmailVo emailVo) {
        //创建媒体消息
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailFrom);
            helper.setTo(emailVo.getTo());
            helper.setSubject(emailVo.getTitle());
            helper.setText(emailVo.getContext(), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
