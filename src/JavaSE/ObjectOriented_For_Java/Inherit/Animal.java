package JavaSE.ObjectOriented_For_Java.Inherit;

public class Animal {
    protected String name;
    protected int age;

    public Animal(String name,int age) {
        this.name = name;
        this.age = age;
        System.out.println("正在调用构造函数Animal");
    }
    public void eat(){
        System.out.println(name + "正在吃饭");
    }

}
