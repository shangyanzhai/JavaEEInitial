package JavaEEInitial.Threads.DemoOfThread;

// 控制台上不断地、交替地打印 A 或者 B
public class ConcurrentProgramTest {
    static class SubThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("B");
            }
        }
    }

    public static void main(String[] args) {
        SubThread subThread = new SubThread();
        subThread.start();

        while (true) {
            System.out.println("A");
        }
    }
}
