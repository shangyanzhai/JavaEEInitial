package JavaEEInitial.Threads.BlockingQueue;

/**
 * FIFO + 有容量上限（循环阻塞队列）
 */

/**
 * //该程序在多生产者-多消费者场景下有 BUG，尤其双方数量差别较大时。
 * 解决方案是，既然因为notify()是随机唤醒
 *  * 又无法保证生产者一定唤醒的是消费者
 *  * 消费者一定唤醒的是生产者
 *  * 那就把阻塞队列的所有都唤醒
 *  * 满足条件的执行，不满足条件的继续等待
 *  使用 notifyAll() 代替，
 *  把所有线程都唤醒，被唤醒的线程由于有 while 条件判断，不满足条件继续 wait 即可，这样，只有满足条件的线程才会执行后续逻辑。
 */
/*
public class CircleBlockingQueue {
    private final Long[] array;
    private int size;   // 目前队列中的元素个数
    private int frontIdx;   // 队首下标位置
    private int rearIdx;    // 队尾下标位置 —— 放入元素的位置

    // 构造方法必然只会在一个线程中执行，不需要考虑保护原子性
    private CircleBlockingQueue(int capacity) {
        array = new Long[capacity];
        size = 0;
        frontIdx = 0;
        rearIdx = 0;
    }

    // 阻塞版本
    public void put(Long e) throws InterruptedException {
        synchronized (this) {   // this.wait() 已经对 this 加锁
            while (isFull()) {  // this.wait() 可能中途假醒来，条件还不满足时醒来
                // 我们现在是阻塞版本的
                // 我们不直接返回
                // 所以要让我们等待在某个条件上 (!isFull())
                // 调用某个对象的 wait() 等待被别人唤醒
                // 已经对哪个对象加锁了，就等待着哪个对象上
                this.wait();
            }

            // 队列中一定不是满的
            array[rearIdx] = e;
            // 修改 rearIdx 指向下一个位置，注意循环
            rearIdx += 1;
            if (rearIdx == array.length) {
                rearIdx = 0;
            }
            // 修改元素个数
            size += 1;

            // 唤醒可能等待着的消费者
            this.notify();
        }
    }

    public Long take() throws InterruptedException {
        synchronized (this) {
            while (isEmpty()) {
                this.wait();
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

            // 因为取走一个元素了，现在队列必然不是 Full 的
            // 所以应该尝试唤醒等待着队列上的一个生产者
            this.notify();

            return e;
        }
    }

    public boolean offer(Long e) {
        synchronized (this) {
            if (isFull()) {
                return false;
            }

            // 依赖一个前提，队列不是满的
            array[rearIdx] = e;
            // 修改 rearIdx 指向下一个位置，注意循环
            rearIdx += 1;
            if (rearIdx == array.length) {
                rearIdx = 0;
            }
            // 修改元素个数
            size += 1;

            // 唤醒可能等待着的消费者
            this.notify();

            return true;
        }
    }

    public Long poll() {
        synchronized (this) {
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

            // 因为取走一个元素了，现在队列必然不是 Full 的
            // 所以应该尝试唤醒等待着队列上的一个生产者
            this.notify();

            return e;
        }
    }

    public Long peek() {
        synchronized (this) {
            if (isEmpty()) {
                return null;
            }

            return array[frontIdx];
        }
    }

    public synchronized boolean isEmpty() {
        return size() == 0;
    }

    public synchronized boolean isFull() {
        return size() == array.length;
    }

    public synchronized int size() {
        return size;
    }


    private static final CircleBlockingQueue queue = new CircleBlockingQueue(10);

    private static class Producer extends Thread {
        @Override
        public void run() {
            try {
                int i = 0;
                while (i < 20) {
                    queue.put((long) i);
                    System.out.printf("生产者放入了: %d\n", i);
                    i++;
                    TimeUnit.MILLISECONDS.sleep(500);
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
        Customer customer = new Customer();
        customer.start();

        TimeUnit.SECONDS.sleep(3);

        Producer producer = new Producer();
        producer.start();
    }
}
 */

