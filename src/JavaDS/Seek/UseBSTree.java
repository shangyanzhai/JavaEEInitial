package JavaDS.Seek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseBSTree {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        System.out.println(list);
    }

    public static void main2(String[] args) {
        BSTree t1 = new BSTree();
        BSTree t2 = new BSTree();
        BSTree t3 = new BSTree();

        {
            t1.add(0);
            t1.add(1);
            t1.add(2);
            t1.add(3);
            t1.add(4);
        }

        {
            t2.add(4);
            t2.add(3);
            t2.add(2);
            t2.add(1);
            t2.add(0);
        }

        {
            t3.add(3);
            t3.add(1);
            t3.add(4);
            t3.add(2);
            t3.add(0);
        }
    }

    public static void main1(String[] args) {
        BSTree tree = new BSTree(); // 定义搜索树对象
        tree.add(0);
        tree.add(7);
        tree.add(2);
        tree.add(19);
        tree.add(21);
        tree.add(9);
        tree.add(7);
        tree.add(3);

        System.out.println(tree.inorder());
    }
}
