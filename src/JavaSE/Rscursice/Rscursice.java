package JavaSE.Rscursice;

/**
 * 递归练习
 */
public class Rscursice {
    /**
     * 斐波那契数列
     * @param n
     * @return
     */
    public static int test1(int n ){
        if((n == 2)||(n == 1)){
            return 1;
        }
        return test1(n - 1) + test1(n - 2);
    }

    /**
     * 计算n的阶乘
     * @param n
     * @return
     */
    public static int factorial(int n){
        if(n == 2){
            return 2;
        }
        return n * factorial(n-1);
    }

    /**
     * 计算从1 - n之间所有数之和
     * @param n
     * @return
     */
    public static int add(int n){
        if(n == 1){
            return 1;
        }
        return n + add(n-1);
    }
    //解法1

    /**
     * 顺序打印一个非负整数的每一位
     * @param n
     */
    public static void print(int n){
        if(n < 10){
            System.out.print(n + " ");
            return;
        }
        //在此刻 ，n >= 10是成立的，但是任然要最后一个输出各位，而这个函数正好是我自身在写的函数
        print(n / 10);
        System.out.print(n % 10 + " ");
        return;
    }
    //解法2
//    public static int print(int n){
//        int temp = 0;
//        for(int i = 1;n / i != 0;i *= 10){
//            temp++;
//        }
//        int num = 1;
//        for(int i = 1;i < temp;i++){
//            num *= 10;
//        }
//        if(n < 10){
//            System.out.println(n);
//            return 0;
//        }
//        System.out.println(n / num);
//        return print(n % num);
//    }
    //写一个递归方法，输入一个非负整数，返回组成它的数字之和
//    public static int make(int n){//1234 = 1000 + 200 + 30 + 4
//        int temp = 0;
//        for(int i = 1;n / i != 0;i *= 10){
//            temp++;
//        }
//        int num = 1;
//        for(int i = 1;i < temp;i++){
//            num *= 10;
//        }
//        int a = n / num;
//        if(n < 10){
//            System.out.print(n * num);
//            return 0;
//        }
//        System.out.print( a * num + "+" );
//        return make(n % num);
//    }

    /**
     * 写一个递归方法，输入一个非负整数，返回组成它的数字之和
     * @param n
     * @return
     */
    public static int make(int n){
        if(n < 10){
            return n;
        }
        int sum = 0;
        sum += n % 10;
        return sum + make(n / 10);
    }
    public static void main(String[] args) {
        int num = test1(6);
        System.out.println(num);
        num = factorial(5);
        System.out.println(num);
        num = add(10);
        System.out.println(num);
        print(1234);
        System.out.println(make(1235));

    }
}
