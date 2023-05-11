package JavaSE.ObjectOriented_For_Java.Port;

/**
 * 定义接口USB
 */

/**
 * //在Java中，使用interface关键字定义接口，接口中只有全局常量和抽象方法（TDK8之前）
 *
 * //java中的抽象方法就是以abstract修饰的方法，这种方法只声明返回的数据类型、方法名称和所需的参数，没有方法体，也就是说抽象方法只需要声明而不需要实现
 *
 * //从JDK8开始，接口也允许存在普通方法，接口中的普通方法使用default关键字来定义，有方法体，子类实现接口之后可以直接使用接口中的普通方法
 * //接口也无法实例化对象，必须通过子类的向上转型进行实例化
 * //接口与接口之间可以使用extends关键字，接口可以继承父接口，接口的继承是多继承，没有is a原则
 */
interface A{

}
interface B{

}
public interface USB extends A,B {
    //接口与接口之间可以使用extends关键字，接口可以继承父接口，接口的继承是多继承，
    // 没有is a原则,接口不能用extends继承一个类，如果能继承，则与接口只有全局常量与抽象方法这一特性冲突
    // 因为接口中普遍只存在全局常量和抽象方法，因此，接口中public static final abstract统统可以省略不写，只有接口可以省略
//    public static final JavaSE.String MSG = "全局常量";//全局常量
        String MSG = "全局常量";
        //    public abstract void plugIn();//抽象方法
        void plugIn();//抽象方法
        //    public abstract void work();
        void work();
        //普通方法
//    public default void write(){
//        System.out.println("正在调用，写入中");
//    }
        default void write(){//只是用default来定义普通方法，权限实际上是public
            System.out.println("正在调用，写入中");
        }


}
