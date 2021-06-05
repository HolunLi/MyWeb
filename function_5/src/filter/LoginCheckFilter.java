package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
在同一个Web工程中,有的页面(比如welcome.jsp)是只能在登录成功后才能访问的,不能直接通过浏览器地址栏访问。所以需要编写一个登录验证过滤器对客户端
请求的页面进行过滤。
*/
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();//获取用户请求的URL
        System.out.println(requestURI);

        //如果客户请求的是登录页面login.jsp或LoginCheckServlet,不需要进行过滤,直接放行
        if (requestURI.contains("login.jsp") || requestURI.contains("LoginCheckServlet")) {
            filterChain.doFilter(request, response);
        }
        else {
            /*有些页面是在登录成功的情况下才能访问(比如:welcome.jsp),不能直接通过浏览器地址栏访问,所以在访问这类页面时,需要判断用户是否登录*/
            //如果用户已登录,就将请求放行(在LoginCheckServlet中只有用户名和密码都正确,即登录成功的情况下才会将用户名和密码添加到session对象中)
            if (request.getSession().getAttribute("uname") != null) //如果sessions中有uname属性代表登录成功
                filterChain.doFilter(request, response);
            else                                                       //session中没有uname属性代表未登录,直接返回登录页面进行登录
                response.sendRedirect("login.jsp");
        }
    }
}
