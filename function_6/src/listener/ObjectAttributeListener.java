package listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

//bjectAttributeListener类同时实现ServletRequestAttributeListener和HttpSessionAttributeListener接口
//所以用该类创建的监听器可以同时监听request,session对象属性的变化
@WebListener("listener.ObjectAttributeListener")
public class ObjectAttributeListener implements ServletRequestAttributeListener, HttpSessionAttributeListener {
    /*ServletRequestAttributeEvent对象用于监听request对象属性的变化*/
    //当往request对象中添加属性时,监听器会执行此方法
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        String name = srae.getName();  //ServletRequestAttributeEvent对象调用getName方法可以获取监听的属性名
        Object value = srae.getValue(); //ServletRequestAttributeEvent对象调用getName方法可以获取监听的属性值
        System.out.println("往request对象中添加了一个属性,属性名为:" + name + ",属性值为:" + value);
    }

    //删除request对象中的属性时,监听器会执行此方法
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("将名为" + srae.getName() + "的属性从request对象中删除了");
    }

    //修改request对象中的属性时,监听器会执行此方法
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        HttpServletRequest request = (HttpServletRequest) srae.getServletRequest();
        String name = srae.getName(); //获取修改的属性名
        System.out.println("将request对象中,名为" + name + "的属性由原值(" +srae.getValue() + ") 修改为新值(" + request.getAttribute(name) + ")");
    }

    /*HttpSessionBindingEvent对象用于监听session对象属性的变化*/
    //当往session对象中添加属性时,监听器会执行此方法
    public void attributeAdded(HttpSessionBindingEvent se) {
        String name = se.getName();
        Object value = se.getValue();
        System.out.println("往session对象中添加了一个属性,属性名为:" + name + ",属性值为:" + value);
    }

    //移除session对象中的属性时,监听器会执行此方法
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("将名为" +se.getName() + "的属性从session对象中删除了");
    }

    //修改session对象中的属性时,监听器会执行此方法
    public void attributeReplaced(HttpSessionBindingEvent se) {
        HttpSession session =  se.getSession();
        String name = se.getName(); //获取修改的属性名
        System.out.println("将session对象中,名为" + name + "的属性由原值(" +se.getValue() + ") 修改为新值(" + session.getAttribute(name) + ")");
    }
}
