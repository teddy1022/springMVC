package com.systex.playground.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*") 
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String requestURI = httpRequest.getRequestURI();

        boolean isLoginRequest = requestURI.equals(httpRequest.getContextPath() + "/login");
        boolean isRegisterRequest = requestURI.equals(httpRequest.getContextPath() + "/register");
     

        boolean isProtectedResource = requestURI.startsWith(httpRequest.getContextPath() + "/lottery") ||
                                      requestURI.startsWith(httpRequest.getContextPath() + "/guess") ||
                                      requestURI.equals(httpRequest.getContextPath() + "/") ||
                                      requestURI.equals(httpRequest.getContextPath() + "/index");

        if (isProtectedResource && (session == null || session.getAttribute("loggedInUser") == null)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        } else {
            if (isLoginRequest || isRegisterRequest) {
                chain.doFilter(request, response);
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
