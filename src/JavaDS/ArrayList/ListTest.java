package JavaDS.ArrayList;

import java.util.*;

/**
 * List类的测试，方法
 */

/**
 * List(线性表)的特征：
 * 1.线性表的元素有逻辑上的前后关系
 * 2.每个元素都有一个唯一的位置相关联 --下标[0,size)
 */
public class ListTest{
    public static void main(String[] args) {
        List list = new ArrayList();

        list.add(200);
        list.add(1);
        System.out.println(list);
        list.add(30);

        System.out.println(list);

        System.out.println(list.get(0));

        list.add(0,4000);
        System.out.println(list);

        list.add(list.size(),500);//此时不报错，因为相当于尾插
//        list.add(list.size() + 1,500);//此时不等于尾插，发生了越界访问
        System.out.println(list);

        list.add(0,500);
        System.out.println(list.indexOf(500));//查找500，从前往后
        System.out.println(list.lastIndexOf(500));//从后往前

        System.out.println(list);
        list.remove(0);//删除 0 位置的数
        System.out.println(list);

        System.out.println(list.subList(1, 3));//切出区间为[1,3)的子集

    }
}
