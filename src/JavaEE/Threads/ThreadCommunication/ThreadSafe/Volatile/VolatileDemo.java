package JavaEE.Threads.ThreadCommunication.ThreadSafe.Volatile;

import java.util.Scanner;

public class VolatileDemo {
    volatile static boolean quit = false;    // 共享
//    static boolean quit = false;

    static class SubThread extends Thread {
        @Override
        public void run() {
            while (!quit) { }   // 原则上，子线程看到 quit 变成 true 之后，就应该退出

            System.out.println("子线程退出了");
        }
    }

    public static void main(String[] args) {
        SubThread t = new SubThread();
        t.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        quit = true;    // 主线程修改之后，原则，子线程应该能看到这个修改
    }
}
