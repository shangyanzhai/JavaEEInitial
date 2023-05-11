package JavaDS.Seek;

public class BSTNode {
    public int val;
    public BSTNode left;
    public BSTNode right;

    public BSTNode(int val){
        this.val = val;
    }

    @Override
    public String toString() {
        return "BSNode{" +
                "val=" + val +
                '}';
    }
}
