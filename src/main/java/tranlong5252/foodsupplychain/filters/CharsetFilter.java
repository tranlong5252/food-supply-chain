package tranlong5252.foodsupplychain.filters;

import javax.servlet.http.HttpFilter;

public class CharsetFilter extends HttpFilter {

    @Override
    public void doFilter(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, javax.servlet.FilterChain chain) throws java.io.IOException, javax.servlet.ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
