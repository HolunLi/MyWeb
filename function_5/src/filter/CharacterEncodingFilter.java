package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

//字符编码过滤器
@WebFilter(filterName = "characterEncodingFilter", urlPatterns = "/*", initParams = @WebInitParam(name = "encode", value = "UTF-8"))
public class CharacterEncodingFilter implements Filter {
    private String encode;

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encode);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        encode = filterConfig.getInitParameter("encode");
    }
}
