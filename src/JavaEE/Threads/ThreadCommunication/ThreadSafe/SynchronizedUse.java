package JavaEE.Threads.ThreadCommunication.ThreadSafe;

/**
 * 锁（synchronized 关键字）—— monitor lock
 * 通过 锁 让线程和线程之间呈现互斥（exclusive）关系。
 */
public class SynchronizedUse {
    /**
     * 使用方法 2 种：
     */
    //1. 用来修饰方法（静态方法 / 普通方法）
    public synchronized void method() {  }

    public synchronized static void staticMethod() {  }


    //2. 组成同步代码块
    public void method1() {
        Object o = new Object();
        synchronized (o) {    // o 为 对象的引用，引用不能为 null
        }
    }

    /**
     * synchronized作用：
     * 1. JVM 把 Java 中所有对象都可以看作锁，或者每个对象中都内置了一个 monitor lock。
     * https://bitejiuyeke.feishu.cn/docx/BsA3duT39obESrxbXjacvf46nYg
     * 2. Synchronized 代码块的操作，加锁/解锁操作
     */
    //2. Synchronized 代码块的操作，加锁/解锁操作
    public void Method() {
        Object o = new Object();
        synchronized (o) {    // o 为 对象的引用，引用不能为 null ，尝试加 o 指向的对象锁
            // 只有加锁成功之后，才能执行此处的代码
        }// 当前线程释放锁
    }
    // 加锁失败之后：
    // 1）让出 CPU
    // 2）把线程状态修改为 BLOCKED
    // 3) 线程挂在这把锁的阻塞队列（等待队列）
    // 等待，直到锁被释放，处于该所阻塞队列中的某个线程被释放
    // 该线程的状态修改为 RUNNABLE 重新去抢锁

}
