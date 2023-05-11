package JavaDS.Seek;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BSTree {
    private BSTNode root = null;

    public boolean add(int val) {
        BSTNode node = new BSTNode(val);
        if (root == null) {
            root = node;
        }
        BSTNode parent = null;
        BSTNode cur = root;

        while (cur != null) {
            if (val == cur.val) {
                // 要插入的元素已经在搜索树中了
                // 搜索树中不允许有重复的元素
                // 所以，插入失败，返回 false
                return false;
            } else if (val < cur.val) {
                parent = cur;
                cur = cur.left;
            } else {
                parent = cur;
                cur = cur.right;
            }
        }

        if (val < parent.val) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        return true;
    }

    public boolean contains(int val) {
        // 递归形式
//        return contains递归(root, val);
        // 非递归
        return contains非递归(root, val);
    }

    private void removeNode(BSTNode parent, BSTNode node) {
        if (node.left == null) {
            // 让 node 的右孩子 代替 node 的位置
            // 判断 node 处于什么地位: node 是根、node 是 parent 的左、node 是 parent 的右
            if (parent == null) {
                // node 是 根
                root = node.right;
                return;
            }

            if (parent.left == node) {
                parent.left = node.right;
                return;
            }

            parent.right = node.right;
            return;
        } else if (node.right == null) {
            if (parent == null) {
                root = node.left;
                return;
            }

            if (parent.left == node) {
                parent.left = node.left;
                return;
            }

            parent.right = node.left;
            return;
        }

        // 左右都不为空
        // 找比我小的最大的一个，即左孩子中最右边的结点
        BSTNode rParent = node;
        BSTNode r = node.left;
        while (r.right != null) {
            rParent = r;
            r = r.right;
        }

        node.val = r.val;

        // 删除 r 结点
        // 问 r 是 rParent 的左还是右？
        if (r == rParent.left) {
            rParent.left = r.left;
        } else {
            rParent.right = r.left;
        }
    }

    public boolean remove(int val) {
        // 1. 先查找
        BSTNode parent = null;
        BSTNode cur = root;
        while (cur != null) {
            if (val == cur.val) {
                // 要删除
                removeNode(parent, cur);
                return true;
            } else if (val < cur.val) {
                parent = cur;
                cur = cur.left;
            } else {
                parent = cur;
                cur = cur.right;
            }
        }

        return false;
    }

    private boolean contains非递归(BSTNode root, int val) {
        BSTNode cur = root;

        while (cur != null) {
            if (val == cur.val) {
                return true;
            } else if (val < cur.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        return false;
    }

    private static boolean contains递归(BSTNode node, int val) {
        if (node == null) {
            return false;
        }

        if (node.val == val) {
            return true;
        }

        if (val < node.val) {
            return contains递归(node.left, val);
        } else {
            return contains递归(node.right, val);
        }
    }

    public List<Integer> inorder() {
        List<Integer> ans = new ArrayList<>();
        Deque<BSTNode> stack = new LinkedList<>();
        BSTNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            BSTNode top = stack.pop();
            ans.add(top.val);
            cur = top.right;
        }

        return ans;
    }
}
