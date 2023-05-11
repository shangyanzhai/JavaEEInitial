package JavaSE.ObjectOriented_For_Java.Inherit;


public class Cat extends Animal{
    protected String color;

    public Cat(String name,int age,String color) {
        super(name,age);
        this.color = color;
        System.out.println("正在调用构造函数Cat");
    }
    @Override
    public void eat(){//使用注解检查是否重写成功
        System.out.println(this.name + "正在吃猫粮");
    }
    public static void main(String[] args) {
        Animal cat1 = new Cat("77",7,"黄");
        cat1.eat();
    }

}
