package JavaSE.ObjectOriented_For_Java.Inherit;

/**
 * 类的定义
 * 例： 狗
 */
 public class Dog {

    private String Name;//成员变量
    private String Color;

    public int age;
    protected String eyes;

    protected String mouth;

    public  static String living;//所有的狗都属于动物，所以living这个属性和具体的对象无关是一个类属性

    //构造方法，如果本身没有定义构造方法，编译后的class文件将自动创建一个无参构造，
    //当如果再文件中自身定义了构造方法，无参方法则不会出现，如果在需要使用无参构造，则需要自己定义
    //构造方法是一个没有返回值声明 + 名字同类名一样的
    public Dog(){//实际是一个无参构造方法，构造方法可以重载，所以可以有多个，参数数量不同的构造方法
        System.out.println("构造方法被调用");
    }

    public Dog(String Name) {
        this();
        this.Name = Name;
    }

    public Dog(String Name, String Color){//实际上是一个有参构造方法，可以通过使用this方法，从而避免出现参数使用就近原则
        this(Name);
        this.Color = Color;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    //成员方法
    public void bark(){//实际上是一个成员方法

        System.out.println(Name + "汪汪叫");
    }
    public void wag(){

        System.out.println(Name + "摇尾巴");
    }
    public void dog(String Name,String Color){
        this.Name = Name;
        this.Color = Color;
        System.out.println(this);
    }
    public static void main(String[] args) {
//        Dog dog1 = new Dog("小黄","黄");
//        Dog.living = "Anima";
//        dog1 = null;
//        System.out.println(dog1.Name);
//        System.out.println(dog1.living);
        //
//        static JavaSE.String eyes = "黄";//类中无法定义静态变量
        //
//        System.out.println(living);
//        System.out.println(Color);//错误
        //
//        Dog dog1 = new Dog();//其中new后面的Dog(),实际上是构造方法，而dog1是一个引用数据类型，引用的是由new出来的对象
//        dog1.dog("小黄","黄");
//        dog1.Color = "黄";
//        dog1.Name = "小黄";
//        Dog.living = "Animal";
//        dog1.bark();
//        dog1.wag();
//        dog1.dog("小黑","黑");
//
//        Dog dog2 = new Dog("小黄");
//        dog2.wag();
//        dog2.bark();
//        dog2.dog("小黑", "黑");
//
//        Dog dog3 = new Dog("小黄","黄");
//        dog3.bark();
//        dog3.wag();
    }
}
