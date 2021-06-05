package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "myFilter2", urlPatterns = "/page.jsp")
public class MyFilter2 implements Filter {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("第二个过滤器拦截请求");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("第二个过滤器拦截响应,随后将响应放行");
    }
}
