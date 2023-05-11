package JavaDS.LinkedList;

// 1、无头单向非循环链表实现
public class SingleLinkedList {
    public ListNode node;
    public int size;
    

    public SingleLinkedList(){
        node = null;
        size = 0;
    }
    //头插法
    public void addFirst(int data){
        ListNode list = new ListNode((long)data);
        if(size == 0){
            node = list;
        }else{
            list.next = node;
            node = list;
        }
        size++;
    }
    //尾插法
    public void addLast(int data) {
        ListNode list = new ListNode((long)data);
        ListNode list1 = node;
        if(size == 0){
            node = list;
        }else{
            while(list1.next != null){
                list1 = list1.next;
            }
            list1.next = list;
        }
        size++;
    }
    //任意位置插入,第一个数据节点为0号下标
    public boolean addIndex(int index,int data) {
        //首先先判断下标不合法的情况
        //下标不合法的情况分为: index > size ; index < 0;
        if(index > size || index < 0){return false;}
        
        //此时，下标合法，他现在存在三种情况
        //情况一 : index == 0 此时为头插
        if(index == 0){
            addFirst(data);
            return true;
        }
        //情况二 : index == size 此时为尾插
        if(index == size){
            addLast(data);
            return true;
        }
        //情况三 : 0 < index && index < size
        ListNode list1 = node.next;
        for (int i = 0; i < index - 1; i++) {
            node = node.next;
            list1 = list1.next;
        }
        ListNode list = new ListNode((long)data);
        node.next = list;
        list.next = list1;
        size++;
        return true;
    }
    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key) {
        if(size == 0){
            return false;
        }
        if(size == 1) {
            if(node.val.equals((long)key)){
                return true;
            }
            return false;
        }

//        while(node.next != null){
//            if(node.val.equals((long)key)){
//                return true;
//            }
//            node = node.next;
//        }
//        if(node.val == key){
//            return true;
//        }

        while(node != null){
            if(node.val == key){
                return true;
            }
        }
        return false;
    }
    //删除第一次出现关键字为key的节点
    public void remove(int key) {
        if(size == 0){
            return ;
        }
        if(size == 1){
            if(node.val.equals((long)key)){
                node = null;
                size--;
            }else{
                return;
            }
        }
        ListNode list = null;
        ListNode list1 = node;
        while(list1 != null){
            if(node.val.equals((long)key)){
                if(list1.next == null ){//此时说明list1为尾结点
                    list.next = null;
                    size--;
                    return;
                }
                if(list == null ){//此时说明list1为头节点
                    node = node.next;
                    size--;
                    return;
                }
                    list.next = list1.next;
                    list1.next = null;
                    size--;
                    return;
            }
            //此时说明并不相等，则继续循环
            list = list1;
            list1 = list1.next;
        }
    }
    //删除所有值为key的节点
    public void removeAllKey(int key) {
        if(size == 0){
            return ;
        }
        if(size == 1){
            if(node.val.equals((long)key)){
                node = null;
                size--;
            }else{
                return;
            }
        }
        ListNode list = null;
        ListNode list1 = node;
        while(list1 != null){
            if(node.val.equals((long)key)){
                if(list1.next == null ){//此时说明list1为尾结点
                    list.next = null;
                    size--;
                    return;
                }
                if(list == null ){//此时说明list1为头节点
                    node = node.next;
                    size--;
                }
                list.next = list1.next;
                list1 = list1.next;
                size--;
            }
            //此时说明并不相等，则继续循环
            list = list1;
            list1 = list1.next;
        }
    }
    //得到单链表的长度
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "SingleLinkedList{" +
                "node=" + node +
                ", size=" + size +
                '}';
    }

    public void display(){
        System.out.println("SingleLinkedList{" +
                "node=" + node +
                ", size=" + size +
                '}');
    }
    //清除链表
    public void clear(){
        size = 0;
        node = null;
    }

    public void test(){
        if(size < 0){
            throw new RuntimeException("size < 0,不合规");
        }
        if(size == 0 ){
            if(node != null){
                throw new RuntimeException("当 size == 0 的时候，此时node 不为空 ，不合规");
            }
            return;
        }
        if(size >=  1){
            int num = 0;
            for(ListNode list = node;list != null;list = list.next){
                num++;
            }
            if(num != size){
                throw new RuntimeException("size >= 1 时，长度不合规，size ！= 链表的长度");
            }
        }
    }

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addFirst(1);//头插{1,node = null}
//        singleLinkedList.display();
//        singleLinkedList.test();
        singleLinkedList.addLast(2);//尾插{1,node = {2,node = null}}
//        singleLinkedList.test();
//        singleLinkedList.display();
        singleLinkedList.addFirst(3);//头插{3,node = {1 , node = {2,node = null}}}
//        singleLinkedList.display();
//        singleLinkedList.test();

        singleLinkedList.addIndex(4,1);//此时index不合规，无法add
        singleLinkedList.addIndex(1,4);//{3, node = {4 , node = {1 , node = {2, node = null}}}}
//        singleLinkedList.display();
//        singleLinkedList.test();
        singleLinkedList.remove(3);//{4 , node = {1 , node = {2, node = null}}}
        singleLinkedList.addLast(1);//{4,node = {1, node = {2,node = {1,node = null}}}}
//        singleLinkedList.display();
////        singleLinkedList.remove(2);//{4 , node = {1 , node = null}}
//        singleLinkedList.remove(1);//{4 , node = {2, node = null}}
//        singleLinkedList.display();
        singleLinkedList.remove(100);
        singleLinkedList.removeAllKey(1);
        singleLinkedList.test();
    }
}
