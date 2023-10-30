package JavaEEInitial.Threads.ThreadCommunication.ThreadSafe.Lock.锁的策略;

import java.util.concurrent.atomic.*;

/**
 * 原子对象
 *      java.util.concurrent.atomic.*;
 */
public class UseAtomic {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(0);

        //int a = ai;
        int a = ai.get();

        //ai = 3;
        ai.set(3);

        //int b = ai;
        //ai = 17;
        int b = ai.getAndSet(17);

        //ai++;
        int c = ai.getAndIncrement();
        //++ai;
        int d = ai.incrementAndGet();

    }
}
