package com.zhy.frame.authentication.oauth2.center.controller;

import com.zhy.frame.authentication.oauth2.center.exception.Oauth2Exception;
import com.zhy.frame.authentication.oauth2.center.service.UserAccountService;
import com.zhy.frame.authentication.oauth2.center.vo.*;
import com.zhy.frame.core.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Controller
@RequestMapping(value = "/management/user")
public class ManageUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManageUserController.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserAccountService userAccountService;
    /**
     * 更新状态
     */
    private static final int DELETEOPERATION_IPDATE = -2;

    @GetMapping(value = {"/", "", "/master"})
    public String master(Principal principal) {

        return "user/master";
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public JsonObjects<UserAccountVo> listObjects(@RequestParam(value = "search[value]", required = false, defaultValue = "") String searchValue,
                                                  @RequestParam(value = "draw", defaultValue = "0") int draw,
                                                  @RequestParam(value = "length", defaultValue = "10") Integer pageSize,
                                                  @RequestParam(value = "start", defaultValue = "0") Integer start,
                                                  @RequestParam(value = "sidx", defaultValue = "id") String sortField,
                                                  @RequestParam(value = "sord", defaultValue = "desc") String sortOrder) {
        int pageNum = start / 10 + 1;
        JsonObjects<UserAccountVo> result = userAccountService.listByUsername(searchValue, pageNum, pageSize, sortField, sortOrder);
        result.setDraw(draw + 1);
        return result;
    }

    @GetMapping(value = "/details")
    @ResponseBody
    public UserAccountVo setupDetails(@RequestParam(value = "id") Long id,
                                      @RequestParam(value = "additionalData", required = false) String additionalData) {
        UserAccountVo object = userAccountService.retrieveById(id);
        object.setAdditionalData(additionalData);
        return object;
    }

    @PostMapping(value = "/details")
    @ResponseBody
    public void handlePost(@RequestParam(value = "id", required = false) long id,
                           @RequestParam(value = "deleteOperation", required = false, defaultValue = "1") int deleteOperation,
                           @RequestParam(value = "nickName", required = false) String nickName,
                           @RequestParam(value = "address", required = false) String address,
                           @RequestParam(value = "password", required = false) String password) {


        if (deleteOperation == DELETEOPERATION_IPDATE && id > 0) {
            long userId = getUserIdBySpring();
            if (userId != id) {
                userAccountService.updateRecordStatus(id, 0);
            }
        } else if (deleteOperation == 0 && id > 0) {
            long userId = getUserIdBySpring();
            if (userId != id) {
                userAccountService.updateRecordStatus(id, -2);
            }
        } else if (id > 0) {
            UserAccountVo object = userAccountService.retrieveById(id);
            if (StringUtils.isNotEmpty(password)) {
                object.setPassword(passwordEncoder.encode(StringUtils.trim(password)));
            }
            if (StringUtils.isNotEmpty(address)) {
                object.setAddress(address);
            }
            object.setNickName(nickName);
            userAccountService.updateById(object);
        } else {
            throw new BusinessException(Oauth2Exception.Proxy.VERIFICATION_ERROR);
        }

    }

    private long getUserIdBySpring() {
        UserInfo userDetails = null;
        try {
            userDetails = (UserInfo) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
            userDetails = (UserInfo) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
        } catch (Exception e) {
            LOGGER.error("获得用户信息报错:{}", e.getMessage());
        }
        return userDetails.getUserId();
    }

}
