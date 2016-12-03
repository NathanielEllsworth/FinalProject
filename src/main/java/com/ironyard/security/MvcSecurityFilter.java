package com.ironyard.security;

import com.ironyard.data.TheUser;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by nathanielellsworth on 11/5/16.
 */

public class MvcSecurityFilter implements javax.servlet.Filter {

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
        HttpServletRequest req = ((HttpServletRequest) request);
        HttpServletResponse resp = ((HttpServletResponse) response);
        // check sessio
        TheUser usr = (TheUser) req.getSession().getAttribute("user");
        boolean authorized = (usr != null);

        if(authorized) {
            chain.doFilter(request, response);
        }else{
            resp.sendRedirect("/mvc/open/login.jsp");
        }
    }


    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}