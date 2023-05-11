package JavaDS.ArrayList;

/**
 * 自定义的线性表（以接口的形式体现）
 * 1.元素类型 Long
 *  1)int 下标  long 元素
 *  2）包装类：体现对象的形式而不是基本类型
 * 2.遵守线性表的相关规定
 * 3.教学过程中的体现（仅是教学过程中体现，线性表中无相关规定）
 *  有元素的位置： != null
 *  没有元素的位置： == null
 */
public interface MyList {
    /**
     * 返回线性表中的元素个数
     * @return
     */
    int size();
    /**
     * 将 e 尾插到线性表中，一定返回true
     * @param e
     * @return
     */
    boolean add(Long e);

    /**
     * 将 e 插入线性表的 index位置 ，从[index , size() )向后移
     * index 的合法下标 [0 , size() ]
     * 如果下标不合法，抛出一个 ArrayIndexOutOfBoundsException
     * @param index
     * @param e
     */
    void add(int index,Long e);

    /**
     * 删除 index 位置的元素
     * index 的合法下标 [0 , size() )
     * 如果下标不合法，抛出一个 ArrayIndexOutOfBoundsException
     * @param index
     * @return 从线性表中删除的元素
     */
    Long remove(int index);

    /**
     * 从前到后，删除第一个遇到的 e (equals() == true)
     * @param e
     * @return 删除成功，返回true ，删除失败(没有该元素)，返回 false
     */
    boolean remove(Long e);

    /**
     * 直接返回 index 位置的元素
     * index 的合法下标 [0 , size() )
     * @param index
     * @return
     */
    Long get(int index);

    /**
     * 使用 e 替换index 位置的元素
     * index 的合法下标 [0 , size() )
     * @param index
     * @param e
     * @return 原来 index 位置的元素
     */
    Long set(int index,Long e);

    /**
     * 返回第一次遇到 e 的下标 (equals() == true)
     * @param e
     * @return 如果没有遇到 ，返回 -1
     */
    int indexOf(Long e);

    /**
     * 从后往前 ，返回第一次遇到 e 的下标 (equals() == true)
     * @param e
     * @return 如果没有遇到 ，返回 -1
     */
    int lastIndexOf(Long e);

    /**
     * 线性表中是否包含 e (equals() == true)
     * @param e
     * @return
     */
    boolean contains(Long e);

    /**
     * 清空线性表
     */
    void clear();

    /**
     * 判断线性表是否是空的（empty） 等价于一个元素都没有
     * @return
     */
    boolean isEmpty();
}
