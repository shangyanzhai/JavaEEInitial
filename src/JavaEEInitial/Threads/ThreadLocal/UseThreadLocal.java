package JavaEEInitial.Threads.ThreadLocal;

import java.sql.Connection;

/**
 * class ThreadLocal<T> {
 *     // 支持被子类重写：初始值
 *     protected T initialValue() {
 *         return null;
 *     }
 *
 *     // 各个线程自己设置自己的
 *     public T get() { ... }
 *     public void set(T value) { ... }
 * }
 */

public class UseThreadLocal {
    // Connection 对象不是线程安全的，所以，不能跨线程使用同一个 Connection 对象
    // 但是同一个线程的多次查询之间，可以使用同一个 Connection 对象
    private static ThreadLocal<Connection> connections = new ThreadLocal<>();
    /**
        private static ThreadLocal<Connection> connections = new ThreadLocal<>() {
            //// 支持被子类重写：初始值
            @Override
            protected Object initialValue() {
                return new Connection();
            }
        };
     */


    private static class SubThread extends Thread {
        @Override
        public void run() {
            Connection connection = connections.get();
        }
    }
}