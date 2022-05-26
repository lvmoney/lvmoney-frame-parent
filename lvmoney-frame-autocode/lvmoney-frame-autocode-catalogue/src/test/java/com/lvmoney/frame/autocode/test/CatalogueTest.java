package com.lvmoney.frame.autocode.test;/**
 * 描述:
 * 包名:com.lvmoney.frame.autocode.test
 * 版本信息: 版本1.0
 * 日期:2020/6/17
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.autocode.catalogue.util.CatalogueUtil;
import com.lvmoney.frame.autocode.catalogue.vo.CatalogueVo;
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
public class CatalogueTest {

    @Test
    public void catalogue() throws Throwable {
        CatalogueVo catalogueVo = new CatalogueVo();
//        catalogueVo.setPath("D:\\git\\lvmoney-frame-parent\\lvmoney-frame-autocode\\lvmoney-frame-autocode-catalogue");
        catalogueVo.setSys("auth");
        catalogueVo.setOrg("lvmoney");
        catalogueVo.setArtifact("frame");
        CatalogueUtil.create(catalogueVo);
    }
}
