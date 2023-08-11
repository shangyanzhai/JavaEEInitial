package JavaDS.Queue;

/**
 * 循环队列
 */
public class CircleQueue {
    private final Long[] array;
    private int size;   // 目前队列中的元素个数
    private int frontIdx;   // 队首下标位置
    private int rearIdx;    // 队尾下标位置 —— 放入元素的位置

    private CircleQueue(int capacity) {
        array = new Long[capacity];
        size = 0;
        frontIdx = 0;
        rearIdx = 0;
    }

    public boolean offer(Long e) {
        if (isFull()) {
            return false;
        }

        array[rearIdx] = e;
        // 修改 rearIdx 指向下一个位置，注意循环
        rearIdx += 1;
        if (rearIdx == array.length) {
            rearIdx = 0;
        }
        // 修改元素个数
        size += 1;

        return true;
    }

    public Long poll() {
        if (isEmpty()) {
            return null;
        }

        Long e = array[frontIdx];
        array[frontIdx] = null;
        // 让 frontIdx 指向下一个位置，循环
        frontIdx += 1;
        if (frontIdx == array.length) {
            frontIdx = 0;
        }
        // 处理元素个数
        size -= 1;

        return e;
    }

    public Long peek() {
        if (isEmpty()) {
            return null;
        }

        return array[frontIdx];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFull() {
        return size() == array.length;
    }

    public int size() {
        return size;
    }
}
