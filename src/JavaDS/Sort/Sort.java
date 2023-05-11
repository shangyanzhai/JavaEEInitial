package JavaDS.Sort;

import java.util.Deque;
import java.util.LinkedList;

public class Sort {
    public static void bubbleSort(long[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean sorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    sorted = false;
                    swap(array, j, j + 1);
                }
            }
            if (sorted) {
                return;
            }
        }
    }

    public static void selectSort(long[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int maxIdx = 0;
            for (int j = 1; j < array.length - i; j++) {
                if (array[j] > array[maxIdx]) {
                    maxIdx = j;
                }
            }
            swap(array, maxIdx, array.length - i - 1);
        }
    }

    public static void insertSort(long[] array) {
        for (int i = 1; i < array.length; i++) {
            long key = array[i];
            int j;
            for (j = i - 1; j >= 0 && key < array[j]; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = key;
        }
    }


    public static void heapSort(long[] array) {
        // 1. 由于要排升序，把整个无序区间（一开始整个 array 都是无序区间）都建成大堆
        createLargeHeap(array, array.length);   // O(n) ~ O(n * log(n))

        // 开始堆选择过程      // O(n * log(n))
        for (int i = 0; i < array.length - 1; i++) {    // n 次
            // 无序区间: [0, array.length - i)
            // 由于无序区间已经是大堆了，所以最大的元素在 [0] 下标
            // 把最大的元素和无序区间的最后一个元素 [array.length - i - 1] 交换
            swap(array, 0, array.length - i - 1);   // O(1)
            // 随着这次交换发生，无序区间少了一个元素，所以新的无序区间还剩 array.length - i - 1
            // 现在新的无序区间暂时不是大堆了，堆顶元素位置破坏了
            // 所以需要进行一次向下调整操作，调整的下标是 [0]
            shiftDown(array, array.length - i - 1, 0);  // O(log(n))
        }
    }

    private static void createLargeHeap(long[] array, int size) {
        int lastIdx = size - 1;
        int lastParentIdx = (lastIdx - 1) / 2;
        for (int i = lastParentIdx; i >= 0; i--) {
            shiftDown(array, size, i);
        }
    }

    // O(log(n))
    private static void shiftDown(long[] array, int size, int index) {
        while (true) {
            int leftIdx = 2 * index + 1;
            if (leftIdx >= size) {
                return;
            }

            int maxIdx = leftIdx;
            if (maxIdx + 1 < size && array[maxIdx + 1] > array[maxIdx]) {
                maxIdx++;
            }

            if (array[index] >= array[maxIdx]) {
                return;
            }

            swap(array, maxIdx, index);

            index = maxIdx;
        }
    }

    // 10 个元素
    // 第一次：5组
    // 第二次：2组
    // 第三次：1组
    // 结束排序
    public static void shellSort(long[] array) {
        int group = array.length / 2;
        while (true) {
            insertSortWithGroup(array, group);
            if (group == 1) {
                return;
            }
            group = group / 2;
        }
    }

    private static void insertSortWithGroup(long[] array, int group) {
        for (int i = group; i < array.length; i++) {
            // 1. 找到要插入的元素
            long key = array[i];
            int j;
            for (j = i - group; j >= 0 && key < array[j]; j = j - group) {
                array[j + group] = array[j];
            }
            array[j + group] = key;
        }
    }

    public static void quickSort(long[] array) {
        quickSortRange(array, 0, array.length - 1);
    }

    // 左闭右闭
    private static void insertSortRange(long[] array, int fromIdx, int toIdx) {
        int size = toIdx - fromIdx + 1;
        for (int i = 1; i < size; i++) {
            // [fromIdx, toIdx]
            // [fromIdx, fromIdx + i)
            long key = array[fromIdx + i];
            int j;
            for (j = fromIdx + i - 1; j >= fromIdx && key < array[j]; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = key;
        }
    }

    private static void quickSort非递归版本(long[] array) {
        Deque<Integer> stack = new LinkedList<>();
        // 先放区间的右边，再放区间的左边
        stack.push(array.length - 1);
        stack.push(0);

        while (!stack.isEmpty()) {  // 还有待排序区间未处理
            int fromIdx = stack.pop();
            int toIdx = stack.pop();
            int size = toIdx - fromIdx + 1;
            if (size <= 1) {
                continue;
            }

            int pivotIdx = partition1(array, fromIdx, toIdx);
            // [fromIdx, pivotIdx - 1]
            // [pivotIdx + 1, toIdx]
            // 为了让先处理左边，先放右边
            stack.push(toIdx);
            stack.push(pivotIdx + 1);
            stack.push(pivotIdx - 1);
            stack.push(fromIdx);
        }
    }

    // [fromIdx, toIdx]
    private static void quickSortRange(long[] array, int fromIdx, int toIdx) {
        // 求出本次处理的无序区间内的元素个数
        // 1. 当元素个数较少时，使用插入排序代替
        int size = toIdx - fromIdx + 1;
        if (size <= 16) {
            insertSortRange(array, fromIdx, toIdx);
            return;
        }

        // 增加一个 3 数取中法
        long e1 = array[fromIdx];
        long e2 = array[(fromIdx + toIdx) / 2];
        long e3 = array[toIdx];

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
        swap(array, pivotIdx, toIdx);

        // pivotIdx 是 partition 之后，pivot 所在的下标
//        int pivotIdx = partition3(array, fromIdx, toIdx);
        int[] borderIdx = partition4(array, fromIdx, toIdx);
        int ltIdx = borderIdx[0];
        int gtIdx = borderIdx[1];

        // 整个 [fromIdx, toIdx] 的区间被 pivot 分成两部分
        // 左边: [fromIdx, pivotIdx - 1]
        // 右边: [pivotIdx + 1, toIdx]
        quickSortRange(array, fromIdx, ltIdx);
        quickSortRange(array, gtIdx, toIdx);
    }

    // hover 法
    private static int partition1(long[] array, int fromIdx, int toIdx) {
        int leIdx = fromIdx;
        int geIdx = toIdx;
        long pivot = array[toIdx];
        // 小于等于基准值的区间: [fromIdx, leIdx)  区间 1
        // 大于等于基准值的区间：[geIdx, toIdx]    区间 2
        // 没有和基准值比较的区间: [leIdx, geIdx)  区间 3
        // 停止条件： 区间 3 的元素个数是 0 个
        while (geIdx > leIdx) {
            // 基准值在右侧，先动左边
            while (geIdx > leIdx && array[leIdx] <= pivot) {
                leIdx++;    // leIdx 在变化，变化过程中，geIdx > leIdx 条件可能会被破坏
            }

            // 左侧遇到一个大于基准值的元素了

            while (geIdx > leIdx && array[geIdx] >= pivot) {
                geIdx--;
            }

            // 右侧遇到一个小于基准值的元素了

            swap(array, leIdx, geIdx);
        }

        swap(array, leIdx, toIdx);

        return leIdx;
    }

    // 挖坑法
    private static int partition2(long[] array, int fromIdx, int toIdx) {
        int leIdx = fromIdx;
        int geIdx = toIdx;
        long pivot = array[toIdx];
        // 小于等于基准值的区间: [fromIdx, leIdx)  区间 1
        // 大于等于基准值的区间：[geIdx, toIdx]    区间 2
        // 没有和基准值比较的区间: [leIdx, geIdx)  区间 3
        // 停止条件： 区间 3 的元素个数是 0 个
        while (geIdx > leIdx) {
            // 基准值在右侧，先动左边
            while (geIdx > leIdx && array[leIdx] <= pivot) {
                leIdx++;    // leIdx 在变化，变化过程中，geIdx > leIdx 条件可能会被破坏
            }

            // 左侧遇到一个大于基准值的元素了
            array[geIdx] = array[leIdx];

            while (geIdx > leIdx && array[geIdx] >= pivot) {
                geIdx--;
            }

            // 右侧遇到一个小于基准值的元素了
            array[leIdx] = array[geIdx];
        }

        array[leIdx] = pivot;

        return leIdx;
    }

    private static int partition3(long[] array, int fromIdx, int toIdx) {
        int ltIdx = fromIdx;
        int geIdx = fromIdx;
        // 小于基准值: [fromIdx, ltIdx)
        // 大于等于基准值: [ltIdx, geIdx)
        // 未比较元素区间: [geIdx, toIdx)
        long pivot = array[toIdx];

        while (geIdx < toIdx) {
            if (array[geIdx] >= pivot) {
                geIdx++;
            } else {
                swap(array, ltIdx, geIdx);
                ltIdx++;
                geIdx++;
            }
        }

        swap(array, ltIdx, toIdx);
        return ltIdx;
    }

    private static int[] partition4(long[] array, int fromIdx, int toIdx) {
        int ltIdx = fromIdx;
        int eqIdx = fromIdx;
        int gtIdx = toIdx;
        long pivot = array[toIdx];

        // 小于: [fromIdx, ltIdx)
        // 等于: [ltIdx, eqIdx)
        // 未比较: [eqIdx, gtIdx]
        // 大于: (gtIdx, toIdx]
        while (eqIdx <= gtIdx) {
            if (array[eqIdx] == pivot) {
                eqIdx++;
            } else if (array[eqIdx] < pivot) {
                swap(array, ltIdx, eqIdx);
                ltIdx++;
                eqIdx++;
            } else {
                swap(array, eqIdx, gtIdx);
                gtIdx--;
            }
        }

        return new int[] { ltIdx - 1, gtIdx + 1 };
    }

    // 时间复杂度: O(n)
    // 空间复杂度: O(n)
    // [fromIdx, midIdx)   有序
    // [midIdx, toIdx)     有序
    private static void merge(long[] array, int fromIdx, int midIdx, int toIdx) {
        // 顺序表合并 有序区间 需要额外空间
        int size = toIdx - fromIdx;
        long[] extra = new long[size];  // 合并期间临时用到
        // 一共需要几个下标变量 3 个  左边区间、右边区间、额外区间
        // i 左边   j 右边   k 额外
        // 左边区间: [fromIdx, midIdx)
        // 右边区间: [midIdx, toIdx)
        // 额外区间: [0, size)
        int i = fromIdx;
        int j = midIdx;
        int k = 0;
        // 1. 当左右两个有序区间都还有元素时，分别找出最小的，比较，把小的尾插额外区间中
        // 左边区间还有元素： i < midIdx
        // 右边区间还有元素:  j < toIdx
        // 左边有 && 右边有
        while (i < midIdx && j < toIdx) {
            if (array[i] <= array[j]) {
                // 放左边
                extra[k] = array[i];
                i++;
                k++;
            } else {
                extra[k] = array[j];
                j++;
                k++;
            }
        }

        // 2. 把还有元素的区间的剩余的元素全部尾插到额外区间中
        while (i < midIdx) {
            extra[k] = array[i];
            i++;
            k++;
        }

        while (j < toIdx) {
            extra[k] = array[j];
            j++;
            k++;
        }

        // 3. 把额外区间中的元素按序搬回原来的位置
        // 额外区间: [0, size)
        // 原来区间: [fromIdx, toIdx)
        for (int n = 0; n < size; n++) {
            // 原来区间下标: n + fromIdx
            array[n + fromIdx] = extra[n];
        }
    }

    // 左闭右开
    private static void mergeSortRange(long[] array, int fromIdx, int toIdx) {
        int size = toIdx - fromIdx;
        if (size <= 1) {
            return;
        }

        int midIdx = (fromIdx + toIdx) / 2;
        // [from, mid)
        // 先排序左边
        mergeSortRange(array, fromIdx, midIdx);
        // [mid, to)
        // 再排序右边
        mergeSortRange(array, midIdx, toIdx);

        // 左右两个小区间分别有序
        merge(array, fromIdx, midIdx, toIdx);
    }

    public static void mergeSort(long[] array) {
        mergeSortRange(array, 0, array.length);
    }

    public static void mergeSort非递归(long[] array) {
        for (int i = 1; i < array.length; i = i * 2) {   // i: 本次归并过程中，单个区间的长度
            // 每组需要 2 * i 个元素
            for (int j = 0; j < array.length; j = j + 2 * i) {
                // 归并有序区间，要找到 fromIdx, midIdx, toIdx
                int fromIdx = j;
                int midIdx = fromIdx + i;
                int toIdx = midIdx + i;
                // 请问 能保证 fromIdx, midIdx, toIdx 一定不越界么？
                // fromIdx 一定不越界
                if (midIdx >= array.length) {
                    // 一定没有右边区间，所以不需要合并
                    continue;
                }
                if (toIdx >= array.length) {
                    toIdx = array.length;
                }

                merge(array, fromIdx, midIdx, toIdx);
            }
        }
    }

    private static void swap(long[] array, int i, int j) {
        long t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}