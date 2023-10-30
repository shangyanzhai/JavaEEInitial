package JavaEEInitial.Threads.ThreadCommunication.ThreadSafe.SingletonModeOfThreadSafe;

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

    //优化1
//    private static LazyModel l = null;
//    public static LazyModel getInstance2() {
//        // 该方法第一次被调用时，进行实例化即可
//        if(l == null){
//            //只在第一次的时候给其上锁
//            synchronized (LazyModel.class){
//                // 能否保证现在 s 仍然是 null？  无法保证
//                if (l == null) {    // 二次判断机制 double-check
//                    // 加锁期间，没有其他线程实例化过
//                    l = new LazyModel();//此时仍然可能存在代码重排序
//                }
//            }
//        }
//        return l;
//    }

    /**
     * SomeClass sc = new SomeClass(p1, p2, p3);
     *     1. 根据 SomeClass 类的信息，计算 SomeClass 对象的内存空间有多大
     *        根据大小申请足够的内存
     *        把刚申请好的内存统一初始化为 0x0。—— 属性的初始值是 0 的变种
     *        —— 对象存在了，但不是一个合法的对象
     *     2. 执行对象的初始化操作：执行构造代码块中的语句、构造方法中的语句。
     *        —— 对象是一个合法的对象
     *     3. 把对象赋值给 sc 引用 —— 暴露出对象，应用开发者可以看到对象
     *
     *     理论上是 1 -> 2 -> 3
     *     实际中可能 1 -> 3 -> 2，在单线程情况下没有问题，但如果在多线程情况下
     *                            则，有些线程可能看到非法对象，导致运行出错
     *
     *     volatile SomeClass sc = new SomeClass(p1, p2, p3);
     *     // 禁止 1 -> 3 -> 2 的重排序
     */
    private volatile static LazyModel l = null;//将其设置为volatile，此时可以禁止下方的重排序
    //最终优化
    public static LazyModel getInstance() {
        // 该方法第一次被调用时，进行实例化即可
        if(l == null){
            //只在第一次的时候给其上锁
            synchronized (LazyModel.class){
                // 能否保证现在 s 仍然是 null？  无法保证
                if (l == null) {    // 二次判断机制 double-check
                    // 加锁期间，没有其他线程实例化过
                    l = new LazyModel();//通过volatile禁止重排序
                }
            }
        }
        return l;
    }
}
