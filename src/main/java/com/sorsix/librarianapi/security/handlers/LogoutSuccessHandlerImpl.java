package com.sorsix.librarianapi.security.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(LogoutSuccessHandlerImpl.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String principal = authentication.getName();
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        logger.info("User [{}] with roles [{}] has signed out", principal, roles);
    }
}
