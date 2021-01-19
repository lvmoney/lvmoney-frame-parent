package com.lvmoney.frame.autocode.yml.test;/**
 * 描述:
 * 包名:com.lvmoney.frame.autocode.test
 * 版本信息: 版本1.0
 * 日期:2020/6/17
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.autocode.yml.util.YmlUtil;
import com.lvmoney.frame.autocode.yml.vo.YmlVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/17 15:25
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class YmlTest {

    @Test
    public void catalogue() throws Throwable {
        YmlVo ymlVo = new YmlVo();
        ymlVo.setSysPort(8080);
        ymlVo.setSysName("lvmoney");
        ymlVo.setCover(true);
        ymlVo.setGateway("gateway");
        YmlUtil.createApplicationYml(ymlVo);
        YmlUtil.createOtherApplicationYml(ymlVo);
    }
}
