package JavaEE.Threads.ThreadTheoryAndWay;
/**
 * Thread 和 Runnable 对比
 */

/**
 * Thread 对象的介绍
 *
 * 1. JVM 进程中可以包含多个线程，其中启动时的一个前台线程（non-daemon）被称为主线程（名字就是 main）
 * 2. JVM 线程有父子关系，自动继承 优先级/前后台线程
 * 3. 线程分前台线程/后台线程（daemon）
 * 4. JVM 进程退出条件：所有前台线程都结束。不管后台前程，也不管主线程。
 * 5. 创建线程的基本方式两种。
 * 6. 线程都可以有自己的名字（name），为了给人看的，对系统没啥用，可以重复。
 *
 *
 * 构造方法
 *          Thread()：创建了一个没有任何事情要做的线程
 *          Thread(Runnable target)：传入一个任务，创建线程
 *          Thread(Runnable target, String name)：传入一个任务，并且指定线程名字
 *          Thread(String name)：创建了一个没有任何事情要做的线程但设置了名字
 */
public class RunnableAndThread {
    // 任务类
    static class PrintALoopTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("A");
            }
        }
    }
    //任务：Runnable（接口） 对象
    //定义任务：
    //定义类，实现 Runnable 接口，把任务（一组语句）放到 run 方法（重写 run 方法）
    //对比，这个是主线程在执行，所以因为上面在不断的死循环，以至于主线程没有精力再执行下面打印B的操作了
    public static void main1(String[] args) {
        // 通过任务类定义两个对象
        // 所以最终有两个任务
        // 只是这两个任务的工作都是循环打印 A
        PrintALoopTask t1 = new PrintALoopTask();
        PrintALoopTask t2 = new PrintALoopTask();

        t1.run();       // 没有创建线程，所以当前线程（主线程）去执行任务 1

        while (true) {  // 主线程在执行
            System.out.println("B");
        }
    }
    //线程：Thread 类的对象
    //创建线程对象时，需要被该线程分配任务（传入一个任务对象）
    //此时，创建了一个子线程，该子线程正在不断地执行死循环操作，但是主线程是没有在执行的
    //此时主线程和子线程间断的占用CPU资源，所以是一会打印A，一会打印B
    public static void main(String[] args) {
        // 通过任务类定义两个对象
        // 所以最终有两个任务
        // 只是这两个任务的工作都是循环打印 A
        PrintALoopTask t1 = new PrintALoopTask();
        PrintALoopTask t2 = new PrintALoopTask();

        // 创建了一个线程，让该线程去执行 任务 1
        Thread tA = new Thread(t1);
        tA.setName("子线程");
        tA.start(); // 才真正地启动了线程了

        while (true) {  // 主线程在执行
            System.out.println("B");
        }
    }
}