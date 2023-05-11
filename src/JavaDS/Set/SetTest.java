package JavaDS.Set;

import java.util.*;
public class SetTest {
    public static void main(String[] args) {
        // 1. 构建一个 empty 的集合对象
        // 2. 元素需要具备大小比较的能力（Comparable or Comparator）
        Set<Integer> set = new TreeSet<>();

        System.out.println(set.size());    // 0
        System.out.println(set.isEmpty());    // true;

        set.add(13);
        set.add(9);
        set.add(15);
        set.add(7);

        System.out.println(set.size());    // 4
        System.out.println(set.isEmpty());    // false;

        set.add(1);
        set.add(7);    // 重复
        set.add(12);
        set.add(9);    // 重复

        System.out.println(set.size());    // 6
        System.out.println(set.isEmpty());    // false;

        System.out.println(set.contains(1));    // true;
        System.out.println(set.contains(2));    // false

        System.out.println(set.remove(1));        // true;
        System.out.println(set.remove(2));        // false;

        System.out.println(set.size());    // 5
        System.out.println(set.isEmpty());    // false;

        System.out.println("迭代：");
        /**
         * 迭代器
         */
        // 迭代器
        // TreeSet 内部默认使用中序遍历迭代，即元素按照大小顺序迭代
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            Integer e = it.next();
            System.out.println(e);
        }

        // 如果要对集合中的每个元素都进行迭代，可以省略 foreach
        // 只要自身这个类实现了Iterator这个接口，就可以这样写
        for (Integer e : set) {
            System.out.println(e);
        }

        // TreeSet 中的元素是否可以为 null
        // set.add(null) 是不可以的
        // Integer 能不能是 null
        for (int e : set) {    // 隐含了一个自动拆箱 Integer -> int
            System.out.println(e);
        }
    }
}
