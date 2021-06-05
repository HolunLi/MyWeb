package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//客户端只要请求page.jsp页面,会先经过过滤器myFilter过滤,过滤后的请求被放行后抵达目的地
@WebFilter(filterName = "myFilter3", urlPatterns = "/page.jsp")
public class MyFilter3 implements Filter {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("第一个过滤器拦截请求");
        filterChain.doFilter(servletRequest, servletResponse); //将请求和响应放行。如果不将请求放行,客户就无法请求到目标页面。
        System.out.println("第一个过滤器拦截响应,随后将响应放行"); //程序执行到这,说明之前拦截的请求已被放行,抵达的目的地。此时是将响应拦截
    }
}
