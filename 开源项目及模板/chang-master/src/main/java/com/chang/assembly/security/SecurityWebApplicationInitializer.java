package com.chang.assembly.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Security过滤请求
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    @Override
    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }
}
