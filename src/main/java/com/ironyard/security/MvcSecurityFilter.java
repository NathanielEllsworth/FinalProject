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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = ((HttpServletRequest) servletRequest);
        HttpServletResponse resp = ((HttpServletResponse) servletResponse);
        // check session
        TheUser usr = (TheUser) req.getSession().getAttribute("user");
        boolean authorized = (usr != null);

        if(authorized){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            resp.sendRedirect("/mvc/open/login.jsp");
        }
    }


    @Override
    public void destroy(){

    }

}
