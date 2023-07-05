package JavaSE.String;
import java.util.*;

/**
 * String分割
 */
public class StringSplit {
    public static void main1(String[] args) {
        String str1 = "abcabcabc";
        String str2 = "ab";
        String[] arr = str1.split(str2);
        System.out.println(arr.length);
        System.out.println(Arrays.toString(arr));
    }
    public static void main(String[] args) {
        String str1 = "aaaaaa";
        String str2 = "aa";
        String[] arr = str1.split(str2);
        System.out.println(arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
