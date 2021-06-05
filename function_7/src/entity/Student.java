package entity;

public class Student {
    private String xh;
    private String name;
    private int age;
    private String yx;

    public Student() {
    }

    public Student(String xh, String name, int age, String yx) {
        this.xh = xh;
        this.name = name;
        this.age = age;
        this.yx = yx;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getYx() {
        return yx;
    }

    public void setYx(String yx) {
        this.yx = yx;
    }
}
