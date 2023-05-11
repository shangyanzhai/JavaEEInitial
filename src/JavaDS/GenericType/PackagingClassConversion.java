package JavaDS.GenericType;

/**
 * 包装类转化&比较
 *
 * 自动拆装箱
 */
public class PackagingClassConversion {
    public static void main(String[] args) {
        /**
         * Java 的泛型只支持引用，不支持基本类型
         * 包装类：
         * byte -》 class Byte
         * short -》 class Short
         * int -》 class Integer
         * long -》 class Long
         * float -》 class Float
         * double -》 class Double
         * char -》 class Charater
         * boolean -》 class Boolean
         *
         * 包装类已经是引用对象了，则不能使用 == 进行比较 必须使用equals
         *    ==    : 同一性
         *   equals ： 相等性
         */
        int a1 = 1000;//a1 是基本类型
//        Integer a2 = new Integer(1);//完整写法
        Integer a2 = 1000;//a2 是引用类型
        System.out.println(a1);
        System.out.println(a2);

        Integer a3 = 1000;
        System.out.println(a2 == a3);
        System.out.println(a2.equals(a3));

        a1 = a2.intValue();//unboxxing 拆箱过程  简写，自动拆装箱 a1 = a2;
        a2 = Integer.valueOf(a1);//boxing 装箱过程 同理
        /**
         * X(类型) a ;    Y(类型) b ;
         * a = b; //符合语法不报错，请询问X类型和Y类型之间可能是哪些情况？
         * 1）X类型和Y类型是同一类型
         * 2）X类型和Y类型都是基本类型，但X类型表示的数据范围 > Y类型表示的数据范围
         * double > float > long > int > char > short >byte
         * 3）X类型和Y类型都是引用类型，并且，X类型是Y类型的上级类型
         *      a) X 是一个接口 Y 是一个类 ，Y实现了（implements）X接口
         *      b) X 是一个接口 Y 是一个接口 ，Y继承 (extends)X接口
         *      c) X 是一个类  Y 是一个类 ，Y继承 (extends)X接口
         * 4)自动拆箱 和 自动装箱 即上述所列举的那样
         */
        /**
         * X(类型) a ;    Y(类型) b ;
         * a = (X) b;  //这个语法满足以下条件成功
         * 1) 隐式转换可以成功的情况
         * 2）基本类型 :double > float > long > int > char > short >byte
         *      此时范围大转范围小 但X类型表示的数据范围 < Y类型表示的数据范围
         * 3）引用类型： 必须在继承链上，从上往下转
         */
    }
}
