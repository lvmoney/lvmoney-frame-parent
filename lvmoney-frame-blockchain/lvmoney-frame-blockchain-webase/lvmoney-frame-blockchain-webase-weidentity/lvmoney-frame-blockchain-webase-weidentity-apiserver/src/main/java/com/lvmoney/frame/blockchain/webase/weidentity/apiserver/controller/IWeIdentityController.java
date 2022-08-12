package com.lvmoney.frame.blockchain.webase.weidentity.apiserver.controller;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.apiserver.controller
 * 版本信息: 版本1.0
 * 日期:2021/7/5
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.dozermapper.core.Mapper;
import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.blockchain.webase.weidentity.api.ao.*;
import com.lvmoney.frame.blockchain.webase.weidentity.api.constant.WeidConstant;
import com.lvmoney.frame.blockchain.webase.weidentity.api.surface.IWeIdentity;
import com.lvmoney.frame.blockchain.webase.weidentity.api.vo.*;
import com.lvmoney.frame.blockchain.webase.weidentity.apiserver.feign.IWeIdClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/5 11:39
 */
@RestController
public class IWeIdentityController implements IWeIdentity {
    @Value("${webase.server.weidentity.version:1.0.0}")
    String defaultVersion;
    @Autowired
    private Mapper mapper;
    @Autowired
    IWeIdClient iWeIdClient;

    private static final String KEY_CPT_JSON_SCHEMA = "cptJsonSchema";

    private static final String KEY_CLAIM = "claim";

    @Override
    public ApiResult<CreateWeIdVo> createWeId(WeIdRequestAo requestAo) {
        return ApiResult.success(mapper.map(getResult(requestAo, WeidConstant.FUNC_CREATE_WE_ID).getRespBody(), CreateWeIdVo.class));

    }

    @Override
    public ApiResult<GetWeIdDocumentVo> getWeIdDocument(WeIdRequestAo<GetWeIdDocumentAo> requestAo) {
        System.out.println(mapper.map(getResult(requestAo, WeidConstant.FUNC_GET_WE_ID_DOCUMENT).getRespBody(), GetWeIdDocumentVo.class).getId());
        return ApiResult.success(mapper.map(getResult(requestAo, WeidConstant.FUNC_GET_WE_ID_DOCUMENT).getRespBody(), GetWeIdDocumentVo.class));

    }

    @Override
    public ApiResult registerAuthorityIssuer(WeIdRequestAo<RegisterAuthorityIssuerAo> requestAo) {
        return ApiResult.success(getResult(requestAo, WeidConstant.FUNC_REGISTER_AUTHORITY_ISSUER).getRespBody());

    }

    @Override
    public ApiResult<QueryAuthorityIssuerVo> queryAuthorityIssuer(WeIdRequestAo<QueryAuthorityIssuerAo> requestAo) {
        return ApiResult.success(mapper.map(getResult(requestAo, WeidConstant.FUNC_QUERY_AUTHORITY_ISSUER).getRespBody(), QueryAuthorityIssuerVo.class));
    }

    @Override
    public ApiResult<RegisterCptVo> FUNC_REGISTER_CPT(WeIdRequestAo<RegisterCptAo> requestAo) {
        return ApiResult.success(mapper.map(getResult(requestAo, WeidConstant.FUNC_REGISTER_CPT).getRespBody(), RegisterCptVo.class));

    }

    @Override
    public ApiResult<QueryCptVo> queryCpt(WeIdRequestAo<QueryCptAo> requestAo) {

        ResponseVo responseVo = getResult(requestAo, WeidConstant.FUNC_QUERY_CPT);
        JSONObject jsonObject = JSON.parseObject(JsonUtil.t2JsonString(responseVo.getRespBody()));
        JSONObject cptJsonSchema = jsonObject.getJSONObject(KEY_CPT_JSON_SCHEMA);
        QueryCptVo queryCptVo = mapper.map(responseVo.getRespBody(), QueryCptVo.class);
        queryCptVo.setCptJsonSchema(cptJsonSchema);
        return ApiResult.success(queryCptVo);

    }

    @Override
    public ApiResult<CreateCredentialVo> createCredential(WeIdRequestAo<CreateCredentialAo> requestAo) {
        ResponseVo responseVo = getResult(requestAo, WeidConstant.FUNC_CREATE_CREDENTIAL);
        JSONObject jsonObject = JSON.parseObject(JsonUtil.t2JsonString(responseVo.getRespBody()));
        JSONObject cptJsonSchema = jsonObject.getJSONObject(KEY_CLAIM);
        CreateCredentialVo createCredentialVo = mapper.map(responseVo.getRespBody(), CreateCredentialVo.class);
        createCredentialVo.setClaim(cptJsonSchema);
        return ApiResult.success(createCredentialVo);
    }

    @Override
    public ApiResult<Boolean> verifyCredential(WeIdRequestAo<VerifyCredentialAo> requestAo) {
        ResponseVo responseVo = getResult(requestAo, WeidConstant.FUNC_VERIFY_CREDENTIAL);
        return ApiResult.success(responseVo.getRespBody());

    }

    @Override
    public ApiResult<CreateCredentialPojoVo> createCredentialPojo(WeIdRequestAo<CreateCredentialPojoAo> requestAo) {
        ResponseVo responseVo = getResult(requestAo, WeidConstant.FUNC_CREATE_CREDENTIAL_POJO);
        JSONObject jsonObject = JSON.parseObject(JsonUtil.t2JsonString(responseVo.getRespBody()));
        JSONObject cptJsonSchema = jsonObject.getJSONObject(KEY_CLAIM);
        CreateCredentialPojoVo createCredentialVo = mapper.map(responseVo.getRespBody(), CreateCredentialPojoVo.class);
        createCredentialVo.setClaim(cptJsonSchema);
        return ApiResult.success(createCredentialVo);
    }

    @Override
    public ApiResult<Boolean> verifyCredentialPojo(WeIdRequestAo<VerifyCredentialPojoAo> requestAo) {
        ResponseVo responseVo = getResult(requestAo, WeidConstant.FUNC_VERIFY_CREDENTIAL_POJO);
        return ApiResult.success(responseVo.getRespBody());
    }

    /**
     * 构造调用weid接口的入参
     *
     * @param weIdRequestAo:
     * @param funcName:
     * @throws
     * @return: com.lvmoney.frame.blockchain.webase.weidentity.api.ao.RequestAo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/5 13:34
     */
    private RequestAo weIdRequestAo2RequestAo(WeIdRequestAo weIdRequestAo, String funcName) {
        RequestAo requestAo = mapper.map(weIdRequestAo, RequestAo.class);
        requestAo.setFunctionArg(weIdRequestAo.getFunctionArg());
        requestAo.setFunctionName(funcName);
        requestAo.setV(defaultVersion);
        return requestAo;
    }

    /**
     * 获得返回值并构造返回值
     *
     * @param requestAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/5 13:38
     */
    private ResponseVo responseVo2ApiResult(RequestAo requestAo) {
        return iWeIdClient.invoke(requestAo);
    }

    /**
     * 获得结果
     *
     * @param weIdRequestAo:
     * @param funcName:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/5 13:45
     */
    private ResponseVo getResult(WeIdRequestAo weIdRequestAo, String funcName) {
        RequestAo requestAo = weIdRequestAo2RequestAo(weIdRequestAo, funcName);
        return responseVo2ApiResult(requestAo);
    }
}
