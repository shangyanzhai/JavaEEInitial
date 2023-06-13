package JavaEE.Threads.ThreadCommunication.ThreadSafe.SingletonModeOfThreadSafe;

/**
 * // 懒汉模式 / 懒加载（lazy-init）
 * // 不是线程安全的
 */
//public class LazyModel {
//    private LazyModel() {}
//
//    private static LazyModel l = null;
//
//    public static LazyModel getInstance() {
//        // 该方法第一次被调用时，进行实例化即可
//        // check-update 原子性
//        if(l == null){
//            l = new LazyModel();
//        }
//        //原子性
//        return l;
//    }
//}

/**
 * // 懒汉模式 / 懒加载（lazy-init）
 * // 是线程安全的
 * // 性能很差
 */
public class LazyModel {
    private LazyModel() {}

    private static LazyModel l = null;

    public static LazyModel getInstance1() {
        // 该方法第一次被调用时，进行实例化即可
        synchronized (LazyModel.class){
            //1. 初始情况下，s 为 null
            //2. 第一次需要 s 时，实例化 s【线程不安全的情况】
            //3. 以后，无论多少次获取 s，都是直接读取 s 即可。
            //为了第一次的原子性保证，让之后的所有时间都承担了加锁的成本，自然性能不高。
            if(l == null){
                l = new LazyModel();
            }
        }
        return l;
    }
    //优化
    public static LazyModel getInstance() {
        // 该方法第一次被调用时，进行实例化即可
        if(l == null){
            //只在第一次的时候给其上锁
            synchronized (LazyModel.class){
                // 能否保证现在 s 仍然是 null？  无法保证
                if (l == null) {    // 二次判断机制 double-check
                    // 加锁期间，没有其他线程实例化过
                    l = new LazyModel();
                }
            }
        }
        return l;
    }
}
