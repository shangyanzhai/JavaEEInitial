package JavaEE.Threads.ThreadCommunication.ThreadSafe.Lock.InterThreadCoordination;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;//Lock是一个接口
import java.util.concurrent.locks.ReentrantLock;//ReentrantLock是一个实现类
public class JUCLocksWaitDemo {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition c = lock.newCondition();
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        // 等待线程
        lock.lock();
        try{
            while(num > 100){//举个例子
                //必须把 wait 放在某个 while 循环中，判断外部条件是否已经满足
                c.await();
            }
        }finally {
            lock.unlock();
        }

        // 唤醒线程
        lock.lock();
        try {
            c.signal();
        } finally {
            lock.unlock();
        }
    }
}
