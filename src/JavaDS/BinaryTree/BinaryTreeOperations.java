package JavaDS.BinaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeOperations {
    // 线性思想
    private static int resultSize;
    // 线性的办法
    private static int resultLeafSize;

    public static TreeNode buildTree() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n1.left = n2;   n1.right = n3;
        n2.left = null; n2.right = null;    // 可以省略
        n3.left = n4;   n3.right = n5;
        n4.left = null; n4.right = null;
        n5.left = null; n5.right = null;

        return n1;
    }

    public static void main(String[] args) {
        TreeNode root = buildTree();

//        System.out.print("前序：");
//        preorder(root);
//        System.out.println();
//
//        System.out.print("中序：");
//        inorder(root);
//        System.out.println();
//
//        System.out.print("后序：");
//        postorder(root);
//        System.out.println();
//
//        int size = sizeof_1(root);
//        System.out.println("结点个数: " + size);
//        size = sizeof_1(root);
//        System.out.println("结点个数: " + size);
//        size = sizeof_1(root);
//        System.out.println("结点个数: " + size);
//
//        resultSize = 0;
//        sizeof_2(root);
//        System.out.println("结点个数: " + resultSize);
//
//
//        resultSize = 0;
//        sizeof_2(root);
//        System.out.println("结点个数: " + resultSize);
//
//
//        resultSize = 0;
//        sizeof_2(root);
//        System.out.println("结点个数: " + resultSize);
//
//
//        int leafSize = leafSizeOf_1(root);
//        System.out.println("叶子结点个数: " + leafSize);
//
//        resultLeafSize = 0;
//        leafSizeOf_2(root);
//        System.out.println("叶子结点个数: " + resultLeafSize);
//
//        resultLeafSize = 0;
//        leafSizeOf_2(root);
//        System.out.println("叶子结点个数: " + resultLeafSize);
//
//        resultLeafSize = 0;
//        leafSizeOf_2(root);
//        System.out.println("叶子结点个数: " + resultLeafSize);
//
//        int kSize = sizeofKLevel(root, 1);
//        System.out.println("第1层: " + kSize);
//
//        kSize = sizeofKLevel(root, 2);
//        System.out.println("第2层: " + kSize);
//
//        kSize = sizeofKLevel(root, 3);
//        System.out.println("第3层: " + kSize);
//
//        kSize = sizeofKLevel(root, 4);
//        System.out.println("第4层: " + kSize);
        // 构建一棵空树，如何处理
        // 空树 <=> 一个结点都没有的树 <=> 没有根结点
//        root = null;
//
//        levelOrderWithNull(root);
        System.out.println(sizeofKLevel(root, 2));
        System.out.println(sizeof_1(root));
        System.out.println(levelOrderWithLevelButReturnList(root));
    }

    /**
     * // 前序遍历（只进行打印）
     * // 这个方法的含义：对 root 结点为根的树进行遍历
     * @param root
     */
    public static void preorder(TreeNode root) {
        if (root == null) {
            // 根结点不存在 <=> 空树
            System.out.print("# ");
            return;
        }

        // 不是空树了
        // 前序遍历：根 + 左子树的前序遍历 + 右子树的前序遍历
        System.out.print(root.val + " ");   // 根

        // 对左子树进行前序遍历（把左子树的根 <=> 当前根的左孩子）
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * // 中序遍历（只进行打印）
     * // 这个方法的含义：对 root 结点为根的树进行遍历
     * @param root
     */

    public static void inorder(TreeNode root) {
        if (root == null) {
            System.out.print("# ");
            return;
        }

        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }
    /**
     * // 后序遍历（只进行打印）
     * // 这个方法的含义：对 root 结点为根的树进行遍历
     * @param root
     */
    public static void postorder(TreeNode root) {
        if (root == null) {
            System.out.print("#");
            return;
        }
        // 从双亲进来，第一次经过该结点

        postorder(root.left);
        // 左子树回来，第二次经过该结点

        postorder(root.right);
        // 右子树回来，第三次经过该结点
        System.out.print(root.val + " ");
    }

    /** //反回结点个数
     * // 子问题的思想
     * // 结点个数
     * @param root
     * @return
     */
    public static int sizeof_1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSize = sizeof_1(root.left);
        int rightSize = sizeof_1(root.right);

        int size = leftSize + rightSize + 1;
        return size;
    }
    /**
     * // 子问题的思想
     * @param root
     * @return
     */
    private static void sizeof_2(TreeNode root) {
        if (root == null) {
            return;
        }

        resultSize = resultSize + 1;
        sizeof_2(root.left);
        sizeof_2(root.right);
    }

    /**
     * // 求叶子结点个数
     * // 子问题思想
     * @param root
     * @return
     */
    private static int leafSizeOf_1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 只有一个结点的树
        if (root.left == null && root.right == null) {
            return 1;
        }

        int leftSize = leafSizeOf_1(root.left);
        int rightSize = leafSizeOf_1(root.right);

        int leafSize = leftSize + rightSize;
        return leafSize;
    }
    /**
     * // 求叶子结点个数
     * // 子问题思想
     * @param root
     * @return
     */
    private static void leafSizeOf_2(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null || root.right != null) {
            // 不是叶子
            leafSizeOf_2(root.left);
            leafSizeOf_2(root.right);
            return;
        }

        // 剩余情况 root.left == null && root.right == null
        resultLeafSize += 1;
    }
    /**
     * // 求二叉树最大深度
     * // 子问题思想
     * @param root
     * @return
     */
    private static int heightOf(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = heightOf(root.left);
        int rightHeight = heightOf(root.right);

        return Integer.max(leftHeight, rightHeight) + 1;
    }

    /**
     * // 求二叉树每层节点个数
     // 规定 root 所在的层是 1，k >= 1
     * @param root
     * @return
     */
    private static int sizeofKLevel(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }

