package entity;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

public class Student implements HttpSessionBindingListener, HttpSessionActivationListener {
    private String name;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sessionWillPassivate(HttpSessionEvent se) {

    }

    public void sessionDidActivate(HttpSessionEvent se) {

    }

    public void valueBound(HttpSessionBindingEvent event) {
        String id = event.getSession().getId();
        String name = event.getName(); //获取该对象被绑定到session对象的哪个属性上
        System.out.println("将名为" + this.name + "的对象绑定到id为" + id + "的session对象的" + name + "属性上");
    }

    public void valueUnbound(HttpSessionBindingEvent event) {
        String id = event.getSession().getId();
        String name = event.getName();
        System.out.println("将名为" + this.name + "的对象与id为" + id + "的session对象的" + name + "属性解除绑定");
    }
}
