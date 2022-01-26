package com.lvmoney.frame.blockchain.webase.weidentity.api.surface;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.api.surface
 * 版本信息: 版本1.0
 * 日期:2021/7/4
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.blockchain.webase.weidentity.api.ao.*;
import com.lvmoney.frame.blockchain.webase.weidentity.api.constant.WeidConstant;
import com.lvmoney.frame.blockchain.webase.weidentity.api.vo.*;
import com.lvmoney.frame.prefix.constant.FramePrefixConstant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/4 12:01
 */
@RequestMapping(FramePrefixConstant.PLATFORM_BLOCKCHAIN_WEBASE_WE_IDENTITY)
public interface IWeIdentity {
    /**
     * 创建WeIdentity DID（无参创建方式）
     *
     * @param requestAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.weidentity.api.vo.CreateWeIdVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/5 11:40
     */
    @PostMapping(value = WeidConstant.FUNC_CREATE_WE_ID)
    ApiResult<CreateWeIdVo> createWeId(@RequestBody WeIdRequestAo requestAo);

    /**
     * 获取WeIdentity DID Document
     *
     * @param requestAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.weidentity.api.vo.GetWeIdDocumentVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/5 11:41
     */
    @PostMapping(value = WeidConstant.FUNC_GET_WE_ID_DOCUMENT)
    ApiResult<GetWeIdDocumentVo> getWeIdDocument(@RequestBody WeIdRequestAo<GetWeIdDocumentAo> requestAo);

    /**
     * 获取WeIdentity DID Document
     *
     * @param requestAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/5 11:41
     */
    @PostMapping(value = WeidConstant.FUNC_REGISTER_AUTHORITY_ISSUER)
    ApiResult registerAuthorityIssuer(@RequestBody WeIdRequestAo<RegisterAuthorityIssuerAo> requestAo);

    /**
     * 查询AuthorityIssuer
     *
     * @param requestAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.weidentity.api.vo.QueryAuthorityIssuerVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/5 11:41
     */
    @PostMapping(value = WeidConstant.FUNC_QUERY_AUTHORITY_ISSUER)
    ApiResult<QueryAuthorityIssuerVo> queryAuthorityIssuer(@RequestBody WeIdRequestAo<QueryAuthorityIssuerAo> requestAo);


    /**
     * 查询AuthorityIssuer
     *
     * @param requestAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.weidentity.api.vo.RegisterCptVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/5 11:41
     */
    @PostMapping(value = WeidConstant.FUNC_REGISTER_CPT)
    ApiResult<RegisterCptVo> FUNC_REGISTER_CPT(@RequestBody WeIdRequestAo<RegisterCptAo> requestAo);

    /**
     * 查询AuthorityIssuer
     *
     * @param requestAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.weidentity.api.vo.QueryCptVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/5 11:41
     */
    @PostMapping(value = WeidConstant.FUNC_QUERY_CPT)
    ApiResult<QueryCptVo> queryCpt(@RequestBody WeIdRequestAo<QueryCptAo> requestAo);

    /**
     * 创建Credential
     *
     * @param requestAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.weidentity.api.vo.CreateCredentialAo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/5 11:41
     */
    @PostMapping(value = WeidConstant.FUNC_CREATE_CREDENTIAL)
    ApiResult<CreateCredentialVo> createCredential(@RequestBody WeIdRequestAo<CreateCredentialAo> requestAo);

    /**
     * 校验Credential
     *
     * @param requestAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.weidentity.api.vo.VerifyCredentialAo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/5 11:41
     */
    @PostMapping(value = WeidConstant.FUNC_VERIFY_CREDENTIAL)
    ApiResult<Boolean> verifyCredential(@RequestBody WeIdRequestAo<VerifyCredentialAo> requestAo);

    /**
     * 创建CredentialPojo
     *
     * @param requestAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.weidentity.api.vo.CreateCredentialPojoVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/5 11:41
     */
    @PostMapping(value = WeidConstant.FUNC_CREATE_CREDENTIAL_POJO)
    ApiResult<CreateCredentialPojoVo> createCredentialPojo(@RequestBody WeIdRequestAo<CreateCredentialPojoAo> requestAo);

    /**
     * 校验CredentialPojo
     *
     * @param requestAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.weidentity.api.vo.verifyCredentialPojo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/5 11:41
     */
    @PostMapping(value = WeidConstant.FUNC_VERIFY_CREDENTIAL_POJO)
    ApiResult<Boolean> verifyCredentialPojo(@RequestBody WeIdRequestAo<VerifyCredentialPojoAo> requestAo);
}
