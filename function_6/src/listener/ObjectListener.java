package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//ObjectListener类同时实现ServletRequestListener, ServletContextListener, HttpSessionListener接口
//所以用该类创建的监听器可以同时监听request,session,application对象的创建和销毁
@WebListener("listener.ObjectListener")
public class ObjectListener implements ServletRequestListener, ServletContextListener, HttpSessionListener {
    private String ipAddr;

    /*ServletRequestEvent对象用于监听request对象的创建和销毁*/
    //客户端每发送一次请求,监听器都会执行该方法(因为客户端每发送一次请求,对应的都会创建一个request对象)
    public void requestInitialized(ServletRequestEvent sre) {
        //ServletRequestEvent对象调用getServletRequest()可以返回它正在监听的request对象
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        ipAddr = request.getRemoteAddr();
        String url = request.getRequestURI();

        url = request.getQueryString() == null? url : url + "?" + request.getQueryString();
        request.setAttribute("CreateRequest", System.currentTimeMillis()); //记录请求开始的时间(记录request对象是何时创建的)
        request.setAttribute("url", url);
        System.out.println("ip地址为" + ipAddr + "的客户正在请求" + url);
    }

    //请求处理完毕将要被销毁request对象前,监听器会执行该方法
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        //用请求结束的时间(记录request对象是何时销毁的)减去请求开始的时间,就可以得到用户发出请求到请求结束共耗时多久
        long time = System.currentTimeMillis() - (Long) request.getAttribute("CreateRequest");
        System.out.println("ip地址为" + ipAddr + "的客户对" + request.getAttribute("url") + "的请求已结束,共耗时" + time + "毫秒");
    }

    /*ServletContextEvent对象用于监听context对象的创建和销毁,context代表当前的web应用程序(web项目)*/
    //服务器启动时,监听器执行该方法(因为web项目是部署在服务器上的,服务器启动web项目就随之运行,对应的就会创建一个context对象)
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("服务启动!!!");
    }

    //服务器关闭时(context对象被销毁时),监听器执行该方法
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("服务器关闭!!!");
    }

    /*HttpSessionEvent对象用于监听session对象的创建和销毁*/
    //创建session对象时,监听器执行该方法(当客户端第一次请求web工程中的一个页面时,在服务器中就会创建一个session对象与客户端对应,用于记录客户端与服务器之间的会话)
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("ip地址为" + ipAddr + "的客户与服务器的会话开始了! 此时创建一个id为" + session.getId() + "的session对象记录客户端与服务器之间的会话");
    }

    //session对象销毁时,监听器执行该方法(关闭客户端浏览器,超时或执行invalidate()方法都会使session对象被销毁,这就意味着客户端与服务器之间的会话结束)
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("ip地址为" + ipAddr + "的客户与服务器的会话结束了! 此时销毁id为" + se.getSession().getId() + "的session对象");
    }
}
