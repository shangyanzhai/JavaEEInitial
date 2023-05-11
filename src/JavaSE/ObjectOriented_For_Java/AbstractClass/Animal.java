package JavaSE.ObjectOriented_For_Java.AbstractClass;

//被abstract修饰的类就是抽象类
//抽象类是普通类的超集，普通类能定义的则抽象类都可以定义，则子类实例化依然满足继承的原则，先调用父类的构造方法，再调用子类的构造方法
//抽象类与抽象方法存在的意义是在于强制子类重写某些方法，但抽象类仍满足is a原则，即子类必须满足继承要求才能继承抽象类，且每个子类只能继承一个抽象类
//抽象类无法直接实例化对象，但可以通过子类的向上转型进行实例化
//有抽象方法的类都是抽象类，但抽象类可以没有抽象方法，抽象类可以拥有【0-n】个抽象方法
//抽象方法没有方法体，但没有方法体并不一定是抽象方法
//子类继承抽象类，如果子类是 普通类，则需要覆写所有的抽象的方法
//abstract和final，private冲突，abstract强制抽象方法重写，但是private则只有类中可见，final修饰的方法则不能被重写，与abstract冲突
//所有final，private，abstract不能同时出现
public abstract class Animal {
    protected char name;
    protected int age;

    //构造方法
    Animal() {
        System.out.println("正在加载构造方法");
    }

    //方法
    protected final void eat() {//被final修饰的方法不能被重写，被final修饰的类不能被继承，则final不能和abstract修饰同一个方法或类时则会冲突
        System.out.println(this.name + "正在吃食物");
    }

    //抽象方法
    protected abstract void see();//抽象方法没有方法体，还有一类方法没有方法体，只有方法声明，native方法，本地方法

}