package JavaSE.Array;

import java.util.Arrays;
import java.util.Scanner;

public class Array {
    /**
     * 斐波那契数列的前n项
     * @param
     */
    public static int[] fiboN(int n){
        if(n < 0){
            return null;//null - 空指针（空引用），引用数据类型的默认值，当引用数据类型是null的时候，说明该引用没有保存任何的堆内存地址
        }
        int[] arr = new int[n];
        arr[0] = arr[1] = 1;
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
    }

    /**
     * 模仿实现Arrays.toString的功能
     * @param n
     * @return arr
     */
    public static String toString(int[] n){
        String arr = "";
        arr += "[";
        for (int i = 0; i < n.length; i++) {
            arr += n[i];
            if(i < n.length - 1){
                arr += ", ";
            }
        }
        arr += "]";
        return arr;
    }

    /**
     * 模仿实现Arrays.copyof的功能
     */
    public static int[] copyof(int n[]){
        int[] arr = new int[n.length];
        for (int i = 0; i < n.length; i++) {
            arr[i] = n[i];
        }
        return arr;
    }
    /**
     * 如何证明数组为升序数组呢，也就是找反例，只要找到一个n[j] > n[j + 1]，则返回false 否则则返回true
     *
     */
    public static boolean isAscending(int n[]) {
        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n.length - i - 1; j++) {
                if (n[j] > n[j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
    /**前提是有序数组，降序数组，升序数组均可，下面所写方法是升序数组
     *查找数组中的元素，如果找到，则返回对应的数组下标，如果未能找到则返回-1
     * @param n,num
     */
    //第一种
    public static int find(int[] n,int num){
        //二分查找数字
        int left =  0;
        int right = n.length - 1;
        while(left <= right){
            int mid = left + ((right - left)>>1);//避免出现数字相加，超过了int类型的最大值//（left + right)>>1 会出现上述情况
            //升序
            if(n[left] <= n[right]){//代表是升序
                if(n[mid] < num){
                    left = mid + 1;
                }else if(n[mid] > num){
                    right = mid - 1;
                }else{
                    return mid;
                }
            }else{
                if(n[mid] < num){
                    right = mid + 1;
                }else if(n[mid] > num){
                    left = mid - 1;
                }else{
                    return mid;
                }
            }

        }
        return -1;
    }
    //第二种递归思想
    public static int find(int[] n,int left,int right,int num){
        if(left > right){
            return -1;
        }
        int mid = left + ((right - left)>>1);
        if(n[mid] == num){
            return mid;
        } else if (n[mid] > num) {
            return find(n,left,mid,num);
        }else {
            return find(n,mid,right,num);
        }
    }

    /**
     * 冒泡排序
     * @param
     */
//    public static void bubble(int[] n){
//        for (int i = 0; i < n.length; i++) {
//            for (int j = 0; j < n.length - 1 - i; j++) {
//                if(n[j] > n[j + 1]){
//                    int tmp = n[j];
//                    n[j] = n[j + 1];
//                    n[j + 1] = tmp;
//                }
//            }
//        }
//    }

    /**
     * 对冒泡排序进行优化，首先是对于无序空间只有一个元素的时候，则不需要进行判断
     * 其次，通过引入标志位来减少循环，如果本身数组作为一个升序数组而言，则不需要进行排序了，
     * 倘使没有发现交换，则默认代表该数组为一个升序数组
     * @param
     */
    public static void bubble(int[] n){
        for (int i = 0; i < n.length; i++) {
            boolean isSwap = false;//引入标志位
            for (int j = 0; j < n.length - 1 - i; j++) {
                if(n[j] > n[ j + 1 ]){
                    isSwap = true;
                    int tmp = n[j];
                    n[j] = n[j + 1];
                    n[j + 1] = tmp;
                }
            }
            //此时内存循环已经走完，在外层循环中，如果内存循环未能交换元素，则代表数组已经有序
            if(!isSwap){
                break;
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] num = fiboN(n);//该情况不为创建一个新的数组，他只是一个新的引用
        int b = num.length;
        /**
         * JDK中内置的将数组转化为字符串的方法
         */
        //JDK中内置的将数组转化为字符串的方法
        System.out.println(Arrays.toString(num));
        //自己实现的
        System.out.println(toString(num));
        /**
         * JDK中数组的拷贝
         */
        //第一种 全拷贝
        int[] arr1 = Arrays.copyOf(num, num.length);
        System.out.println(toString(arr1));
        //当所传的数组长度大于或小于原数组的值。
        int[] arr2 = Arrays.copyOf(num, num.length - 1);//小于 从第一个数开始截取到新数组的长度
        int[] arr3 = Arrays.copyOf(num, num.length + 1);//大于 超出的部分默认为默认值
        System.out.println(toString(arr2));
        System.out.println(toString(arr3));
        //自身实现
        int[] arr5 = copyof(num);
        System.out.println(toString(arr5));
        //
        //第二种 部分拷贝
        int[] arr4 = Arrays.copyOfRange(arr1, 1,6);//意思是从第二个元素开始到第六个元素，实际上是一个左闭右开的形式
        System.out.println(toString(arr4));

        //数组中寻找数据
        int c[] = new int[]{91,87,61,52,32,19,8,6,1};
//        int d = find(c, 52);
//        System.out.println(d);
        bubble(c);
        System.out.println(toString(c));
        boolean a = isAscending(c);
        System.out.println(a);
//        for (int i : num){
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        System.out.println(b);


        int[] arr = new int[3];
        Arrays.fill(arr,0);//调用 Arrays中 fill() 方法，进行快速初始化 ,但是该方法初始化仅限一维数组
        for(int i : arr){
            System.out.print(i);
        }
    }
}
