package JavaEE.Threads.DemoOfThread;
import java.util.*;

/**
 * 1. 利用多线程提升程序执行速度（减少执行时长）
 *
 * 在多核情况下，使用多线程可以提升执行速度，很容易理解。
 * 问题是在单核情况下，使用多线程是否也可能提升速度？ —— 也是可能的。因为增加了抢到 CPU 的概率了。
 *
 * 是否线程数越多，速度就越快？   不行，有个极限的。
 * 线程数提升，带来的抢到 CPU 的概率是有个极限的，但由于线程数变多之后，调度器成本增加，导致收益可能为负，反倒执行效率降低。
 */
//首先先对四个区间进行一次插入排序
//当四个区间均有序了，则对其进行一次归并排序
public class SortDemoOfThreads {
    public static void insertSort(long[] arr,int fromIdx,int toIdx){
        if(arr == null || arr.length == 1 || arr.length == 0){
            return;
        }
        //有序区间  [formIdx,i)
        //无序区间  [i,toIdx)

        for(int i = fromIdx;i < toIdx;i++){
            long temp = arr[i];
            int j;
            for(j = i - 1;j >= fromIdx && temp < arr[j];j--){
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = temp;
        }
    }

    // 区间类
    private static class Interval {
        private final long[] array;
        private final int fromIdx;
        private final int toIdx;
        private int currentIdx;

        public Interval(long[] array, int fromIdx, int toIdx) {
            this.array = array;
            this.fromIdx = fromIdx;
            this.toIdx = toIdx;
            this.currentIdx = fromIdx;
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public long peek() {
            return array[currentIdx];
        }

        public void pop() {
            currentIdx++;
        }

        public int size() {
            return toIdx - currentIdx;
        }
    }

    private static void merge(long[] array) {
        long[] extra = new long[10000];
        int extraIdx = 0;

        // list 里面的区间是还有元素的区间
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(array, 0, 2500));
        list.add(new Interval(array, 2500, 5000));
        list.add(new Interval(array, 5000, 7500));
        list.add(new Interval(array, 7500, 10000));

        while (list.size() > 1) {
            int minIdx = 0;
            long minE = list.get(0).peek();

            for (int i = 1; i < list.size(); i++) {
                long e = list.get(i).peek();
                if (e < minE) {
                    minIdx = i;
                    minE = e;
                }
            }

            extra[extraIdx++] = minE;

            Interval interval = list.get(minIdx);
            interval.pop();
            if (interval.isEmpty()) {
                list.remove(minIdx);
            }
        }

        Interval interval = list.get(0);
        System.arraycopy(interval.array, interval.currentIdx, extra, extraIdx, interval.size());

        System.arraycopy(extra, 0, array, 0, array.length);
    }

    // 单线程完成
    public static void main1(String[] args) {
        Random rand = new Random(20230507);
        long[] array = new long[10000];
        for (int i = 0; i < 10000; i++) {
            array[i] = rand.nextLong();
        }


        long[] copy = Arrays.copyOf(array, array.length);
//        Arrays.sort(copy);
        Arrays.parallelSort(copy);

        double s = System.currentTimeMillis();
        insertSort(array, 0, 2500);
        insertSort(array, 2500, 5000);
        insertSort(array, 5000, 7500);
        insertSort(array, 7500, 10000);
        merge(array);
        double e = System.currentTimeMillis();
        System.out.println((e - s) / 1000 + "s");

        System.out.println(Arrays.equals(copy, array));
    }

    static class SortThread extends Thread {
        private final long[] array;
        private final int fromIdx;
        private final int toIdx;

        SortThread(long[] array, int fromIdx, int toIdx) {
            this.array = array;
            this.fromIdx = fromIdx;
            this.toIdx = toIdx;
        }

        @Override
        public void run() {
            insertSort(array, fromIdx, toIdx);
        }
    }

    // 多线程方式
    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random(20230507);
        long[] array = new long[10000];
        for (int i = 0; i < 10000; i++) {
            array[i] = rand.nextLong();
        }


        long[] copy = Arrays.copyOf(array, array.length);
//        Arrays.sort(copy);
        Arrays.parallelSort(copy);


        double s = System.currentTimeMillis();
        // 主线程负责创建 4 个线程，分别对小区间做插入排序
        SortThread t1 = new SortThread(array, 0, 2500);
        SortThread t2 = new SortThread(array, 2500, 5000);
        SortThread t3 = new SortThread(array, 5000, 7500);
        SortThread t4 = new SortThread(array, 7500, 10000);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // 主线程等待 4 个线程全部结束之后（4 个区间都有序了），再进行归并
        t1.join();
        t2.join();
        t3.join();
        t4.join();


        merge(array);
        double e = System.currentTimeMillis();
        System.out.println((e - s) / 1000 + "s");

        System.out.println(Arrays.equals(copy, array));
    }
}
