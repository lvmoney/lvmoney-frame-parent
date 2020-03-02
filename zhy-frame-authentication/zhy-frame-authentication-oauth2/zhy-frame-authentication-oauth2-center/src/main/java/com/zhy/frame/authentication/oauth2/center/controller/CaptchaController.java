package com.zhy.frame.authentication.oauth2.center.controller;

import com.zhy.frame.authentication.oauth2.center.exception.Oauth2Exception;
import com.zhy.frame.authentication.oauth2.center.vo.resp.CaptchaGraphBase64RespVo;
import com.zhy.frame.authentication.oauth2.center.vo.resp.CaptchaGraphRespVo;
import com.revengemission.commons.captcha.core.VerificationCodeUtil;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.captcha.service.CaptchaService;
import com.zhy.frame.captcha.vo.ValidateResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Controller
public class CaptchaController {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CaptchaService captchaService;

    /**
     * 图形验证码
     */
    @ResponseBody
    @RequestMapping(value = "/captcha/graph")
    public CaptchaGraphRespVo captchaGraph() {
        CaptchaGraphRespVo captchaGraphRespVo = new CaptchaGraphRespVo();
        ValidateResultVo validateResultVo = captchaService.getCaptcha(150, 38, 4);
        captchaGraphRespVo.setStatus(1);
        captchaGraphRespVo.setExpire(18000L);
        captchaGraphRespVo.setGraphId(validateResultVo.getSerialNumber());
        captchaGraphRespVo.setGraphUrl("/captcha/graph/print?graphId=" + validateResultVo.getSerialNumber());
        return captchaGraphRespVo;

    }

//    /**
//     * 短信证码
//     *
//     * @param phone   手机号
//     * @param graphId 图形验证码id
//     */
//    @ResponseBody
//    @RequestMapping(value = "/captcha/sms")
//    public Map<String, Object> captchaSms(@RequestParam(value = "phone") String phone,
//                                          @RequestParam(value = "captcha") String inputCaptcha, @RequestParam(value = "graphId") String graphId) {
//        Map<String, Object> resultMap = new HashMap<>();
//
//        String captcha = captchaService.getCaptcha(CachesEnum.GraphCaptchaCache, graphId);
//
//        if (StringUtils.equalsIgnoreCase(inputCaptcha, captcha)) {
//            String uuid = UUID.randomUUID().toString();
//            String smsCaptcha = RandomStringUtils.randomNumeric(4);
//
//            captchaService.saveCaptcha(CachesEnum.SmsCaptchaCache, uuid, phone + "_" + smsCaptcha);
//
//            log.info("smsCaptcha=" + smsCaptcha);
//            // TODO send sms smsCaptcha
//
//            resultMap.put("status", 1);
//            resultMap.put("smsId", uuid);
//            resultMap.put("ttl", CachesEnum.SmsCaptchaCache.getTtl());
//            captchaService.removeCaptcha(CachesEnum.GraphCaptchaCache, graphId);
//        } else {
//            resultMap.put("status", 0);
//            resultMap.put("message", "验证码错误！");
//        }
//
//        return resultMap;
//    }

    /**
     * 图形验证码打印
     *
     * @param graphId 验证码编号
     * @param width   图片宽度
     * @param height  图片高度
     */
    @RequestMapping(value = "/captcha/graph/print")
    public void captchaGraphPrint(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "graphId") String graphId, @RequestParam(value = "w", defaultValue = "150") int width,
                                  @RequestParam(value = "h", defaultValue = "38") int height) throws IOException {

        String captcha = captchaService.getValidate(graphId).getValue();
        if (captcha != null) {
            request.getSession(true);
            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache, no-store");
            response.setHeader("Pragma", "no-cache");
            long time = System.currentTimeMillis();
            response.setDateHeader("Last-Modified", time);
            response.setDateHeader("Date", time);
            response.setDateHeader("Expires", time);
            ServletOutputStream stream = response.getOutputStream();
            VerificationCodeUtil.outputImage(width, height, stream, captcha);
            stream.flush();
            stream.close();
        }

    }

    /**
     * 图形验证码Base64
     *
     * @param graphId 验证码编号
     * @param width   图片宽度
     * @param height  图片高度
     */
    @ResponseBody
    @RequestMapping(value = "/captcha/graph/base64")
    public CaptchaGraphBase64RespVo captchaGraphBase64(@RequestParam(value = "graphId") String graphId, @RequestParam(value = "w", defaultValue = "150") int width,
                                                       @RequestParam(value = "h", defaultValue = "38") int height) throws IOException {

        CaptchaGraphBase64RespVo captchaGraphBase64RespVo = new CaptchaGraphBase64RespVo();
        String captcha = captchaService.getValidate(graphId).getValue();
        if (captcha != null) {
            String base64EncodedGraph = captchaService.getValidate(graphId).getCode();
            captchaGraphBase64RespVo.setStatus(1);
            captchaGraphBase64RespVo.setBase64EncodedGraph(base64EncodedGraph);
        } else {
            throw new BusinessException(Oauth2Exception.Proxy.VERIFICATION_ERROR);
        }
        return captchaGraphBase64RespVo;

    }

}