//        if (root.left == null && root.right == null) {
//            return k == 1 ? 1 : 0;
//        }

        if (k == 1) {
            return 1;
        }

        int leftSize = sizeofKLevel(root.left, k - 1);
        int rightSize = sizeofKLevel(root.right, k - 1);

        return leftSize + rightSize;
    }

    /**
     * // 使用前序的方式进行搜索
     * // 1. 先判断根的元素是否是 target，一旦找到，返回找到
     * // 2. 如果根不是，去左子树查找，一旦找到，返回找到
     * // 3. 最后去右子树查找
     * @param root
     * @param target
     * @return
     */
    public static boolean contains(TreeNode root, int target) {
        // 使用前序的方式进行搜索
        // 1. 先判断根的元素是否是 target，一旦找到，返回找到
        // 2. 如果根不是，去左子树查找，一旦找到，返回找到
        // 3. 最后去右子树查找
        if (root == null) {
            return false;
        }

        if (root.val == target) {
            return true;
        }

        boolean leftContains = contains(root.left, target);    // 左子树查找
        if (leftContains) {
            return true;
        }

        boolean rightContains = contains(root.right, target);
        return rightContains;
    }
    /**
     * // 使用前序的方式进行搜索
     * // 1. 先判断根的元素是否是 target，一旦找到，返回对应结点
     * // 2. 如果根不是，去左子树查找，一旦找到，返回对应结点
     * // 3. 最后去右子树查找
     * @param root
     * @param target
     * @return
     */
    public static TreeNode nodeOf(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        if (root.val == target) {
            return root;
        }

        TreeNode leftResult = nodeOf(root.left, target);
        if (leftResult != null) {
            // 左子树中找到了
            // 应该返回什么？
            return leftResult;
        }

        return nodeOf(root.right, target);
    }

    /**
     * // 使用前序的方式进行搜索
     * // 1. 先判断根结点是不是 target，一旦找到，返回找到
     * // 2. 如果根不是，去左子树查找，一旦找到，返回找到
     * // 3. 最后去右子树查找
     * @param root
     * @param target
     * @return
     */
    public static boolean containsNode(TreeNode root, TreeNode target) {
        if(root == null){
            return false;
        }

        if(root == target){
            return true;
        }

        if(containsNode(root.left,target)){
            return true;
        }

        return containsNode(root.right,target);
    }
    /**
     * 打印每个结点
     * // null 写成 #
     * @param root
     */
    public static void levelOrderWithNull(TreeNode root) {
        if(root == null ){
            return ;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node == null){
                System.out.println("#");
            }else{
                System.out.println(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
    }

    /**
     * 打印每个结点
     * @param root
     */
    public static void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        // 一开始先把根放入结点，开启过程
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * //判断是否是完全二叉树
     * //普通二叉树 1 2 3 # # 3 # 4 # #
     * //完全二叉树 1 2 3 4 # # #
     * @param root
     * @return
     */
    public static boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 取出 null 即可以停止了
        while (true) {
            TreeNode node = queue.poll();
            if (node == null) {
                break;
            }

            queue.offer(node.left);
            queue.offer(node.right);
        }

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断两棵 二叉树 是否镜像
     * @param p
     * @param q
     * @return
     */
    public boolean isMirror(TreeNode p, TreeNode q) {
        //判断是否为空树
        if(p == null && q == null){
            return true;
        }
        if(p == null || q == null){
            return false;
        }
        // 两棵树都不是空树的情况

        // 根的元素相等 && p 的左子树和 q 的右子树互为镜像 && p 的右子树和 q 的左子树互为镜像
        return p.val == q.val && isMirror(p.left,q.right) && isMirror(p.right,q.left);

    }

    /**
     * //打印每个结点的深度
     * @param root
     * @param level
     */
    public void preorderWithLevel(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        System.out.println(root.val + "    " + level);
        preorderWithLevel(root.left, level + 1);
        preorderWithLevel(root.right, level + 1);
    }

    public List<TreeNode> levelOrderButReturnList(TreeNode root) {
        List<TreeNode> list = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null){
            return list;
        }

        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node);
            if(root.left != null){
                queue.offer(root.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return list;
    }

    public static List<TreeNodeWithLevel> levelOrderWithLevelButReturnList(TreeNode root) {
        List<TreeNodeWithLevel> list = new LinkedList<>();
        Queue<TreeNodeWithLevel> queue = new LinkedList<>();

        if(root == null){
            return list;
        }
        TreeNodeWithLevel treeNodeWithLevel = new TreeNodeWithLevel(root,1);
        queue.offer(treeNodeWithLevel);

        while(!queue.isEmpty()){
            TreeNodeWithLevel treenode = queue.poll();
            list.add(treenode);
            if(treenode.node.left != null){
                TreeNodeWithLevel node1 = new TreeNodeWithLevel(treenode.node.left,treenode.level + 1);
                queue.offer(node1);
            }
            if (treenode.node.right != null) {
                TreeNodeWithLevel node1 = new TreeNodeWithLevel(treenode.node.right,treenode.level + 1);
                queue.offer(node1);
            }
        }
        return list;
    }

    static class TreeNodeWithLevel {
        TreeNode node;
        int level;
        TreeNodeWithLevel(TreeNode node,int level){
            this.node = node;
            this.level = level;
        }
    }
}
