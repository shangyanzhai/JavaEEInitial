package JavaSE.Scanner;
import java.io.IOException;
import java.util.*;
public class ScannerTest {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            int i = scanner.nextInt();
            long l = scanner.nextLong();
            String str = scanner.nextLine();
            float f = scanner.nextFloat();
            short s = scanner.nextShort();
            byte b = scanner.nextByte();
            double d = scanner.nextDouble();
            boolean bl = scanner.nextBoolean();
            char c = scanner.nextLine().charAt(0);
            //或者用System.in
            char c1 = (char)System.in.read();
        }

        /**
         * //创建一个String[] 字符串数组，在输入之前，必须要先输入nextLine()
         * //输入时，先调用nextInt()函数输入n，然后回车，
         * //再调用nextLine()函数依次输入String，结果第一个字符串自动被赋值，
         * //即第一次调用nextLine()函数返回的值并不是从键盘输入的值，而是读入的回车。
         */
        while(scanner.hasNext()){
            //首先输入一个整型数字，代表字符串个数
            int a = scanner.nextInt();

            //此时，则需要先调用nextLine()函数，再一次输入String
            String[] str = new String[a];
            scanner.nextLine();
            for(int i = 0;i < a;i++){
                str[i] = scanner.nextLine();
                System.out.println(str[i]);
            }
        }

        /**
         * 产生InputMismatchException异常的原因是：nextLine()不能用在nextInt后面。
         * 因为nextLine()方法是返回的是Enter键之前的所有字符，
         * 在使用了nextInt() 方法之后在其后输入的空格键、Tab键或Enter键等视为分隔符或结束符，其仍在缓冲区内；
         * 若紧接着使用nextLine() ,则nextLine() 自动读取Enter等作为其结束符，
         * 则无法从键盘输入值，强行输入会报出InputMismatchException异常。
         */
        while(scanner.hasNext()){
            int num = scanner.nextInt();
            String str = scanner.nextLine();
        }
    }
}
