package JavaEE.Threads.ThreadCommunication.ThreadSafe.Synchronized;

/**
 * 锁的使用
 *  主要是保证原子性，把需要保证原子性的动作放在加锁-解锁之间。这段代码被称为临界区。
 */
public class SynchronizedUse {
    //1.a++;   Check-update;
    public static void main1(String[] args) {
        int a = 0;
        synchronized (SynchronizedUse.class) {
            a++;
        }

        int data = 0;
        synchronized (SynchronizedUse.class) {
            if (data == 0) {
                data = 1;
            }
        }
    }

    //2.把刚才线程不安全的代码改造成线程安全的
    //实现方式还有多种，确保所有对 a 的读写操作都封闭在临界区中，申请的是同一把锁（即同一个对象）即可。
    public static class ThreadSafe {
        private static final int N = 100_0000;
        static int a = 0;
        static Object lock = new Object();

        static class Add extends Thread {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < N; i++) {
                        a++;
                    }
                }
            }
        }

        static class Sub extends Thread {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < N; i++) {
                        a--;
                    }
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            Add t1 = new Add();
            t1.start();

            Sub t2 = new Sub();
            t2.start();

            t1.join();
            t2.join();

            System.out.println(a);
        }
    }
}
