package com.sorsix.librarianapi.security.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthFailureHandlerImpl implements AuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(AuthSuccessHandlerImpl.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        logger.warn("Remote user tried to authenticate but failed [{}]", exception.getMessage());
    }
}
