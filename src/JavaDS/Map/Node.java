package JavaDS.Map;

import java.util.Map;

// key 的类型是使用 Long 类型
// value 的类型使用 String 类型
// 1. key 的类型比较具备比较能力
// 2. value 的类型不做要求
public class Node implements Map.Entry<Long, String>, Comparable<Node> {
    public Long key;
    public String value;
    public Node left;
    public Node right;

    public Node(Long key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Long getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String setValue(String value) {
        String oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    @Override
    public int compareTo(Node o) {
        return this.key.compareTo(o.key);
    }
}
