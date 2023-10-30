package JavaEEInitial.Threads.ThreadCommunication.ThreadSafe.Lock.Synchronized;

/**
 * 锁（synchronized 关键字）—— monitor lock
 * synchronized 是一个可重入锁
 *      即当前如果是上锁状态，如果上锁的人和目前锁的所持有人为同一个，即可以继续上锁
 * 通过 锁 让线程和线程之间呈现互斥（exclusive）关系。
 */
/**
 * 互斥（exclusive）
 * 要做到对共享数据访问互斥：
 * 1. 所有访问共享数据的位置要加锁
 * 2. 一定要确保加的是同一把锁（同时是同一个对象）
 */

/**
 * Synchronized 的作用：
 * 1. 保证原子性（最重要的作用）：需要正确地书写代码，让临界区代码之间互斥来实现。
 * 2. 有限地保证内存可见性：
 *   1） 加锁成功之后，线程需要保证自己工作存储中的数据必须是最新的；
 *   2） 释放锁之前，线程需要保证自己工作存储中的数据写回主存储中；
 *   3） 持有锁期间，不做任何约定，即工作存储中的数据可以随时写回主存储或者不写回均可。
 * 3. 有限地保证代码重排序结果：
 *   1） 持有锁期间的代码不能重排序到加锁之前或者释放锁之后才执行。
 */
public class LockItUpOfSynchronized {
    /**
     * 使用方法 2 种：
     */
    //1. 用来修饰方法（静态方法 / 普通方法）
    public synchronized void method() {  }

    public synchronized static void staticMethod() {  }


    //2. 组成同步代码块
    public void method1() {
        synchronized (this) {    // 括号内对象的引用，引用不能为 null
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
        synchronized (this) {    // 括号内对象的引用，引用不能为 null,尝试对this指向的 SynchronizedUse 对象上锁
            // 只有加锁成功之后，才能执行此处的代码
        }// 当前线程释放锁
    }
    // 加锁失败之后：
    // 1）让出 CPU
    // 2）把线程状态修改为 BLOCKED
    // 3) 线程挂在这把锁的阻塞队列（等待队列）
    // 等待，直到锁被释放，处于该所阻塞队列中的某个线程被释放
    // 该线程的状态修改为 RUNNABLE 重新去抢锁
    /**
     * 静态方法加锁
     * 首先，每个类都有其本身的对象，同样类，原则上只有同一个cls对象
     * Class cls = 类.class;
     * new 类().getClass() == 类.class
     * 例 : class SomeClass{
     *     synchronized static void m1(synchronized.class){}//这里是对SomeClass.class指向的class对象上锁
     *     synchronized void m2(this){}//这里是对SomeClass对象上锁
     * }
     */
    public static void StaticMethod() {
        synchronized (LockItUpOfSynchronized.class) {    //类似于对类进行加锁
            // 只有加锁成功之后，才能执行此处的代码
        }// 当前线程释放锁
    }

}
