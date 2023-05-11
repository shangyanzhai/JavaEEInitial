package JavaDS.Queue;
/**
 * 我的优先级队列
 * 使用堆的方式来实现
 * PriorityQueue底层使用了堆这种数据结构，而堆实际就是在完全二叉树的基础上进行了一些调整。
 */

import java.util.Comparator;

public class MyPriorityQueue<E> {
    private E[] array;
    private int size;
    private final Comparator<E> comparator;

    // 假设 E 是 Comparable 的实现
    private MyPriorityQueue() {
        comparator = null;
        array = (E[]) new Object[100];
        size = 0;
    }

    // 传入了比较器， 元素大小关系的比较使用比较器比较
    private MyPriorityQueue(Comparator<E> comparator) {
        this.comparator = comparator;
        array = (E[]) new Object[100];
        size = 0;
    }

    private int cmp(E e1, E e2) {
        int cmp;
        if (comparator != null) {
            // 构建对象时，传入了比较器，就使用比较器进行比较
            return comparator.compare(e1, e2);
        } else {
            Comparable<E> c;
            try {
                c = (Comparable<E>) e1;
            } catch (ClassCastException exc) {
                throw new RuntimeException("元素类型不具备比较能力");
            }

            return c.compareTo(e2);
        }
    }

    private void shiftDown(int index) {
        while (true) {
            int leftIdx = 2 * index + 1;
            if (leftIdx >= size) {
                return;
            }

            int minIdx = leftIdx;
            // 对象的大小比较  array[leftIdx + 1]    array[leftIdx]

            if (leftIdx + 1 < size && cmp(array[leftIdx + 1], array[leftIdx]) < 0) {
                minIdx++;
            }

            // 再比较 array[index] <= array[minIdx]
            if (cmp(array[index], array[minIdx]) <= 0) {
                return;
            }

            // 交换
            E t = array[index];
            array[index] = array[minIdx];
            array[minIdx] = t;

            index = minIdx;
        }
    }

    private void shiftUp(int index) {
        while (true) {
            if (index == 0) {
                return;
            }

            int parentIdx = (index - 1) / 2;
            // array[parentIdx] <= array[index] 满足条件
            if (cmp(array[parentIdx], array[index]) <= 0) {
                return;
            }

            // 交换
            E t = array[index];
            array[index] = array[parentIdx];
            array[parentIdx] = t;

            index = parentIdx;
        }
    }

    public void offer(E e) {
        array[size++] = e;
        shiftUp(size - 1);
    }

    public E peek() {
        if (size <= 0) {
            throw new RuntimeException("空的");
        }

        return array[0];
    }

    public E poll() {
        if (size <= 0) {
            throw new RuntimeException("空的");
        }

        E e = array[0];

        array[0] = array[size - 1];
        array[size - 1] = null;
        size--;
        shiftDown(0);

        return e;
    }
}
