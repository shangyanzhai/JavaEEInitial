package JavaDS.Complexity;

import java.util.Arrays;

/**
 * 时间复杂度 与 空间复杂度
 */
public class SortingTimeComplexity {
    //冒泡排序
    public static void bubble(int[] arr){
        //判断边界条件
        if(arr == null || arr.length == 0){
            System.err.println("所给数组无法排序");
        }
        //引入一个标志位，如果出现类似已经是有序的情况则直接结束
        boolean temp = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i -1; j++) {
                if(arr[j] > arr[j + 1]){
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                    temp = true;
                }
            }
            if(temp == false){
                break;
            }
        }
    }

    //二分查找 -- 前提该数组为一个有序数组(此处默认为升序数组)，否则其时间复杂度和暴力查找没有任何区别
    public static int twopoints(int[] arr,int value){
        int left = 0;
        int right = arr.length - 1;
        while(left < right){
            int median = left + (right - left) / 2;
            if(arr[median] == value){
                return median;
            } else if (arr[median] < value) {
                left = median;
            }else{
                right = median;
            }
        }
//        //到这说明没找到，则报错
//        System.err.println("没有找到对应的数值");
        return -1;
    }
    public static void main(String[] args) {
//        int[] a = {9,8,7,6,5,4,3,2,1};
        int[] b = {1,2,3,4,5,6,7,8,9};
//        int[] c = null;
//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.toString(b));
//        bubble(a);
//        bubble(b);
//        bubble(c);
//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.toString(b));
        long begin = System.currentTimeMillis();
        System.out.println(twopoints(b, 3));
        long end = System.currentTimeMillis();
        System.out.println(end - begin);//以ms为单位
        begin = System.currentTimeMillis();
        System.out.println(twopoints(b, -1));
        end = System.currentTimeMillis();
        System.out.println(end - begin);//以ms为单位
    }
}
