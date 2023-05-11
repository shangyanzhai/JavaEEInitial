package JavaDS.ComparisonOfObjects;

import java.util.Comparator;

// 比较两个 Student 对象的天平
public class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        // o1 和 o2 比较
        // < 0: o1 小于 o2
        // == 0: o1 等于 o2
        // > 0： o1 大于 o2
        return o1.id - o2.id;
    }
}
