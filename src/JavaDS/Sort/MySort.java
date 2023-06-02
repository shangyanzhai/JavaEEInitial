package JavaDS.Sort;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

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
        // 1. 由于要排升序，把整个无序区间（一开始整个 array 都是无序区间）都建成大堆
        createLargeHeap(arr, arr.length);   // O(n) ~ O(n * log(n))

        // 开始堆选择过程      // O(n * log(n))
        for (int i = 0; i < arr.length - 1; i++) {    // n 次
            // 无序区间: [0, array.length - i)
            // 由于无序区间已经是大堆了，所以最大的元素在 [0] 下标
            // 把最大的元素和无序区间的最后一个元素 [array.length - i - 1] 交换
            swap(arr, 0, arr.length - i - 1);   // O(1)
            // 随着这次交换发生，无序区间少了一个元素，所以新的无序区间还剩 array.length - i - 1
            // 现在新的无序区间暂时不是大堆了，堆顶元素位置破坏了
            // 所以需要进行一次向下调整操作，调整的下标是 [0]
            shiftDown(arr, arr.length - i - 1, 0);  // O(log(n))
        }
    }

    private static void createLargeHeap(long[] arr, int size) {
        int lastIdx = size - 1;
        int lastParentIdx = (lastIdx - 1) / 2;
        for (int i = lastParentIdx; i >= 0; i--) {
            shiftDown(arr, size, i);
        }
    }

    // O(log(n))
    private static void shiftDown(long[] arr, int size, int index) {
        while (true) {
            int leftIdx = 2 * index + 1;
            if (leftIdx >= size) {
                return;
            }

            int maxIdx = leftIdx;
            if (maxIdx + 1 < size && arr[maxIdx + 1] > arr[maxIdx]) {
                maxIdx++;
            }

            if (arr[index] >= arr[maxIdx]) {
                return;
            }

            swap(arr, maxIdx, index);

            index = maxIdx;
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
    //核心思想：
    //1. 在无序区间内确定一个基准值（pivot），哪个元素都可以。咱选择无序区间最右边这个元素
    //{ 5, 3, 1, 8, 2, 4, 9, 0, 7, 6 }
    //2. 遍历整个无序区间，把每个元素都和基准值做比较，并且做一定的元素移动
    //保证，基准值左边的元素都小于等于基准值，右边的元素都大于等于基准值
    //这个过程被称为 partition
    //{ 5, 3, 1, 0, 2, 4, 6, 9, 8, 7 }
    //3. 对左右两个小区间按照相同的思路再次处理，直到无序区间的元素个数收敛成 0 个或者 1 个。
    /**
     快速排序的一些优化/变形：
     1. 选择 pivot 的方式做优化
     当直接选择最右侧作为 pivot 时，数组有序将变成快排的最坏情况
     A. 同时选择两个 pivot1  pivot2
     [ < pivot1 ] [ == pivot1 ] [ > pivot && < pivot2 ] [ == pivot2 ] [ > pivot2]
     B. 随机选择一个元素做为 pivot，计算机中生产随机数也是一个高成本的事情
     C. 当元素个数 > 3 个时，选择 3 个元素出来，最左边、最中间、最右边的 3 个元素，记为 e1, e2, e3。
     比较 3 个元素的大小，把大小关系是中间的元素作为 pivot。—— 三数取中法。
     2. 当元素个数较小时（16 个），直接用插排代替
     3. 非递归（了解即可）
     */
    //对[fromIdx ,toIdx]区间进行插入排序
    public static void insertSortRange1(long[] arr, int fromIdx, int toIdx) {

        int size = toIdx - fromIdx + 1;
        if(size == 0 || size == 1){
            return;
        }
        for (int i = 1; i < size; i++) {
            // [fromIdx, toIdx]
            // [fromIdx, fromIdx + i)
            long key = arr[fromIdx + i];
            int j;
            for (j = fromIdx + i - 1; j >= fromIdx && key < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = key;
        }
    }
    public static void quickSort(long[] arr){
        quickSortRange(arr,0,arr.length - 1);
    }
    private static void quickSortRange(long[] arr,int fromIdx ,int toIdx){
        if(arr == null || arr.length == 0 || arr.length == 1){
            return;
        }
        //优化 ： 1.当元素个数较少的时候，使用插入排序代替
        int size = toIdx - fromIdx + 1;

        if (size <= 16) {
            insertSortRange1(arr, fromIdx, toIdx);
            return;
        }

        // 增加一个 3 数取中法
        long e1 = arr[fromIdx];
        long e2 = arr[(fromIdx + toIdx) / 2];
        long e3 = arr[toIdx];

        int pivotIdx;
        if (e1 < e2) {
            if (e2 < e3) {
                pivotIdx = (fromIdx + toIdx) / 2;
            } else if (e1 < e3) {
                pivotIdx = toIdx;
            } else {
                pivotIdx = fromIdx;
            }
        } else {
            // e2 <= e1
            if (e1 < e3) {
                pivotIdx = fromIdx;
            } else if (e3 < e2) { // e3 <= e1
                pivotIdx = (fromIdx + toIdx) / 2;
            } else {
                pivotIdx = toIdx;
            }
        }

        // 把 pivot 交换到最后边
        swap(arr, pivotIdx, toIdx);

//        int index = partition3(arr,fromIdx,toIdx);
//        quickSortRange(arr,fromIdx ,index - 1);
//        quickSortRange(arr,index + 1,toIdx);
//        pivotIdx 是 partition 之后，pivot 所在的下标
//        int pivotIdx = partition3(array, fromIdx, toIdx);
        int[] borderIdx = partition(arr, fromIdx, toIdx);
        int leIdx = borderIdx[0];
        int geIdx = borderIdx[1];

        // 整个 [fromIdx, toIdx] 的区间被 pivot 分成两部分
        // 左边: [fromIdx, pivotIdx - 1]
        // 右边: [pivotIdx + 1, toIdx]
        quickSortRange(arr, fromIdx, leIdx);
        quickSortRange(arr, geIdx, toIdx);
    }

    //快速排序的非递归写法

    /**
     * 非递归快速排序实际不一定必须使用栈，只是需要一个容器来记录区间的边界条件，
     * 比如使用队列也是可行的
     */
    public static void quickSort非递归版本(long[] arr) {
        if(arr == null || arr.length == 0 || arr.length == 1){
            return;
        }
        //优化 ： 1.当元素个数较少的时候，使用插入排序代替
        int size = arr.length;

        if (size <= 16) {
            insertSortRange1(arr, 0, size - 1);
            return;
        }

        // 增加一个 3 数取中法
        long e1 = arr[0];
        long e2 = arr[(0 + size - 1) / 2];
        long e3 = arr[size - 1];

        int pivotIdx;
        if (e1 < e2) {
            if (e2 < e3) {
                pivotIdx = (0 + size - 1) / 2;
            } else if (e1 < e3) {
                pivotIdx = size - 1;
            } else {
                pivotIdx = 0;
            }
        } else {
            // e2 <= e1
            if (e1 < e3) {
                pivotIdx = 0;
            } else if (e3 < e2) { // e3 <= e1
                pivotIdx = (0 + size - 1) / 2;
            } else {
                pivotIdx = size - 1;
            }
        }

        // 把 pivot 交换到最后边
        swap(arr, pivotIdx, size - 1);

        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        stack.push(size - 1);
        while(!stack.isEmpty()){
            int geIdx = stack.pop();
            int leIdx = stack.pop();
            if(leIdx >= geIdx){
                continue;
            }
            int[] indexArr = partition(arr,leIdx,geIdx);
            stack.push(leIdx);
            stack.push(indexArr[0]);
            stack.push(indexArr[1]);
            stack.push(geIdx);
        }

    }

    /**
     * 1. Horve 法
     * 以数组最右边的那个为pivot（基准值），所以基准值在最右边的话，应该先动左边。否则 { ... 1 2 3 4 5 ... } 这种情况处理是错误的。
     * @param arr
     * @param fromIdx
     * @param toIdx
     * @return
     */
    private static int partition1(long[] arr,int fromIdx,int toIdx){
        //此时将右边的值为pivot
        long pivot = arr[toIdx];
        int leIdx = fromIdx;
        int geIdx = toIdx;

        while(geIdx > leIdx){
            //因为基准值是最右边的，所以此时我应该先动左边
            while(geIdx > leIdx && arr[leIdx] <= pivot){
                leIdx++;
            }

            while(geIdx > leIdx && arr[geIdx] >= pivot){
                geIdx--;
            }

            swap(arr,leIdx,geIdx);
        }

        swap(arr,leIdx,toIdx);
        return leIdx;
    }

    /**
     * 2. 挖坑法
     * 以数组最右边的那个为pivot（基准值），所以基准值在最右边的话，应该先动左边。否则 { ... 1 2 3 4 5 ... } 这种情况处理是错误的。
     * @param arr
     * @param fromIdx
     * @param toIdx
     * @return
     */
    private static int partition2(long[] arr,int fromIdx,int toIdx){
        //此时将右边的值为pivot
        long pivot = arr[toIdx];
        int leIdx = fromIdx;
        int geIdx = toIdx;

        while(geIdx > leIdx){
            //因为基准值是最右边的，所以此时我应该先动左边
            while(geIdx > leIdx && arr[leIdx] <= pivot){
                leIdx++;
            }

//            swap(arr,leIdx,geIdx);
            arr[geIdx] = arr[leIdx];

            while(geIdx > leIdx && arr[geIdx] >= pivot){
                geIdx--;
            }

//            swap(arr,leIdx,geIdx);
            arr[leIdx] = arr[geIdx];
        }

        arr[leIdx] = pivot;

        return leIdx;
    }

    /**
     * 3.前后指针法
     * 以数组最右边的那个为pivot（基准值），所以基准值在最右边的话，应该先动左边。否则 { ... 1 2 3 4 5 ... } 这种情况处理是错误的。
     * 与前两种方法不同的是，这一次的geIdx 和 leIdx都是在最左边
     * 当geIdx 大于等于 基准值，那么只有geIdx往右移
     * 当如果遇到 geIdx 小于 基准值，则 先交换geIdx 和 leIdx ，然后geIdx 和 leIdx均往右移
     * @param arr
     * @param fromIdx
     * @param toIdx
     * @return
     */
    private static int partition3(long[] arr,int fromIdx,int toIdx){
        int leIdx = fromIdx;
        int geIdx = fromIdx;
        long pivot = arr[toIdx];

        while(geIdx < toIdx){
            if(arr[geIdx] >= pivot){
                geIdx++;
            }else{
                swap(arr,geIdx,leIdx);
                leIdx++;
                geIdx++;
            }
        }
        swap(arr,leIdx,geIdx);
        return leIdx;
    }

    /**
     * 4. 做完 partition 之后，整个区间被分成 [ 小于 pivot ] [ == pivot ] [ > pivot ]
     * 实际上是吧前面的几种进行一个结合
     * 在其一开始就将其视为四个部分
     * 所以带上基准值就是五个部分 [ 小于 pivot ] [ == pivot ] [ 未比较的区间 ] [ > pivot ] [ 基准值 ]
     * 所以 小于 基准值的 和 等于 基准值的他们都是向右收敛
     * 而 大于 基准值的，则是向左收敛
     * @param arr
     * @param fromIdx
     * @param toIdx
     * @return
     */
    private static int[] partition(long[] arr,int fromIdx,int toIdx){
        int leIdx = fromIdx;
        int eqIdx = fromIdx;
        int geIdx = toIdx;
        long pivot = arr[toIdx];

        // 小于: [fromIdx, leIdx)
        // 等于: [leIdx, eqIdx)
        // 未比较: [eqIdx, geIdx]
        // 大于: (geIdx, toIdx]
        while (eqIdx <= geIdx) {
            if (arr[eqIdx] == pivot) {
                eqIdx++;
            } else if (arr[eqIdx] < pivot) {
                swap(arr, leIdx, eqIdx);
                leIdx++;
                eqIdx++;
            } else {
                swap(arr, eqIdx, geIdx);
                geIdx--;
            }
        }

        return new int[] { leIdx - 1, geIdx + 1 };
    }

    //归并排序
    public static void mergeSort(long[] arr){

    }

    private static void merge(){

    }
    public static void swap(long[] arr,int a ,int b){
        long temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        long[] arr = {2,4,7,2,5,73,2,6,8,2,5,7};
//        long[] arr = {9,8,7,6,5,4,3,2,1};
        double s = System.currentTimeMillis();
        quickSort非递归版本(arr);
        double e = System.currentTimeMillis();
        System.out.println(Arrays.toString(arr));
        System.out.println((e - s) / 1000 + "s");
    }
}