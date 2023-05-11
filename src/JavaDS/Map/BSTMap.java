package JavaDS.Map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap {
    private Node root = null;

    // put 方法
    // 1. 添加含义：当 key 不存在 map 中，则把 key，value 添加到搜索树中
    // 2. 更新含义：当 key 已经在 map 中，则更新 key 对应的 value
    // 3. 返回值：当添加时，返回 null；当更新时，返回原来的 value
    public String put(Long key, String value) {
        Node node = new Node(key, value);
        if (root == null) {
            root = node;
            return null;    // 添加
        }

        // 开始查找
        Node cur = root;
        Node parent = null;
        int cmp = -1;
        while (cur != null) {
            cmp = key.compareTo(cur.key);
            if (cmp == 0) {
                // 执行更新操作
                String oldValue = cur.value;
                cur.value = value;

                return oldValue;    // 更新
            } else if (cmp < 0) {   // key < cur.key
                parent = cur;
                cur = cur.left;
            } else {                // key > cur.right
                parent = cur;
                cur = cur.right;
            }
        }

        if (cmp < 0) {
            // key < parent.key
            parent.left = node;
        } else {
            parent.right = node;
        }

        return null;    // 添加
    }

    // get 操作
    // 查找操作，根据 key，找到 key 对应的 value，如果找不到，返回 null
    public String get(Long key) {
        Node cur = root;

        while (cur != null) {
            int cmp = key.compareTo(cur.key);
            if (cmp == 0) {
                return cur.value;
            } else if (cmp < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        return null;
    }

    // 获取所有的 key
    public Set<Long> keySet() {
        Set<Long> keySet = new TreeSet<>();
        // 搜索树情况下，使用中序获取
        Deque<Node> stack = new LinkedList<>();
        Node cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            Node top = stack.pop();
            // 处理 top 就是中序遍历
            keySet.add(top.key);
            cur = top.right;
        }

        return keySet;
    }

    // 索取所有的 value
    public Collection<String> values() {
        List<String> ans = new ArrayList<>();

        // 搜索树情况下，使用中序获取
        Deque<Node> stack = new LinkedList<>();
        Node cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            Node top = stack.pop();
            // 处理 top 就是中序遍历
            ans.add(top.value);
            cur = top.right;
        }

        return ans;
    }

    // 获取所有的 key-value
    // 首先外部是一个 Set，因为 key-value 组合不会重复
    // 表示 key-value 组合使用 Map.Entry
    // Map.Entry<Long, String>: key-value 组合，key 是 Long 类型，value 是 String 类型
    public Set<Map.Entry<Long, String>> entrySet() {
        // 把 Node 作为 TreeSet 的元素类型，问 Node 是否具备大小比较能力
        Set<Map.Entry<Long, String>> ans = new TreeSet<>();

        // 搜索树情况下，使用中序获取
        Deque<Node> stack = new LinkedList<>();
        Node cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            Node top = stack.pop();
            // 处理 top 就是中序遍历
            ans.add(top);
            cur = top.right;
        }

        return ans;
    }
}
