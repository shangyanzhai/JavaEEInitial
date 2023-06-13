package JavaEE.Threads.ThreadCommunication.ThreadSafe.Lock.SystemLock;

/**
 * 因为 synchronized 设计的比较早，相对而言并没有太灵活
 * 所以在此之外设计了lock体系锁，
 * 而他也在java.util.concurrent.locks.*
 */
import JavaEE.Threads.ThreadCommunication.ThreadSafe.Lock.Synchronized.SynchronizedUse;

import java.util.concurrent.locks.Lock;//Lock是一个接口
import java.util.concurrent.locks.ReentrantLock;//ReentrantLock是一个实现类
public class SystemLock {
    /**
        lock():void//加锁
        lockInterruptibly():void//允许被中断的加锁,如果让被加锁的中断，是可以被中断的，他可以接收的到异常
        tryLock():boolean//尝试加锁，加锁成功，返回true，加锁失败，返回false
        tryLock(long,TimeUnit):boolean//尝试加锁，给一个最长时间，如果到了这个时间还没有，就先干别的任务
        unlock():void//释放锁
     */


    //使用lock体系锁来上锁
    //使用try{}但是finally必须要写，如果不写，当线程挂掉的时候，就没有人释放锁了
    public static class ThreadSafe {
        private static final int N = 100_0000;
        static int a = 0;
//        static Object lock = new Object();
        static Lock lock = new ReentrantLock(false);

        static class Add extends Thread {
            @Override
            public void run() {
                lock.lock();
                try{
                    for (int i = 0; i < N; i++) {
                        a++;
                    }
                }finally {//无论如何都要写finally，如果不写，当线程挂掉的时候，也就没有人释放锁了
                    lock.unlock();
                }
            }
        }

        static class Sub extends Thread {
            @Override
            public void run() {
                lock.lock();
                try{
                    for (int i = 0; i < N; i++) {
                        a--;
                    }
                }finally {
                    lock.unlock();
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            ThreadSafe.Add t1 = new ThreadSafe.Add();
            t1.start();

            ThreadSafe.Sub t2 = new ThreadSafe.Sub();
            t2.start();

            t1.join();
            t2.join();

            System.out.println(a);
        }
    }
}
