package JavaEE.Threads;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CommonThreadMethod {
    //通用方法

    /**
     * //Thread.sleep(Timeout)
     * //外在现象：该语句会使得程序休眠（sleep）一段时间，才继续后序代码的执行。
     * //内部实际上：该线程让出 CPU，并且线程的状态被系统修改为 Timed-Waiting，
     * 直到指定时间过后，系统把线程的状态修改为 Runnable，线程才可能重新被分配 CPU，继续后序代码的执行。
     * @param args
     * @throws InterruptedException
     */
    public static void main1(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        System.out.println("A");
        thread.sleep(3 * 1000);

        //变形
        TimeUnit.DAYS.sleep(1);
        TimeUnit.HOURS.sleep(1);
        TimeUnit.MICROSECONDS.sleep(1);
        System.out.println("B");
    }

    /**
     * //Thread.yield()
     * //让出CPU
     * //只是让出 CPU，但状态仍然是 Runnable（就绪），随时可以再次被调度。适合做一些长期任务时，让出 CPU 让其他线程也可以使用 CPU.
     */
    static class PrintThread extends Thread {
        private final String target;
        private final boolean isYield;

        PrintThread(String target, boolean isYield) {
            this.target = target;
            this.isYield = isYield;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(target);
                if (isYield) {
                    Thread.yield();
                }
            }
        }
    }

    public static void main2(String[] args) {
//        PrintThread printA = new PrintThread("A", false);
        PrintThread printA = new PrintThread("A", true);
        PrintThread printB = new PrintThread("B", false);

        printA.start();
        printB.start();
    }


    /**
     * //Thread.currentThread()
     * //获取当前线程的实例引用。
     */
    static class PrintTask implements Runnable {
        @Override
        public void run() {

            Thread thread = Thread.currentThread();
//            System.out.println(thread == this);    // 无法比较
            System.out.println((Runnable)thread == this);
            System.out.println(thread.getName());
        }
    }

    static class PrintName extends Thread {
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            System.out.println(thread == this);
            System.out.println(thread.getName());
        }
    }

    public static void main3(String[] args) {
//        Thread thread = Thread.currentThread();
//
//        System.out.println(thread.getName());

        PrintName t1 = new PrintName();
        PrintName t2 = new PrintName();
        t2.start();
    }

    //线程的常见控制方法

    /**
     * 启动线程 thread.start()
     * 启动线程，即将线程的状态从 NEW 变成 RUNNABLE
     * (实际只是就绪状态，什么时候真正被调度到 CPU 上开始执行线程中的语句，对我们来说是随机的）
     *
     * 对比 thread.start() 和 thread.run()
     *         //thread.start() 启动线程，让启动的线程去干活
     *         //thread.run() 没有启动线程，自己去干活
     */
    public static class StartVsRun extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
    //对比
    public static void main4(String[] args) {
        //对比 thread.start() 和 thread.run()
        //thread.start() 启动线程，让启动的线程去干活
        //thread.run() 没有启动线程，自己去干活
        StartVsRun s = new StartVsRun();
        s.start();//Thread - 0
        s.run();//main
    }

    /**
     * 一个线程停止另一个线程（前提：两个线程都已经启动了）
     * 原理上，有 2 种停止方案：
     * 1. 粗暴地直接杀掉该线程。由于杀掉之后，可能造成损毁的中间数据，所以不建议使用。
     * 2. 采用协商式的停止，需要被停止的线程事先准备好一些准备工作。【主要采用的方式】
     */
    //1.粗暴地直接杀掉该线程。由于杀掉之后，可能造成损毁的中间数据，所以不建议使用。
    public static class SubThread extends Thread{
        @Override
        public void run() {
            while(true){
                System.out.println("A");
            }
        }
    }
    public static void main5(String[] args) {
        SubThread s = new SubThread();
        s.start();

        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        s.stop();
    }

    //2. 采用协商式的停止，需要被停止的线程事先准备好一些准备工作。【主要采用的方式】
//    static class InterruptSubThread extends Thread{
//        @Override
//        public void run() {
//            while(true){
//                System.out.println("A");
//
//                boolean interrupted = Thread.interrupted();
//                if(interrupted){
//                    break;
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        InterruptSubThread interruptSubThread = new InterruptSubThread();
//        interruptSubThread.start();
//
//        Scanner sc = new Scanner(System.in);
//        sc.nextLine();
//
//        interruptSubThread.interrupt();
//    }

    //基于协商式的方案，如果被停止线程正在 sleep（或者其他类似的休眠动作）时，则无法及时地响应停止信号。
    //为了避免这种情况，JVM 让所有的休眠动作，可能会抛出 InterruptedException，代表有人给当前线程发送中断信号了。
    //换言之，一个线程如何判断自己有没有别人中断？
    //1. 如果正在休眠，通过捕获 InterruptedException 异常得知
    //2. 如果没有在休眠，需要通过定期调用 Thread.interrupted() 方法得知。
    //得知中断信号之后，后序要如何处理，完全看代码实现。

    static class InterruptSubThread extends Thread{
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("A");

                    TimeUnit.SECONDS.sleep(10);
                    boolean interrupted = Thread.interrupted();
                    if (interrupted) {
                        break;
                    }
                }
            }catch(InterruptedException i){

            }
        }
    }

    public static void main(String[] args) {
        InterruptSubThread interruptSubThread = new InterruptSubThread();
        interruptSubThread.start();

        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        interruptSubThread.interrupt();
    }
}
