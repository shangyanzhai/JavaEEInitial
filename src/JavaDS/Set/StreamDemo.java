package JavaDS.Set;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamDemo {

    public static void main(String[] args) {
        Set<Integer> inSet = new TreeSet<>();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            inSet.add(random.nextInt(100));
        }
        // 可能有重复元素，所以最多有 30 个元素
        System.out.println(inSet);
        System.out.println("========================");

//        Stream<Integer> stream = inSet.stream();
//        Stream<Integer> stream1 = stream.map();
//        Stream<Integer> stream2 = stream.filter();
//        Set<Integer> outSet = stream2.collect(Collectors.toSet());

        {
            // 引入 lambda 表达式
            Set<Integer> outSet = inSet.stream()
                    .filter(e -> e % 2 == 0)    // 形参是 e，返回值是 e % 2 == 0
                    .map(e -> e * 2)
                    .collect(Collectors.toSet());
        }

        {
            // 引入匿名类
            Set<Integer> outSet = inSet.stream()
                    .filter(new Predicate<Integer>() {
                        @Override
                        public boolean test(Integer integer) {
                            return integer % 2 == 0;
                        }
                    })
                    .map(new Function<Integer, Integer>() {
                        @Override
                        public Integer apply(Integer integer) {
                            return integer * 2;
                        }
                    })
                    .collect(Collectors.toSet());
        }
        //因为java中无法直接传入一个函数，所以需要使用一个接口
        //使用接口，从而来传入一个函数
        {

            把一个数乘以2 obj1 = new 把一个数乘以2();
            判断偶数 obj2 = new 判断偶数();
            Set<Integer> outSet = inSet.stream()
                    .filter(obj2)
                    .map(obj1)
                    .collect(Collectors.toSet());

            System.out.println(outSet);
        }
    }

    static class 把一个数乘以2 implements Function<Integer, Integer> {

        @Override
        public Integer apply(Integer integer) {
            return integer * 2;
        }
    }

    static class 判断偶数 implements Predicate<Integer> {
        @Override
        public boolean test(Integer integer) {
            return integer % 2 == 0;
        }
    }
}