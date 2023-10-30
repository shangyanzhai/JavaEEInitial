package JavaEEInitial.Threads.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 JUC 下的 Lock / Condition 体系可以更优雅地实现 循环阻塞队列
 */
public class CircleBlockingQueueOfLocks {

    private final Lock lock;
    private final Condition fullCondition;
    private final Condition emptyCondition;

    private final Long[] array;
    private volatile int size;
    private int frontIdx;
    private int rearIdx;

    public CircleBlockingQueueOfLocks(int capacity) {
        this.array = new Long[capacity];
        this.size = 0;
        this.frontIdx = 0;
        this.rearIdx = 0;

        this.lock = new ReentrantLock(true);
        this.fullCondition = this.lock.newCondition();
        this.emptyCondition = this.lock.newCondition();
    }

    public int size() {
        lock.lock();
        try {
            return size;
        } finally {
            lock.unlock();
        }
    }

    public boolean isFull() {
        return size() == array.length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void put(Long e) throws InterruptedException {
        lock.lock();
        try {
            while (isFull()) {
                // 调用 put 的一定是生产者，等待在 fullCondition
                this.fullCondition.await();
            }

            array[rearIdx] = e;
            // 修改 rearIdx 指向下一个位置，注意循环
            rearIdx += 1;
            if (rearIdx == array.length) {
                rearIdx = 0;
            }
            // 修改元素个数
            size += 1;

            // 通知等待着 emptyCondition，一定都是消费者
            this.emptyCondition.signal();

        } finally {
            lock.unlock();
        }
    }

    public Long take() throws InterruptedException {
        lock.lock();
        try {
            while (isEmpty()) {
                this.emptyCondition.await();
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

            // 唤醒生产者
            this.fullCondition.signal();

            return e;
        } finally {
            lock.unlock();
        }
    }

    private static final CircleBlockingQueueOfLocks queue = new CircleBlockingQueueOfLocks(10);

    private static class Producer extends Thread {
        @Override
        public void run() {
            try {
                int i = 0;
                while (true) {
                    queue.put((long) i);
//                    System.out.printf("生产者放入了: %d\n", i);
                    i++;
                }
            } catch (InterruptedException ignored) {}
        }
    }

    private static class Customer extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    long e = queue.take();
                    System.out.printf("消费者获取了: %d\n", e);
                }
            } catch (InterruptedException ignored) {}
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Customer customer = new Customer();
            customer.setName("消费者");
            customer.start();
        }

        for (int i = 0; i < 100; i++) {
            Producer producer = new Producer();
            producer.setName("生产者");
            producer.start();
        }
    }
}
