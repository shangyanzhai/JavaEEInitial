package JavaEE.Threads;

public class ThreadMethod {
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
        System.out.println("B");
    }
}
