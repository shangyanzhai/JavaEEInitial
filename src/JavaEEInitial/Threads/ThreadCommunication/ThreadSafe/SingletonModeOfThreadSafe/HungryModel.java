package JavaEEInitial.Threads.ThreadCommunication.ThreadSafe.SingletonModeOfThreadSafe;
// 饿汉模式
// 多线程环境，有没有共享数据（s），没有写操作
// 线程安全的
public class HungryModel {
    private HungryModel() {}

    private static HungryModel h = new HungryModel();

    public static HungryModel getInstance() {
        return h;
    }
}
