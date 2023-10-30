package JavaEEInitial.Threads.ThreadCommunication.ThreadSafe.Lock.锁的策略;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UseRWLock {

    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();

        writeLock.lock();    // 主线程先加读锁

        new Thread(() -> {
            writeLock.lock();
            System.out.println("子线程加锁成功");
        }).start();
    }
}