package JavaDS.LinkedList;

// 不带头（工具结点）
// 不循环
// 双向

// if size == 0 then  head == null &&  last == null
// if size == 1 then  head != null &&  head == last
// if size >= 1 then  head.prev == null &&  last.next == null
// if size > 1 除了 head 和 last 之外  任意 node  满足node.prev.next == node && node.next.prev == node
//                 head.next.prev == head    last.prev.next == last
// 无论合适 size == 遍历得到的链表结点个数

// 备注：这个链表类，不是结点类
public class MyLinkedList {
    private ListNode head;
    private ListNode last;
    private int size;

    public MyLinkedList() {
        head = null;
        last = null;
        size = 0;
    }

    // 尾插
    public boolean add(Long e) {
        ListNode node = new ListNode(e);

        if (size == 0) {
            head = node;
            last = node;
            size = size + 1;
        } else {
            // 把新的结点尾插到链表上，记得双向链表
            last.next = node;
            node.prev = last;
            // 更新尾结点
            last = node;
            size = size + 1;
        }

        return true;
    }

    public void add(int index, Long e) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        ListNode node = new ListNode(e);

        // 下标一定合法
        if (size == 0) {
            head = node;
            last = node;
            size = size + 1;
            return;
        }

        if (size == 1) {
            if (index == 0) {
                // 双向链表
                node.next = head;
                head.prev = node;
                // 更新头结点
                head = node;
            } else {
                // 把新的结点尾插到链表上，记得双向链表
                node.prev = last;
                last.next = node;
                // 更新尾结点
                last = node;
            }
            size = size + 1;
            return;
        }

        // size > 1
        if (index == 0) {
            // 双向链表
            node.next = head;
            head.prev = node;
            // 更新头结点
            head = node;
            size = size + 1;
        } else if (index == size) {
            // 把新的结点尾插到链表上，记得双向链表
            node.prev = last;
            last.next = node;
            // 更新尾结点
            last = node;
            size = size + 1;
        } else {
            // size > 1 && index > 0 && index < size - 1
            ListNode cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;     // 需要考虑 cur == null 的情况么？
            }
            ListNode prev = cur.prev;
            // 把 node 插入到 prev 和 cur 之间
            node.prev = prev;
            node.next = cur;
            prev.next = node;
            cur.prev = node;

