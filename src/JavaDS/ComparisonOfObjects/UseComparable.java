package JavaDS.ComparisonOfObjects;

public class UseComparable {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "World";

        int cmp = s1.compareTo(s2); // 字符挨个比较
        if (cmp < 0) {
            System.out.println("s1 小于 s2");
        } else if (cmp == 0) {
            System.out.println("s1 等于 s2");
        } else {
            System.out.println("s1 大于 s2");
        }
    }
}