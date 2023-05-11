package JavaDS.ComparisonOfObjects;


import java.util.Objects;

public class Student {
    public int id;  // 学号
    public String name; // 姓名

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // 满足：自反性   x 和 x 自身是相等的
    // 满足：传递性   x 和 y 相等 并且 y 和 z 相等，x 和 z 相等性
    //               x 和 y 相等，y 和 x 相等
//    @Override
//    public boolean equals(Object o) {
//        // this 指向的对象 和 o 指向的对象是否相等
//        // 1. this 一定不是 null，如果 o 是 null，则一定不相等
//        if (o == null) {
//            return false;
//        }
//        // 2. o 的类型是否是 Person 或者 Person 的子类
//        if (!(o instanceof Student)) {
//            // o 不是一个 Student 对象
//            return false;
//        }
//
//        // 3. 比较关键属性即可
//        Student s = (Student) o;    // 向下转型
//        return this.id == s.id && this.name.equals(s.name);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(name, student.name);
    }

    public static void main(String[] args) {
        Student s1 = new Student(1, "张三");
        Student s2 = new Student(1, "张三");

        // 理论： s1 和 s2 不满足同一性，但是满足相等性
        // 作为类的对象的使用者
        if (s1.equals(s2)) {
            System.out.println("s1 和 s2 相等");
        } else {
            System.out.println("s1 和 s2 不相等");
        }

        StudentComparator sc = new StudentComparator();
        int cmp = sc.compare(s1, s2);
        if (cmp < 0) {
            System.out.println("s1 小于 s2");
        } else if (cmp == 0) {
            System.out.println("s1 等于 s2");
        } else {
            System.out.println("s1 大于 s2");
        }
    }
}