            size = size + 1;
        }
    }

    public Long remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (index == 0) {
            Long e = head.val;
            head = head.next;
            if (size > 1) {
                head.prev = null;
            } else {
                // size == 1
                last = null;
            }

            size = size - 1;
            return e;
        } else if (index == size - 1) {
            // size 为 1 的情况是否需要考虑？  不需要，因为 size 为 1 时，index 必然是 0
            // 所以此处只需要考虑 size > 1

            ListNode prev = last.prev;      // 倒数第二个结点
            Long e = last.val;
            prev.next = null;
            last = prev;

            size = size - 1;
            return e;
        } else {
            // index 是中间的
            // 先找到要删除的结点
            ListNode cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }

            ListNode prev = cur.prev;
            ListNode next = cur.next;

            prev.next = next;
            next.prev = prev;
            size = size - 1;

            return cur.val;
        }
    }

    public boolean remove(Long e) {
        ListNode cur = head;
        for (int i = 0; i < size; i++) {
            if (cur.val.equals(e)) {
                // 需要删除 cur
                if (i == 0) {
                    head = head.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        last = null;
                    }
                    size = size - 1;
                    return true;
                }

                if (i == size - 1) {
                    last = last.prev;
                    if (last != null) {
                        last.next = null;
                    } else {
                        head = null;
                    }
                    size = size - 1;
                    return true;
                }

                ListNode prev = cur.prev;
                ListNode next = cur.next;
                prev.next = next;
                next.prev = prev;
                size = size - 1;

                return true;
            }

            cur = cur.next;
        }

        return false;
    }

    public Long get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.val;
    }

    public Long set(int index, Long e) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        Long o = cur.val;
        cur.val = e;
        return o;
    }

    public int indexOf(Long e) {
        ListNode cur = head;
        for (int i = 0; i < size; i++) {
            if (cur.val.equals(e)) {
                return i;
            }
            cur = cur.next;
        }

        return -1;
    }

    public int lastIndexOf(Long e) {
        ListNode cur = last;
        for (int i = size - 1; i >= 0; i--) {
            if (cur.val.equals(e)) {
                return i;
            }

            cur = cur.prev;
        }

        return -1;
    }

    public boolean contains(Long e) {
        return indexOf(e) != -1;
    }

    public void clear() {
        size = 0;
        head = null;
        last = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 从这里往下的代码，大家能大概理解即可，不需要会写
    public void test() {
        if (size < 0) {
            throw new RuntimeException("size 小于 0 了");
        } else if (size == 0) {
            if (head != null) {
                throw new RuntimeException("size 为 0 时，head 不为 null");
            }

            if (last != null) {
                throw new RuntimeException("size 为 0 时，last 不为 null");
            }

            return;
        } else if (size == 1) {
            if (head == null) {
                throw new RuntimeException("size 为 1 时，head 为 null");
            }

            if (last != head) {
                throw new RuntimeException("size 为 1 时，head 和 last 不相等");
            }
        }


        if (head.prev != null) {
            throw new RuntimeException("head 的 prev 不为 null");
        }

        if (last.next != null) {
            throw new RuntimeException("last 的 next 不为 null");
        }

        // 判断除了 head 和 last 之外所有的结点 node.prev.next == node   node.next.prev == node
        for (ListNode cur = head.next; cur != null && cur != last; cur = cur.next) {
            if (cur.prev.next != cur) {
                throw new RuntimeException("cur.prev.next 不等于 cur");
            }

            if (cur.next.prev != cur) {
                throw new RuntimeException("cur.next.prev 不等于 cur");
            }

            int count = 0;
            for (ListNode c = head; c != null; c = c.next) {
                count++;
            }

            if (size != count) {
                throw new RuntimeException("size 不等于遍历链表得到的结点个数");
            }
        }
    }

    public void testElements(long[] elements) {
        if (size != elements.length) {
            throw new RuntimeException("元素个数不符合预期");
        }

        int i = 0;
        ListNode cur = head;

        while (i < elements.length) {
            long e1 = elements[i];
            long e2 = cur.val;
            if (e1 != e2) {
                throw new RuntimeException("元素不匹配: " + i);
            }

            i++;
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        // 测试 remove(index = 0):
        MyLinkedList list = new MyLinkedList();
        list.add(100L);
        list.add(200L);
        list.add(300L);
        list.add(400L);
        list.test();
        list.testElements(new long[] { 100, 200, 300, 400 });

        System.out.println(list.remove(100L));  // true
        list.test();
        list.testElements(new long[] { 200, 300, 400 });

        System.out.println(list.remove(200L));  // true
        list.test();
        list.testElements(new long[] { 300, 400 });

        System.out.println(list.remove(400L));  // true
        list.test();
        list.testElements(new long[] { 300 });

        System.out.println(list.remove(300L));  // true
        list.test();
        list.testElements(new long[] { });

        System.out.println(list.remove(500L));  // false

//        System.out.println(list.remove(2)); // 300
//        list.test();
//        list.testElements(new long[] { 100, 200, 400 });

//        System.out.println(list.remove(0)); // 100
//        System.out.println(list.remove(0)); // 200
//        System.out.println(list.remove(0)); // 300
//        list.test();

//        System.out.println(list.remove(2)); // 300
//        list.test();
//        System.out.println(list.remove(1)); // 200
//        list.test();
//        System.out.println(list.remove(0)); // 100
//        list.test();
        /*

        // size == 0 时的插入
        list = new MyLinkedList();
        list.add(0, 100L);
        list.test();
        list.testElements(new long[] { 100 });

        // size == 1 时 头插 和 尾插
        list.add(0, 200L);
        list.test();
        list.testElements(new long[] { 200, 100 });

        list = new MyLinkedList();
        list.add(100L);
        list.add(1, 200L);
        list.test();
        list.testElements(new long[] { 100, 200 });

        // size > 1 时  头插、尾插，中间插入
        list.add(0, 50L);
        list.test();
        list.testElements(new long[] { 50, 100, 200 });

        list.add(3, 300L);
        list.test();
        list.testElements(new long[] { 50, 100, 200, 300 });

        list.add(2, 1000L);
        list.test();
        list.testElements(new long[] { 50, 100, 1000, 200, 300 });

         */


//        list.test();
//        list.testElements(new long[0]);
//
//        list.add(10L);
//        list.test();
//        list.testElements(new long[]{10});
//
//        list.add(20L);
//        list.test();
//        list.testElements(new long[]{10, 20});
//
//        list.add(30L);
//        list.test();
//        list.testElements(new long[]{10, 20, 30});
    }
}