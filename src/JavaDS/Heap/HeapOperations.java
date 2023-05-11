package JavaDS.Heap;

/** 堆
 * 解决：动态地、在一组元素中找最值的场景。
 * 最大堆（用来找最大值）/最小堆（用来找最小值）
 * 定义（最大堆的定义）：
 * 1. 逻辑上是一棵完全二叉树 。
 * 2. 物理上是一个数组。
 * 3. 树中的任意一个结点，只要有孩子，都保证 parent 的值 >= 左右还两个孩子的值。
 */
public class HeapOperations {
    // O(log(n))
    // 需要的参数：
    // 1. 堆（数组）     long[] array, int size
    // 2. 要调整的位置   int index
    // 向下转型
    public static void shiftDown(long[] array,int size,int index) {
        /**
         * 因为是向下转型，所以，需要对index位置的左右孩子进行判断
         * 因为 需要调整的位置是index
         * 这是一个大堆
         * 所以 其左右孩子的下标为 index * 2 + 1   index * 2 + 2
         */
        while (true) {
            // 1. 判断这个位置是否已经满足堆的性质
            // 1.1 只要该位置已经是叶子了，就不需要做处理
            //     逻辑：叶子 <-> 一个孩子都没有 <-> 没有左孩子
            //     物理上是数组：左孩子的下标越界 <-> 没有左孩子
            int leftIndex = index * 2 + 1;
            if (leftIndex >= size) {
                //代表该结点是叶子结点
                return;
            }
            //如果最大的是左孩子
            //不成立的情况 ：存在右孩子，且右孩子大于左孩子
            //此时至少存在左孩子
            // 左右 孩子进行比较，选择其中大的
            int maxIndex = leftIndex;
            if (leftIndex + 1 < size && array[leftIndex + 1] > array[leftIndex]) {
                maxIndex = leftIndex + 1;
            }
            if (array[index] >= array[maxIndex]) {
                //说明满足堆的性质
                return;
            }

            long t = array[index];
            array[index] = array[maxIndex];
            array[maxIndex] = t;

            // 交换之后不算完
            index = maxIndex;
        }
    }
    // O(log(n))
    //向上转型
    public static void shiftUp(long[] array, int index) {
        while (true) {
            if (index == 0) {
                return;
            }

            int parentIdx = (index - 1) / 2;
            if (array[parentIdx] >= array[index]) {
                return;
            }

            long t = array[index];
            array[index] = array[parentIdx];
            array[parentIdx] = t;

            index = parentIdx;
        }
    }

    // O(n * log(n))
    // O(n)
    //建堆
    public static void createHeap(long[] array, int size) {
        for (int i = (size - 2) / 2; i >= 0; i--) {
            shiftDown(array, size, i);
        }
    }

    public static void main(String[] args) {
//        long[] array = { 5, 3, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0 };
//        int index = 1;
//        shiftDown(array, 9, index);
//        System.out.println(Arrays.toString(array));

//        long[] array = {3, 9, 7, 6, 8, 8, 2, 4, 1, 7, 100, 100, 100};
//        int size = 10;
//
//        createHeap(array, size);
//
//        System.out.println(Arrays.toString(array));
    }
}
