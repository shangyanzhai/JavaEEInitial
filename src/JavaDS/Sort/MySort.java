package JavaDS.Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MySort {
    //冒泡排序
    public static void bubbleSort(long[] arr){
        if(arr == null || arr.length == 0 || arr.length == 1){
            return;
        }
        int size = arr.length;
        // 无序空间 有序空间
        // [0 , size - i)[size - i,size)
        for (int i = 0; i < size - 1; i++) {
            boolean position = true;
            for (int j = 1; j < size - i; j++) {
                if(arr[j] < arr[j - 1]){
                    swap(arr,j - 1,j);
                    position = false;
                }
            }
            if(position){
                break;
            }
        }
    }

    // 对 array 的 [fromIndex, toIndex)
    // fromIndex 和 toIndex 一定合法
    public static void bubbleSortRange(long[] arr, int fromIndex, int toIndex) {
        if(arr == null || arr.length == 0 || arr.length == 1){
            return;
        }
        //无序区间 有序区间
        //[fromIndex , i)[i ,toIndex)

        for(int i = toIndex;i >= fromIndex;i--){
            for(int j = fromIndex;j < i - 1;j++){
                if(arr[j] > arr[j + 1]){
                    swap(arr,j,j+ 1);
                }
            }
        }
    }

    //选择排序 每一次遍历以后都将最大的数移到最后面的有序区间前面
    //无序区间 有序区间
    //[0,size - i)[size - i,size)
    public static void selectSort(long[] arr){
        if(arr == null || arr.length == 0 || arr.length == 1){
            return;
        }
        int size = arr.length;
        for (int j = size - 1; j > 0; j--) {
            int max = 0;
            for (int i = 1; i <= j; i++) {
                if(arr[i] > arr[max]){
                    max = i;
                }
            }
            swap(arr,max,j);
        }
    }

    //每次将有序地插入有序区间的最后面
    //有序区间 无序区间
    //[0,i)[i,size)
    public static void selectSort1(long[] arr){
        if(arr == null || arr.length == 0 || arr.length == 1){
            return;
        }
        int size = arr.length;
        for(int i = 0;i < size;i++){
            int minIndex = i;
            for(int j = i + 1;j < size;j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            swap(arr,minIndex,i);
        }
    }

    // 选择排序
    // [有序（小）][无序][有序（大）]
    //[0,left)[left,right)[right ,size)
    public static void selectSort2(long[] arr){
        if(arr == null || arr.length == 0 || arr.length == 1){
            return;
        }
        int size = arr.length;
        int left = 0;
        int right = size;
        while(right - left > 1){
            int minIndex = left;
            int maxIndex = right - 1;
            for(int i = left;i < right;i++){
                if(arr[i] > arr[maxIndex]){
                    maxIndex = i;
                }
                if(arr[i] < arr[minIndex]){
                    minIndex = i;
                }
            }
            //走到这一步则代表找到了最大和最小位置
            //但是存在一种可能，即当交换完最小，可能会出现最大的位置改变，则需要提前记录
            swap(arr,left,minIndex);
            if(maxIndex == left){
                maxIndex = minIndex;
            }
            swap(arr,right - 1,maxIndex);
            left++;
            right--;
        }
    }

    //插入排序
    public static void insertSort(long[] arr){
        if(arr == null || arr.length == 0 || arr.length == 1){
            return;
        }
        //[有序区间][无序区间]
        //[0,i)[i,size)
        int size = arr.length;
        for(int i = 0;i < size;i++){
            long arrangedNum = arr[i];//arrangedNum为待排数字
            //插入排序随着一边找位置一边往后移
            int j;
            for(j = i - 1; j >= 0 && arr[j] > arrangedNum;j--){
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = arrangedNum;
        }
    }
    //对[fromIdx ,toIdx)区间进行插入排序
    public static void insertSortRange(long[] arr, int fromIdx, int toIdx) {
        //有序区间 无序区间
        //[fromIdx , i)[i , toIdx)

        int size = toIdx - fromIdx;
        if(size == 0 || size == 1){
            return;
        }
        for(int i = fromIdx;i < toIdx;i++){
            long arrangedNum = arr[i];
            int j;
            for(j = i - 1;j >= fromIdx && arr[j] > arrangedNum;j--){
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = arrangedNum;
        }
    }

    //堆排序
    public static void heapSort(long[] arr) {
        if(arr == null || arr.length == 0 || arr.length == 1){
            return;
        }
        //首先先对数组进行建堆操作
        //建立一个大堆
        //按照大堆的规范对数组进行调整
        int size = arr.length;
        for(int i = (size - 1) / 2 ;i >= 0;i--){
            shiftDown(arr,i,size);
        }
        //然后每一次都去取堆顶元素，将其放置到最后的有序区间内，并对堆顶元素进行向下调整
        //[无序区间][有序区间]
        //[0,i + 1)[i + 1,size)
        for(int i = size - 1;i >= 0;i--){
            swap(arr,0,i);
            shiftDown(arr,0,i - 1);
        }
    }
    public static void shiftDown(long[] arr,int index,int size){
        int leftIdx = index * 2 + 1;
        int rightIdx = index * 2 + 2;
        if(leftIdx >= size){//代表该位置为叶子结点
            return;
        }
        int maxIdx = leftIdx;
        if(rightIdx < size && arr[rightIdx] > arr[leftIdx]){
            maxIdx = rightIdx;
        }
        if(arr[maxIdx] > arr[index]){
            swap(arr,maxIdx,index);
            shiftDown(arr,maxIdx,size);
        }
    }

    //希尔排序
    //时间复杂度是 O(n^ 1.3)
    public static void shellSort(long[] arr) {
        int shellNumber = arr.length / 2;
        while(true){
            shellInsertSort(arr,shellNumber);
            if(shellNumber == 1){
                return;
            }
            shellNumber = shellNumber / 2;
        }
    }
    //对数组arr进行插入排序，根据分的数字每间隔一个进行排序
    //所以，对于插入排序的数组，前shellNumber是不需要排序的
    //只需要从下标为shellNumber的数开始排序，一直排序到最后一个即可
    private static void shellInsertSort(long[] arr,int shellNumber){
        for(int i = shellNumber;i < arr.length;i++){
            long temp = arr[i];
            int j;
            for(j = i - shellNumber;j >= 0 && arr[j] > temp;j = j - shellNumber){
                arr[j + shellNumber] = arr[j];
            }
            arr[j + shellNumber] = temp;
        }
    }

    //快速排序
    //对于最坏的情况，容易造成栈溢出的情况
    public static void quickSort(long[] arr){

    }

    public static void swap(long[] arr,int a ,int b){
        long temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        long[] arr = {2,4,7,2,5,73,2,6,8,2,5,7};
        double s = System.currentTimeMillis();
        shellSort(arr);
        double e = System.currentTimeMillis();
        System.out.println(Arrays.toString(arr));
        System.out.println((e - s) / 1000 + "s");
    }
}
