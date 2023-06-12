package JavaEE.Threads.ThreadTheoryAndWay;

/**
 * Java 中启动线程的几种基本方法
 */
//Runnable 仅仅代表的是任务，并不一定和多线程绑定。
public class MethodOfStartingTheThread {
    /**
     * 方法一
     * 定义Runnable 对象 + Thread 对象
     */

    static class Task implements Runnable{

        @Override
        public void run() {
            //任务要执行的语句
        }
    }

    public static void main1(String[] args) {
        // 利用刚才定义的任务类，实例化一个任务对象
        Task task = new Task();
        // 利用 Thread 类，实例化一个线程对象，并传入任务对象，代表要该线程执行的任务是什么
        Thread thread = new Thread(task);
        // 正式启动线程，即将线程的状态切换为就绪状态，等待被系统调度
        thread.start();
    }

    /**
     * 方法二
     * 直接继承 Thread 类
     */

    // 定义个线程的子类，并通过重写 run 方法来指定要执行的语句是什么
    static class SubThread extends Thread {
        @Override
        public void run() {
            // 任务要执行的语句
        }
    }

    public static void main2(String[] args) {
        // 利用刚才定义的 Thread 的子类，实例化一个线程对象
        SubThread thread = new SubThread();

        // 正式启动线程，即将线程的状态切换为就绪状态，等待被系统调度
        thread.start();
    }

    /**
     * 方法三
     * 利用匿名类或者 lambda 变形
     */

    public static void main(String[] args) {
        //lambda的写法
        Runnable task = () -> {
            // 要执行的代码
        };

        Thread thread = new Thread(task);
        thread.start();

        //进一步省略的写法
        Thread thread1 = new Thread(() -> {
            // 要执行的代码
        });
        thread1.start();
    }
}
