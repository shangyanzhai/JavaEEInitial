package JavaDS.Sort;

import java.util.Arrays;
import java.util.Random;

public class SortTesting {
    private static final Random rand = new Random();

    public static long[] generateRandomArray(int size) {
        long[] res = new long[size];
        for (int i = 0; i < size; i++) {
            res[i] = rand.nextLong();
        }
        return res;
    }

    public static long[] generateSortedArray(int size) {
        long[] res = generateRandomArray(size);
        Arrays.sort(res);
        return res;
    }

    public static long[] generatedReversedArray(int size) {
        long[] res = generateSortedArray(size);
        for (int i = 0; i < size / 2; i++) {
            res[i] = res[size - i - 1];
        }
        return res;
    }

    private static void testSort(long[] array) {
        long[] copy = Arrays.copyOf(array, array.length);

//        Sort.bubbleSort(array);
//        Sort.selectSort(array);
//        Sort.insertSort(array);
//        Sort.heapSort(array);
//        Sort.quickSort(array);
        double s = System.currentTimeMillis();
//        MySort.bubbleSort(array);
//        MySort.selectSort(array);
//        MySort.insertSort(array);
//        MySort.heapSort(array);
//        MySort.shellSort(array);
//        MySort.quickSort(array);
        MySort.quickSort非递归版本(array);
        double e = System.currentTimeMillis();
        System.out.println((e - s) / 1000 + "s");
//        System.out.println(Arrays.toString(array));
        Arrays.sort(copy);

        if (!Arrays.equals(array, copy)) {
            throw new RuntimeException("排序错误");
        }
    }

    public static void main1(String[] args) {
        int size = 500_0000;

        long[] arr1 = generatedReversedArray(size);
        long[] arr2 = generatedReversedArray(size);
        long[] arr3 = generatedReversedArray(size);
        long[] arr4 = generatedReversedArray(size);

        long s, e, ms;
        double sec;

//        s = System.currentTimeMillis();
//        Sort.bubbleSort(arr1);
//        e = System.currentTimeMillis();
//        ms = e - s;
//        sec = ms / 1000.0;
//        System.out.printf("冒泡排序: %.2f\n", sec);
//
//        s = System.currentTimeMillis();
//        Sort.selectSort(arr2);
//        e = System.currentTimeMillis();
//        ms = e - s;
//        sec = ms / 1000.0;
//        System.out.printf("选择排序: %.2f\n", sec);
//
//        s = System.currentTimeMillis();
//        Sort.insertSort(arr3);
//        e = System.currentTimeMillis();
//        ms = e - s;
//        sec = ms / 1000.0;
//        System.out.printf("插入排序: %.2f\n", sec);

        s = System.currentTimeMillis();
        Sort.heapSort(arr4);
        e = System.currentTimeMillis();
        ms = e - s;
        sec = ms / 1000.0;
        System.out.printf("堆排序: %.2f\n", sec);
    }

    public static void main(String[] args) {
//        int size = 5_0000;
        int size = 1_0000;
        long[] arr1 = generateRandomArray(size);
        testSort(arr1);

        long[] arr2 = generateSortedArray(size);
        testSort(arr2);

        long[] arr3 = generatedReversedArray(size);
        testSort(arr3);
    }
}