/**
 * 解决方案是，既然因为notify()是随机唤醒
 * 又无法保证生产者一定唤醒的是消费者
 * 消费者一定唤醒的是生产者
 * 那就把阻塞队列的所有都唤醒
 * 满足条件的执行，不满足条件的继续等待
 */

public class CircleBlockingQueue {
    private final Long[] array;
    private int size;   // 目前队列中的元素个数
    private int frontIdx;   // 队首下标位置
    private int rearIdx;    // 队尾下标位置 —— 放入元素的位置

    // 构造方法必然只会在一个线程中执行，不需要考虑保护原子性
    private CircleBlockingQueue(int capacity) {
        array = new Long[capacity];
        size = 0;
        frontIdx = 0;
        rearIdx = 0;
    }

    // 阻塞版本
    public void put(Long e) throws InterruptedException {
        synchronized (this) {   // this.wait() 已经对 this 加锁
            while (isFull()) {  // this.wait() 可能中途假醒来，条件还不满足时醒来
                // 我们现在是阻塞版本的
                // 我们不直接返回
                // 所以要让我们等待在某个条件上 (!isFull())
                // 调用某个对象的 wait() 等待被别人唤醒
                // 已经对哪个对象加锁了，就等待着哪个对象上
                this.wait();
            }

            // 队列中一定不是满的
            array[rearIdx] = e;
            // 修改 rearIdx 指向下一个位置，注意循环
            rearIdx += 1;
            if (rearIdx == array.length) {
                rearIdx = 0;
            }
            // 修改元素个数
            size += 1;

            // 唤醒可能等待着的消费者
            this.notifyAll();//使用 notifyAll() 代替，
            // 把所有线程都唤醒，被唤醒的线程由于有 while 条件判断，不满足条件继续 wait 即可，这样，只有满足条件的线程才会执行后续逻辑。
        }
    }

    public Long take() throws InterruptedException {
        synchronized (this) {
            while (isEmpty()) {
                this.wait();
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

            // 因为取走一个元素了，现在队列必然不是 Full 的
            // 所以应该尝试唤醒等待着队列上的一个生产者
            this.notifyAll();//使用 notifyAll() 代替，
            // 把所有线程都唤醒，被唤醒的线程由于有 while 条件判断，不满足条件继续 wait 即可，这样，只有满足条件的线程才会执行后续逻辑。

            return e;
        }
    }

    public boolean offer(Long e) {
        synchronized (this) {
            if (isFull()) {
                return false;
            }

            // 依赖一个前提，队列不是满的
            array[rearIdx] = e;
            // 修改 rearIdx 指向下一个位置，注意循环
            rearIdx += 1;
            if (rearIdx == array.length) {
                rearIdx = 0;
            }
            // 修改元素个数
            size += 1;

            // 唤醒可能等待着的消费者
            this.notifyAll();//使用 notifyAll() 代替，
            // 把所有线程都唤醒，被唤醒的线程由于有 while 条件判断，不满足条件继续 wait 即可，这样，只有满足条件的线程才会执行后续逻辑。

            return true;
        }
    }

    public Long poll() {
        synchronized (this) {
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

            // 因为取走一个元素了，现在队列必然不是 Full 的
            // 所以应该尝试唤醒等待着队列上的一个生产者
            this.notifyAll();//使用 notifyAll() 代替，
            // 把所有线程都唤醒，被唤醒的线程由于有 while 条件判断，不满足条件继续 wait 即可，这样，只有满足条件的线程才会执行后续逻辑。

            return e;
        }
    }

    public Long peek() {
        synchronized (this) {
            if (isEmpty()) {
                return null;
            }

            return array[frontIdx];
        }
    }

    public synchronized boolean isEmpty() {
        return size() == 0;
    }

    public synchronized boolean isFull() {
        return size() == array.length;
    }

    public synchronized int size() {
        return size;
    }

    private static final CircleBlockingQueue queue = new CircleBlockingQueue(10);

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