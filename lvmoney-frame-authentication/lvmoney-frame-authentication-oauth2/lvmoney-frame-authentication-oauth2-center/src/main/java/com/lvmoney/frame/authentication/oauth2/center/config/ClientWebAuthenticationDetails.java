package com.lvmoney.frame.authentication.oauth2.center.config;

import com.lvmoney.frame.authentication.oauth2.center.constant.Oauth2ServerConstant;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class ClientWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 6975601077710753878L;
    private final String inputVerificationCode;
    private final String graphId;

    public ClientWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        inputVerificationCode = request.getParameter(Oauth2ServerConstant.VERIFICATION_CODE);
        graphId = request.getParameter(Oauth2ServerConstant.GRAPH_ID);
    }

    public String getInputVerificationCode() {
        return inputVerificationCode;
    }

    public String getGraphId() {
        return graphId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; inputVerificationCode: ").append(this.getInputVerificationCode())
                .append("; graphId: ").append(this.getGraphId());
        return sb.toString();
    }
}
