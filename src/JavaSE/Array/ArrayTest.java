package JavaSE.Array;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayTest {
    /*1.
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     */
    public static int[] twoNumberSum(int[] nums,int target){
        int[] n = new int[2];
        outter://使用标签思想，可以让break直接跳出所有的循环语句，标签必须紧跟循环语句
        for (int i = 0; i < nums.length; i++) {
            for (int j = n.length; j > i; j--) {
                if(nums[i] + nums[j] == target){
                    n[0] = i;
                    n[1] = j;
                    break outter;
                }
            }
        }
        return n;
    }
    /*2.
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 示例 1:输入: [2,2,1],输出: 1
     * 示例 2:输入: [4,1,2,1,2],输出: 4
     */
    public static int oneself(int[] n){
        int num = n[0];
        for (int i = 1; i < n.length; i++) {
            num ^= n[i];
        }
        return num;
    }
    /*3.
    给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
    你可以假设数组是非空的，并且给定的数组总是存在多数元素。
    示例 1：输入：[3,2,3],输出：3
    示例 2：输入：[2,2,1,1,1,2,2],输出：2
     */
    /**
     * 此时的众数代表的是出现次数大于一半的数字，其中则代表，排序后，中间数一定会是众数
     * 在一组数据中，找出现次数大于1/2的数，这个数最多有且只有一个
     * 在一组数据中，找出现次数大于1/3的数，这个数最多有且只有两个
     * ......
     * 在一组数据中，找出现次数大于1/n的数，这个数最多有且只有（n - 1）个
     */
    //第一种方法
    public static int manyNumber(int[] n){
        Arrays.sort(n);//排序后变成升序数组
        return n[n.length / 2];
    }
    //第二种方法 摩尔投票法解决问题,时间复杂度是O（n）
    public static int manyNumbermo(int[] n){
        int cont = 0;
        int num = -1;
        for (int i = 0; i < n.length; i++) {
            //第一次循环的时候，将n[0]赋值给num，当num归零的时候则将此时的n[i]赋值给num，当循环到最后一个数的时候，则代表所对应的那个数是众数，
            //也就是多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素，因为如果该元素<=1/2,则数组中可能存在两个及以上的众数,那么需要更改投票思路
            //思路是满足正反想消的原则
            if(cont == 0){
                num = n[i];
                cont++;
            }
            if(num == n[i]){
                cont++;
            }else{
                cont--;
            }
        }
        return num;
    }
    /*4.
     * 给你一个整数数组 arr，请你判断数组中是否存在连续三个元素都是奇数的情况：如果存在，请返回 true ；否则，返回 false 。
     * 示例 1：输入：arr = [2,6,4,1],  输出：false
     * 解释：不存在连续三个元素都是奇数的情况。
     *
     * 示例 2： 输入：arr = [1,2,34,3,4,5,7,23,12],输出：true
     * 解释：存在连续三个元素都是奇数的情况，即 [5,7,23]
     */

    /**
     * 写法一
     * @param n
     * @return
     */
//    public static boolean threeOdd(int[] n){
//        for (int i = 0; i < n.length - 2; i++) {
//            if((n[i] % 2 != 0)&&(n[i + 1] % 2 != 0)&&(n[i + 2] % 2 != 0)){
//                return true;
//            }
//        }
//        return false;
//    }
    /**写法二，为了避免出现复杂条件的if
     * 改进，引入标志位
     * @param
     */
    public static boolean threeOdd(int[] n){
        int num = 0;//计数器，用来计算奇数的数量，遇到奇数++，遇到偶数归零
        for(int i : n){
            if(i % 2 != 0){
                num++;//遇到奇数，++
            }else{//则代表遇到偶数，则说明奇数并不连续，则归零
                 num = 0;
            }
            if(num == 3){
                return true;
            }
        }
        return false;
    }
    /*5.
    实现一个方法 transform, 以数组为参数, 循环将数组中的每个元素 乘以 2 , 并设置到对应的数组元素上. 例如 原数组为 {1, 2, 3}, 修改之后为 {2, 4, 6}
     */
    public static void transform(int[] n){
        for (int i = 0; i < n.length; i++) {
            n[i] *= 2;
        }
    }
    public static void main(String[] args) {
//        int[] num = {2,7,11,15};
//        int[] n = twoNumberSum(num,9 );
//        System.out.println(Arrays.toString(n));
//        int[] arr = {2,6,4,1};
//        boolean a = threeOdd(arr);
//        int[] n = {2,2,1};
//        int[] n1 = {4,1,2,1,2};
//        int a = oneself(n1);
//        System.out.println(a);
//        int n[] = {1,9,2,3,4,6,3,6};
//        Arrays.sort(n);
//        System.out.println(Arrays.toString(n));
//        int[] n ={2,2,1,1,1,2,2};
//        System.out.println(manyNumbermo(n));
        int[] n ={1,2,3};
        transform(n);
        System.out.println(Arrays.toString(n));
    }
}
