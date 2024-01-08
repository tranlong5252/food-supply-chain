package tranlong5252.foodsupplychain.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletReq = (HttpServletRequest) request;
        if (servletReq.getRequestURI().equals("/Login") || servletReq.getRequestURI().equals("/")) {
            chain.doFilter(request, response);
            return;
        }
        Cookie[] cookies = servletReq.getCookies();
        int userId = -1;
        boolean isLogin = false;
        for (Cookie cookie : cookies) {
            if ("accountId".equals(cookie.getName())) {
                userId = Integer.parseInt(cookie.getValue());
                isLogin = true;
                break;
            }
        }
        if (!isLogin) {
            ((HttpServletResponse) response).sendRedirect("Login");
            return;
        }
        request.setAttribute("accountId", userId);
        chain.doFilter(request, response);
    }
}