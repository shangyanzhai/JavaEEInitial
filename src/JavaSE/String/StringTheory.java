package JavaSE.String;

import java.util.Arrays;

/**
 * 字符串常量池的理解
 */
public class StringTheory {
    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = "hello";
        String str3 = new String("hello");
        String str4 = String.valueOf("hello");
        char[] arr1 = new char[]{'h','e','l','l','o'};
        String str5 = new String(arr1);
        /**
         * 采用直接赋值String str = "字符串字面量"的方式创建字符串对象，会使用字符串的常量池，且只有该方式会用到
         * 若该对象是第一次使用，就在堆中开辟新空间，产生新对象，产生之后该对象置入字符串的常量池
         * 当下次再次使用该对象时（还是采用直接赋值的方式），若常量池中已有该内容的字符串，直接引用常量池中的对象，不再创建字符串
         * "池化"思路，是程序设计中的常用思路，字符串常量池，数据库连接池，线程池等等...
         * 看到"池" ：节省内存，提高效率
         * 字符串字面量经常会被重复使用，因此放入常量池，当再次使用该内容时，省去创建对象，开辟内存的时间，直接复用已有对象
         */
        System.out.println(str1 == str2);//true
        System.out.println(str2 == str3);//false
        //调用intern方法，会将产生的字符串对象置入常量池（若不存在则置入）
        //若常量中已存在该对象，则返回常量中的字符串对象引用
        str3.intern();
        System.out.println(str2 == str3);//false，只要new出来，地址就不一致
        str3 = str3.intern();
        System.out.println(str2 == str3);//true
        System.out.println(str3 == str4);//true
        System.out.println(str4 == str5);//false
        str1 = "hello world";//当str1改变，则其中value数组则改变地址
        System.out.println(str1);//比较 此时输出为 hello world
        char[] arr2 = {'h','e','l','l','o'};
        change(str2,arr2);
        System.out.println(str2);//hello
        System.out.println(Arrays.toString(arr2));//Hello
    }
    public static  void change(String str,char[] arr){
        str = "hello world";
        arr[0] = 'H';
    }
}

