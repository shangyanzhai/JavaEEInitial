package JavaSE.String;

/**
 * JavaSE.String 反复修改字符串
 * StringBuilder or StringBuffer
 */

/**
 * //因此在Java中，某个场景需要反复修改字符串的内容，
 * //推荐使用Java提供的StringBuilder（90%都是，线程不安全，效率高）或StringBuffer（10%需要保证线程安全的场景，线程安全）类
 * //JavaSE.String 和 StringBuilder 之间的转换，也是值之间的相互拷贝
 */

public class StringBuilderBuffer {
    public static void main(String[] args) {
        //JavaSE.String-->StringBuilder对象
        //这两个sb类的内容可变，因此提供的方法都是同一对象上进行的内容修改
        //a.通过StringBuilder类的构造方法
        String str = "abc";
        StringBuilder str1 = new StringBuilder(str);
        //b.通过StringBuilder类的append方法
        StringBuilder str2 = new StringBuilder();
        str2.append(str);
        str2.append("def");
        str2.append(1);
        //StringBuilder-->String对象调用toString方法
        String str3 = str2.toString();
        System.out.println(str3);

        StringBuilder str4 = new StringBuilder(str3);
        str4.append("gh");//调用append方法在当前sb对象的后面追加任何数据类型
        System.out.println(str4);// abcdefgh
        StringBuffer str5 = new StringBuffer(str4);
        str5.reverse();//反转字符串，StringBuffer类中的
        System.out.println(str5);// hgfedcba
        str5.delete(1,3);//删除【start，end）区间内的字符
        System.out.println(str5);// hedcba
    }
}
