package JavaDS.GenericType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * 泛型
 */
public class Generalised {
    public static void main(String[] args) {
        /**
         * 泛型 -- 变化的类型
         * <...>闭合的尖括号 ———— Java语法中泛型的特征
         * 泛型分为 定义泛型（类 or 方法）和 使用泛型
         * 现阶段，学会如何使用别人定义好的泛型类（Iterable ,Collection ,List）
         *
         * 泛型 Object[]
         * Object是所有类的祖先类
         * 上层引用可以指向下层对象
         * 只能用于引用类型
         * Java 的泛型只在编译阶段起作用
         */
        Iterable<String> iterable = null;//现在的元素是String类型
        Collection<String> collection = null;

        //用法
        List list1 = new ArrayList();//该list里面什么类型都可以放
        list1.add(1);
        list1.add("hello");
        list1.add(new Scanner(System.in));
        //对比
        List<String> list2 = new ArrayList<String>();
//        list2.add(1);//此时，除了字符串类型以外，放入其他类型都会报错
//        list2.add("hello");
//        list2.add(new Scanner(System.in));//此时，除了字符串类型以外，放入其他类型都会报错
    }
}
