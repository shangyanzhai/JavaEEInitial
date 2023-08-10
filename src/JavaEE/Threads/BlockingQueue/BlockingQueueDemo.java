package JavaEE.Threads.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
//生产者达到上限，阻塞
/*
public class BlockingQueueDemo {
    // 单生产者-单消费者模式
    // 阻塞队列在多个线程之间共享
    private static final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

    private static class Producer extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                try {
                    blockingQueue.put(i);
                    System.out.println("生产者放入: " + i);
                    i++;
                } catch (InterruptedException ignored) { }
            }
        }
    }

    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.start();
    }
}
*/

//生产者放入结束，消费者消费完队列中的元素，阻塞
public class BlockingQueueDemo {
    // 单生产者-单消费者模式
    // 阻塞队列在多个线程之间共享
    private static final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

    private static class Producer extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (i < 20) {
                try {
                    blockingQueue.put(i);
                    System.out.println("生产者放入: " + i);
                    i++;
                } catch (InterruptedException ignored) { }
            }
        }
    }

    private static class Customer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Integer e = blockingQueue.take();
                    System.out.println("消费了: " + e);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.start();

        Producer producer = new Producer();
        producer.start();
    }
}
