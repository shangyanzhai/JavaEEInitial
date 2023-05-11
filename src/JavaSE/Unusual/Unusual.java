package JavaSE.Unusual;

import java.util.Scanner;

/**
 * 异常
 */
public class Unusual {
        public static void main(String[] args) {
            System.out.println("异常发生前代码");
            //{
            //异常之前的代码块
            //}
            //try{
            //   //所有可能产生异常的代码块
            //}catch(要捕获的异常类型 异常对象){
            //   //出现异常之后执行的代码
            //}[finally{
            //   //无论是否产生异常，并且这个异常是否被捕获，都一定会执行的代码块，无论程序是否退出，退出之前都会执行
            //只有一种情况，调用System.exit(),将JVM进程强制退出
            // }]--可写可不写
            //{
            //异常之后的代码块
            //}//只有当异常被正确处理才会执行到
            try{
                System.out.println(10/0);
            }catch(ArithmeticException e) {//catch代码块只会捕获对应的异常，若try中出现的异常不是当前catch对应的异常，程序还是会中断
                System.out.println("算数异常发生了");
            }
            System.out.println("异常发生之后的代码");//只有当异常被正确处理后 才会执行到

            System.out.println("异常发生前代码");
            try{//若某代码中有可能产生多个异常时，推荐还是使用多个catch代码块来捕获不同的异常类型
                //异常地捕获只可能出现一次
                String str = null;
                System.out.println(str.length());//空指针异常（NPE异常）
//            int[] arr = {'a','b','c'};
//            System.out.println(arr[3]);//越界异常
            }catch(ArithmeticException e) {//catch代码块只会捕获对应的异常，若try中出现的异常不是当前catch对应的异常，程序还是会中断,
                // 此时需要考虑使用多个catch块
                //而此时ArithmeticException e捕获的是算数异常，捕获不到，程序中断
                System.out.println("算数异常发生了");
                System.err.println("算数异常");
                e.printStackTrace();
            }catch(NullPointerException n){
                /**
                 * 补充：捕获到相关异常之后打印异常产生的错误原因以及出现的位置，可以调用异常对象的printStackTrace方法
                 */
                System.out.println("空指针异常发生了");
                //错误输出  会在控制台输出红色内容，优先级低于标准输出
                System.err.println("空指针异常");
                //打印异常产生的位置和原因，在打印完最后输出
                n.printStackTrace();
            }catch(ArrayIndexOutOfBoundsException a){
                System.out.println("数组越界发生了");
                a.printStackTrace();
            }
            System.out.println("异常发生之后的代码");

            System.out.println(fun());//3

            //若某代码中有可能产生多个异常时，推荐还是使用多个catch代码块来捕获不同的异常类型，也有的程序员使用父类来接受所有的异常
            try{
                System.out.println(10/0);
//            JavaSE.String str = null;
//            System.out.println(str.length());//空指针异常（NPE异常）
//            int[] arr = {'a','b','c'};
//            System.out.println(arr[3]);//越界异常
            }catch(Exception e) {//不推荐这么写，此时出现异常不知道具体的异常产生原因
                System.out.println("异常发生了");
//            System.exit(0);//调用这个方法，强制中断JVM进程
            }finally {//一般资源关闭的操作，网络的关闭操作放在finally里面
                System.out.println("finally代码块");
            }
            System.out.println("异常发生之后的代码");

            /**
             * 关于catch代码块的特殊说明，若多个catch代码块出现父子关系，则一定是子类写在前面，顺序不能发生改变
             */
            System.out.println("异常发生前代码");
            try{
                String str = null;
                System.out.println(str.length());//空指针异常（NPE异常）
//            int[] arr = {'a','b','c'};
//            System.out.println(arr[3]);//越界异常
            }catch(ArithmeticException e) {//多个catch代码块只会捕获一次异常，走一个分支
                System.err.println("算数异常");
                e.printStackTrace();
            }catch(NullPointerException n){
                System.err.println("空指针异常");
                n.printStackTrace();
            }catch(ArrayIndexOutOfBoundsException a){
                System.err.println("数组越界发生了");
                a.printStackTrace();
            }catch(Exception e){//父类要写在子类后面，相当于兜底代码，如果写在前面，相当于废掉子类代码
                e.printStackTrace();
            }
            System.out.println("异常发生之后的代码");

            try{
                num();
            }catch (Exception e){
                System.err.println("发生异常");
            }
            System.out.println("程序正常退出");

        }
        public static int fun(){
            try{
                System.out.println(10 / 0);
                return 1;
            }catch(ArithmeticException e) {
                return 2;
            }finally {//如果finally中有return语句，则finally中的返回语句会覆盖catch和try中的返回语句
                //一般开发中finally只涉及资源关闭操作，不涉及返回语句
                return 3;
            }
        }
        //throws抛出多个异常，若抛出的异常有父子关系，只需要申明父类异常即可
//    public static void num() throws ArithmeticException,ArrayIndexOutOfBoundsException,Exception{
        public static void num() throws Exception{
            System.out.println(10 / 0);
            int[] arr = {'a','b','c'};
            System.out.println(arr[3]);//越界异常
            System.out.println("方法结束");
        }

        /**
         * throw用在方法内部，表示人为进行异常对象的产生并抛出。throw关键字就是由程序自己产生异常对象（不是JVM）
         * 当出现某种特殊情况是，程序内部自己来构造异常对象
         */
    }
    /**
     * 自定义异常类
     * JDK中内置了很多异常类，但是在程序开发过程中，很多场景下出现的问题，JDK中没有相关的异常对应，此时我们需要根据自己的需求自定义异常类
     *
     * 例：用户登陆时，用户名和密码错误，都属于异常，JDK中没有与之相关的异常类
     *
     * 自定义异常类的方法：
     * 1.只需要继承Exception（受查）或者RuntimeException（非受查） -》必须显示异常的 继承Exception
     *                                                          可以不显示的，继承RuntimeException
     * 2.实现一个带String的有参构造，String方法参数，就是异常的原因
     */

    class UserLogin{
        protected  String userName = "admin";
        protected String paseword = "123456";

        public void login(String userName,String paseword) throws PasswordException {
            if(!this.userName.equals(userName)){
                throw new UserNameException("用户名错误");
            }
            if(!this.paseword.equals(paseword)){
                throw new PasswordException("密码错误");
            }
        }

        public static void main(String[] args) {
            UserLogin userLogin = new UserLogin();
            Scanner scanner = new Scanner(System.in);
            String name = scanner.next();
            String password1 = scanner.next();
            try {
                userLogin.login(name,password1);
            } catch (PasswordException e) {
                e.printStackTrace();
            }
        }
    }
    //用户名异常，非受查异常
    class UserNameException extends RuntimeException{
        public UserNameException(String msg){
            super(msg);
        }
    }
    //密码异常，受查异常
    class  PasswordException extends Exception{
        public PasswordException(String msg){
            super(msg);
        }
    }
