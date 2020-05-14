package com.zhy.frame.workflow.activiti.controller;

import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.workflow.activiti.entity.VacationForm;
import com.zhy.frame.workflow.activiti.service.MiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Controller
public class MiaoController {
    @Autowired
    private MiaoService miaoService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    /**
     * @describe: 申请者的首页
     * @param: [model, request]
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:22
     */
    @GetMapping("/home")
    public String index(ModelMap model, HttpServletRequest request) {
        List<VacationForm> forms = miaoService.formList();
        Cookie[] cookies = request.getCookies();
        String user = "";
        //从cookie中获取当前用户
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userInfo".equals(cookie.getName())) {
                    user = cookie.getValue();
                    break;
                }
            }
        }
        List<HashMap<String, Object>> formsMap = new ArrayList<HashMap<String, Object>>();
        for (VacationForm form : forms) {
            //申请者只能看到自己申请的请假单信息
            if (user.equals(form.getApplicant())) {
                HashMap<String, Object> map = new HashMap<String, Object>(BaseConstant.MAP_DEFAULT_SIZE);
                map.put("id", form.getId());
                map.put("title", form.getTitle());
                map.put("content", form.getContent());
                map.put("applicant", form.getApplicant());
                map.put("state", form.getState());
                formsMap.add(map);
            }
        }
        //将forms参数返回
        model.addAttribute("forms", formsMap);
        return "index";
    }

    /**
     * @describe: 审核者的首页
     * @param: [model]
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:22
     */
    @GetMapping("/homeApprover")
    public String indexApprover(ModelMap model) {
        List<VacationForm> forms = miaoService.formList();
        List<HashMap<String, Object>> formsMap = new ArrayList<HashMap<String, Object>>();
        for (VacationForm form : forms) {
            //审核者只能看到待审核状态的请假单
            if ("领导审核".equals(form.getState())) {
                HashMap<String, Object> map = new HashMap<String, Object>(BaseConstant.MAP_DEFAULT_SIZE);
                map.put("id", form.getId());
                map.put("title", form.getTitle());
                map.put("content", form.getContent());
                map.put("applicant", form.getApplicant());
                map.put("state", form.getState());
                formsMap.add(map);
            }
        }
        model.addAttribute("forms", formsMap);
        return "indexApprover";
    }

    /**
     * @describe: 请假单页面
     * @param: []
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:22
     */
    @GetMapping("/form")
    public String form() {
        return "form";
    }
}
