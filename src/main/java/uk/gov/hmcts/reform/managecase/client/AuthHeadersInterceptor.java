package uk.gov.hmcts.reform.managecase.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import uk.gov.hmcts.reform.managecase.security.SecurityUtils;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class AuthHeadersInterceptor implements RequestInterceptor {

    public static final String SERVICE_AUTHORIZATION = "ServiceAuthorization";

    private final SecurityUtils securityUtils;

    public AuthHeadersInterceptor(SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }

    @Override
    public void apply(RequestTemplate template) {
        if (!template.headers().containsKey(AUTHORIZATION)) {
            template.header(AUTHORIZATION, securityUtils.getUserToken());
        }
        if (!template.headers().containsKey(SERVICE_AUTHORIZATION)) {
            template.header(SERVICE_AUTHORIZATION, securityUtils.getS2SToken());
        }
    }
}