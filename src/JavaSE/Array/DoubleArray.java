package JavaSE.Array;

/**
 * 二维数组
 */
public class DoubleArray {
    public static void main(String[] args) {
        int[][] n = {
                {1,2,3,4},
                {5,6,7,8,9},
                {9,10}
        };
        //遍历二维数组，需要用到双层循环
        //最外层循环是表示行数
        for (int i = 0; i < n.length; i++) {
            //内层循环则表示当前行的列数
            for (int j = 0; j < n[i].length; j++) {
                System.out.print(n[i][j] + " ");
            }
            System.out.println();
        }
    }
}
