package JavaDS.ComparisonOfObjects;

public class ComparableStudent implements Comparable<ComparableStudent> {
    public int id;
    public String name;

    public ComparableStudent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // 1. x 和 x 总是相等
    // 2. x < y 则 y > x
    // 3. x < y && y < z => x < z
    // 4. 和 equals 匹配
    @Override
    public int compareTo(ComparableStudent o) {
        // this 和 o 谁大，谁小
        if (o == null) {
            return 1;   // this > null
        }

        return this.id - o.id;

//        if (this.id < o.id) {
//            return -1;
//        } else if (this.id == o.id) {
//            return 0;
//        } else {
//            return 1;
//        }
    }

    public static void main(String[] args) {
        ComparableStudent s1 = new ComparableStudent(2, "张三");
        ComparableStudent s2 = new ComparableStudent(2, "张三");
        int cmp = s1.compareTo(s2);
        if (cmp < 0) {
            System.out.println("s1 小于 s2");
        } else if (cmp == 0) {
            System.out.println("s1 等于 s2");
        } else {
            System.out.println("s1 大于 s2");
        }
    }
}
