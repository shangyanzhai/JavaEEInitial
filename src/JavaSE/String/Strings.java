package JavaSE.String;

import java.util.Arrays;

import JavaSE.ObjectOriented_For_Java.Port.Student;

import java.nio.charset.StandardCharsets;

/**
 * 字符串修改方法
 *
 * //String类上一把刀，他的对象不可变
 * //因此String类的内部提供的所有涉及字符串修改的方法，都会产生新的字符串对象，并不会因此影响原字符串对象
 */

public class Strings {
    public static void main(String[] args) {
        /**
         * 1.字符串的对象创建方法，常用的有四种方式
         */
        //a.使用字符串的常量直接赋值
        String str1 = "hello";
        //b.通过字符串常量的构造方法
        String str2 = new String("hello");
        //c.通过字符数组进行构造
        char[] a = new char[]{'h','e','l','l','o',' '};
        String str3 = new String(a);
        //d.调用字符串的valueOf方法
        String str4 = String.valueOf("Hello");
        //调用字符串对象。length() =》方法，获取字符串的长度，其实是内部value数组中保存的字符个数
        //字符串中，空格也算单个字符
//        System.out.println(str1.length());
//        System.out.println(str3.length());
        //这个方法判断当前字符串对象的长度是否为0
//        System.out.println(str1.isEmpty());
//        System.out.println("hello".isEmpty());

        //题目
        //若字符串的引用为null
        //JavaSE.String str = null;
        //boolean isEmpty = str.isEmpty();
        //a.false b. true c.编译出错
        //选c，只要是通过null值'.'操作任何成员方法，一定都是空指针异常
        /**
         * 判断字符串是否为空的方法
         */
//        JavaSE.String str5 = null;
//        boolean ret = false;
//        if( str5 == null|| str5.isEmpty() ){//必须如此写，如果isEmpty()在前面，则有可能字符串为空，发生空指针异常
//            ret = true;
//        }

        /**
         * 2.两个字符串对象的比较
         */
        //1.通过 == 比较 (比较的是两个字符串的地址，不看内容)
        System.out.println((str1 == str2));
        //2.通过equals方法比较两个字符串的内容是否相等（大小写敏感），返回布尔值
        System.out.println(str1.equals(str4));
        //3.通过 equalsIgnoreCase 方法比较两个字符串的内容是否相等（大小写不敏感），返回布尔值
        System.out.println(str1.equalsIgnoreCase(str4));
        //4.通过compareTo方法来比较两个字符串对象的大小关系，返回int
        //按字符串字典序大小比较，若出现不想等的字符，直接返回这两个字符的大小差值（字符编码差值）
        System.out.println(str1.compareTo(str4));
        //前k（两个字符串之间最小长度）个字符都相等，返回长度差
        System.out.println(str1.compareTo(str3));

        /**
         * 3.字符串内容的查找
         */
        //1.在字符串中查找指定的字符 char charAt(int index);
        String str6 = "hello world";
        char a1 = str6.charAt(4);//调用该方法一定要考虑越界问题（所有和索引相关的方法一定要考虑是否越界的问题）
        System.out.println(a1);//o
        //越界错误
//        System.out.println(str6.charAt(20));
        //问题：写一个方法，判断用户输入的字符串是否由纯数字组成，是返回true，不是返回false
        String str7 = "123456";
        System.out.println(stringIsNumber(str7));
        System.out.println(stringIsNumber(str1));

        //问题：将字符串转化为整型，超出整型最大值返回0，字符串中除了第一位出现非数字字符返回0，字符串第一位除了'+'，'—'以外的非数字字符返回0，null或长度为0返回0
        System.out.println(stringToInt(str7));

        //查找字符/字符串
        String str8 = "hello world";
//        System.out.println(str8.indexOf('l'));//从前往后，找到返回第一次出现，没找到返回-1
//        System.out.println(str8.indexOf('l',3));
//        System.out.println(str8.indexOf("lo") );
//        System.out.println(str8.indexOf("lo",4));
//        System.out.println(str8.lastIndexOf('l'));//从后往前
//        System.out.println(str8.lastIndexOf('l',1));
//        System.out.println(str8.lastIndexOf('l',3));
//        System.out.println(str8.lastIndexOf("lo"));
//        System.out.println(str8.lastIndexOf("lo",3));
//        System.out.println(str8.lastIndexOf("lo",4));

        /**
         * 判断字符串中是否有指定的子串，若有返回true，否则返回false
         * 经常搭配if分支语句使用
         */
//        System.out.println(str8.contains("llo"));
//        System.out.println(str8.contains("world"));
//        System.out.println(str8.contains("World"));

        /**
         * 4.字符串转化
         */
        //1.其他类型转为字符串对象 valueOf方法，可以接受所有参数
        String s1 = String.valueOf(123);
        String s2 = String.valueOf(12.3);
        //当传入的是第三方的对象时，默认调用toString方法
        String s3 = String.valueOf(new Student("小黄",18,01));
        String s4 = String.valueOf(false);
//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s3);
//        System.out.println(s4);
        //2.大小写转换,只限字母字符串，包含数字或中文没啥效果
        String s5 = "hello";
        String s6 = "HELLO";
        String s7 = "Hello";
        String s8 = "hello world 123";
        //s5转为全大写处理
        s5 = s5.toUpperCase();
        System.out.println(s5);
        //s6转为全小写
        s6 = s6.toLowerCase();
        System.out.println(s6);
//        s7 = s7.toUpperCase();
        s7 = s7.toLowerCase();
        System.out.println(s7);
        s8 = s8.toUpperCase();
        System.out.println(s8);
        //3.字符串转数组
        /**
         * 字符串转数组
         */
//        1.字符串转为字符数组 toCharArray();
        String strr1 = "hello world 123 世界";
        char[] arr1 = strr1.toCharArray();
        System.out.println(Arrays.toString(arr1));
//        2.转为字节数组getBytes(可以传入指定的字符编码)
//        如果getBytes方法括号内不写，则按照JVM默认的编码转为字节数组
        byte[] arr2 = strr1.getBytes();
        byte[] arr3 = strr1.getBytes(StandardCharsets.US_ASCII);
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));

        //4.格式化字符串,类似与C的printf，但是支持正则表达式
        //格式化字符串的使用和 System.out.printf 的方式一样
        String strr2 = String.format("%d-%d-%d",2022,10,20);
        System.out.println(strr2);

        /**
         * 5.字符串的替换
         */
        strr1 = "hello world 123 世界";
        //替换所有的指定内容 Sting replaceAll(JavaSE.String regex/*想要替换的子串内容*/,JavaSE.String replacement/*替换后的内容*/)
        System.out.println(strr1.replaceAll("123","你好"));
        System.out.println(strr1);//字符串不可变
        System.out.println(strr1.replaceAll("l","a"));
        //替换首个内容 Sting replaceFirst(JavaSE.String regex/*想要替换的子串内容*/,JavaSE.String replacement/*替换后的内容*/)
        System.out.println(strr1);//字符串不可变
        System.out.println(strr1.replaceFirst("l","a"));

        /**
         * 6.字符串拆分，讲一个字符串按照指定的格式拆分成字符串数组
         */
        strr1 = "hello world 123 世界";
        //将字符串以指定的格式全部拆分，能拆多少是多少，长度为拆完个数 JavaSE.String[] split(JavaSE.String regex)
        String[] arr4 = strr1.split(" ");
        System.out.println(Arrays.toString(arr4));
        System.out.println(arr4.length);
        //将字符串以指定的格式，拆分为limit组，长度为limit JavaSE.String[] split(JavaSE.String regex,int limit)
        String[] arr5 = strr1.split(" ",2);
        System.out.println(Arrays.toString(arr5));
        System.out.println(arr5.length);
        //当调用split碰到某些特殊字符无法正确拆分时，需要用到转义字符,其中特殊字符有"｜"，"*"，"+"，都得加上\\
        //如果是"\",则得写出"\\\\"
        //例：ip
        String strr3 = "192.168.1.2";
        String [] arr6 = strr3.split(".");
        System.out.println(Arrays.toString(arr6));//因为java中"."为特殊字符，无法正确拆分
        String [] arr7 = strr3.split("\\.");//使用双斜杠转义
        System.out.println(Arrays.toString(arr7));
        //TODO:为什么此时拆分的时候，".1"都没了
        String [] arr8 = strr3.split(".1");
        System.out.println(Arrays.toString(arr8));
        String [] arr9 = strr3.split("\\.1");
        System.out.println(Arrays.toString(arr9));

        /**
         * 7.字符串的截取方法
         */
        strr1 = "hello world 123 世界";
        //从指定索引截取到结尾,从指定索引位置开始截取字符出啊结尾，保留开始字符 JavaSE.String substring(int beginIndex)
        System.out.println(strr1);
        System.out.println(strr1.substring(3));
        //从指定索引截取到结尾，牵扯到区间的，都是左闭右开，不保留结束字符 JavaSE.String substring(int beginIndex，int endIndex)
        System.out.println(strr1);
        System.out.println(strr1.substring(3,12));
        //问题：如何实现，传入一个字符串（只包含字母），进行首字母大写操作
        String strr4 = "student";
        System.out.println(strr4);
        strr4 = strr4.substring(0,1).toUpperCase() + strr4.substring(1);
        System.out.println(strr4);

        /**
         * trim():去掉字符串的左右两端空格（制表符，换行符），中间保留
         */
        String strr5 = "    hello world ";
        System.out.println(strr5.trim());
    }
    //问题：写一个方法，判断用户输入的字符串是否由纯数字组成，是返回true，不是返回false
    public static boolean stringIsNumber(String str){
        boolean isEmpty = true;
        //判断边界条件
        if(str == null || str.length() == 0){
            isEmpty = false;
            return isEmpty;
        }
        for (int i = 0; i < str.length(); i++) {
//            if(str.charAt(i) < '0' || str.charAt(i) > '9'){
//                isEmpty = false;
//            }
            //JDK中的char的包装类Character中有一个静态方法，判断字符是否是数字字符
            if(!Character.isDigit(str.charAt(i))){
                isEmpty = false;
            }
        }
        return isEmpty;
    }
    //问题：将字符串转化为整型，超出整型最大值返回0，字符串中除了第一位出现非数字字符返回0，字符串第一位除了'+'，'—'以外的非数字字符返回0，null或长度为0返回0
    public static int stringToInt(String str){
        //边界条件判断
        if(str == null || str.length() == 0){
            return 0;
        }
        int flag = 1;
        long ret = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(i == 0 && c == '+'){
                flag = 1;
            } else if (i == 0 && c == '-') {
                flag = -1;
            }else if(!Character.isDigit(c)){
                return 0;
            }else{
                ret = ret * 10 + (c - '0');//ret = 0 => ret = 1 => ret = 1 * 10 +2....
                if(ret > Integer.MAX_VALUE){
                    return 0;
                }
            }
        }
        ret = ret * flag;

        return (int) ret;
    }
}
