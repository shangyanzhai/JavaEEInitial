package JavaEEInitial.Threads.ThreadCommunication.ThreadSafe.Lock.InterThreadCoordination;

public class WaitReleaseLockDemo {
    static class SomeClass {}
    static class OtherClass {}

    public static void main(String[] args) throws InterruptedException {
        SomeClass sc = new SomeClass();
        OtherClass oc = new OtherClass();

        synchronized (oc) {
            synchronized (sc) {
                /**
                 * wait() 无限制等待（可以被 interrupt)
                 * wait(timeout) 在某段时间内等待（可以被 interrupt)
                 */
                /**
                 * o.wait() 什么情况下会醒来
                 * 1. 被其他线程通知醒来
                 *   1. o.notifyAll()，等待着 o 上的所有线程都会被唤醒
                 *   2. o.notify()，只会唤醒一个线程，唤醒策略是随机的，不保证哪个线程被唤醒
                 * 2. 超时时间到了之后，自动醒来
                 * 3. 因为系统内部原因，假唤醒。
                 */
                sc.wait();    // 只会释放 sc 锁
            }
        }
    }
}
