package JavaSE.ObjectOriented_For_Java.Port;

import JavaSE.ObjectOriented_For_Java.AbstractClass.*;

/**
 * 类Mouse 继承 接口USB
 */

/**
 * //在Java中，子类使用implements表示实现一个父接口，必须覆写所有的抽象方法 ^ + O 一键生成覆写方法
 * //一个子类可以通过implements关键字实现多个父接口（开发中的接口优先原则，优先考虑使用接口）（接口的多 实现 ）
 * //一般来说，在Java中，除了特殊的一些接口外（USB接口，现实存在的接口），接口一般采用大写的I开头，子类使用impl结尾表示是一个接口的实现子类
 * //子类如果是个普通类，其若实现多个父接口，必须覆写这些接口的所有抽象方法，多个父接口通过","隔开
 * //接口和类之间的关系：子类同时继承父类和实现接口的顺序
 * //若有子类同时需要继承一个抽象类，实现父接口，请先使用extends继承一个父类，然后使用implements实现接口
 */

public class Mouse extends Write implements USB{
    @Override
    public void plugIn() {

    }

    @Override
    public void work() {
        this.write();
    }
    //定义了这个方法，采用的都是接口参数，接受所有该接口的子类
    //都属于向上转型，多态
    public static void Write(USB iusb){
        iusb.plugIn();
    }

    public static void main(String[] args) {
        USB usb = new Mouse();//通过子类的向上转型实例化对象
        usb.plugIn();//多态，向上转型的经典使用，不要看前面的类或接口，看new在哪
        System.out.println(USB.MSG);//即不调用对象访问，为静态常量（static）
        usb.write();
    }
}

