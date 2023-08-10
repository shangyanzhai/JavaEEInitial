package JavaEE.Threads.ThreadCommunication.ThreadSafe.Volatile;

public class VolatileTest {
    // 明确告诉编译器，a 和 b 是容易改变的
    // 需要编译器做一定的措施，尽可能及时将这些数据的修改同步回主存储
    // 凡是对这些数据的读操作，必须从主存储中读取，不能从工作存储中读取
    volatile int a;
    volatile static int b;
}
