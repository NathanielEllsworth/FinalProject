package com.ironyard.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nathanielellsworth on 11/24/16.
 */

public class RestSecurityFilter implements javax.servlet.Filter{

    /**
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        // check for key based authentication
        boolean authorized = false;
        String key = req.getHeader("x-authorization-key");
        if(key != null){
            authorized = SecurityUtils.isValidKey(key);
        }

        if(authorized){
            chain.doFilter(request, response);
        }else{
            // tell no
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            res.getWriter().println("<html><body><p> unauthorized </p></body></html>");
        }

    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }




}
