package JavaSE.ObjectOriented_For_Java.Port;

/**
 * 继承 接口Comparable和 接口Cloneable
 *      具备比较大小的能力  标记接口
 */

/**
 * //Student类实现了Comparable接口之后，就具备了可比较的能力
 * //根据compareTo方法的返回值，JVM就知道哪个Student对象谁大谁小了
 * //当一个类实现了Cloneable接口，这个类就具备了可"克隆"的能力，克隆值得是对象b是根据对象a"复制"而来的，这个复制不是简单的创建两个引用指向同一个对象
 * //确实产生了两个独立的对象，对象b的所有属性和行为都是完全从对象a复制而来，换句话说，对象a和对象b除了地址不同，其他都相同，这样的操作称之为"克隆"
 * //实现了Cloneable接口后，啥方法也没有覆写，不报错，没有任何抽象方法的接口称之为标记接口，
 * //不可省略，JVM只会识别所有带这个Cloneable接口的子类，打上可复制的标记
 */
public class Student implements Comparable,Cloneable{
    private String name;
    private int year;
    private int no;

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getNo() {
        return no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Student() {
        System.out.println("构造方法中");
    }

    public Student(String name, int year, int no) {
        this.name = name;
        this.year = year;
        this.no = no;
    }

    {
        System.out.println("代码块");
    }
    static{
        System.out.println("静态代码块");
    }
    public void print(){
        System.out.println("name : " + name + "， year : " + year + "， no : " + no);
    }

    public void printf(){
        System.out.println(this.name + ": " + this.year + ": " + this.no);
    }
    public static void main(String[] args) {
//        Student std1 = new Student();
//        std1.name = "小黄";
//        std1.no = 1;
//        std1.year = 20;
//        std1.print();
        //
        Student[] stu1 = new Student[3];
        stu1[0] = new Student("小黄",20,01);
        stu1[1] = new Student("小红",21,07);
        stu1[2] = new Student("小文",17,05);
        //比较，覆写父类object中toString方法
        System.out.println(stu1[0]);
        //
        //比较两个引用数据类型
        int a1 = 10;
        int a2 = 10;
        System.out.println(a1 == a2);
        Student[] stu2 = new Student[2];
        stu2[0] = new Student("小黄",20,01);
        stu2[1] = new Student("小黄",20,01);
        System.out.println(stu2[0] == stu2[1]);
        System.out.println(stu2[1].equals(stu2[0]));//实际上还是false，如果要进行同一个类的不同对象的比较相等，需要覆写equals方法
        //重写后，则为true（判断条件是全部相等）
    }
    @Override
    public int compareTo(Object o) {//Object类除了可以接受所有类的对象以外（object是java中所有类的父类）
        // （之所以要设立一个所有类的父类是为了参数统一化），object类具有参数的最高统一化
        //object类还可以接受所有的引用数据类型（接口和数组）
        if(o instanceof Student){//向下转型，还原o的Student的应用
            //因为此时需要比较当前对象this和输入时对象o的year谁大谁小
            Student stu = (Student) o ;
            if(this.year > stu.getYear()){
                return 1;
            }else if(this.year < stu.getYear()){
                return -1;
            }else{
                return 0;
            }
        }
        System.err.println("不是Student对象，无法进行大小比较");//System.err代表错误输出
        return 0;
    }

    public Student clone(){
        Student stu = null;
        try{
            //Object是所有类的父类
            //Object的clone方法，所有对象都有，但是只有实现了Cloneable接口的子类才能使用
            stu = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return stu;
    }
    //覆写object类中的toString方法
    //1.
//    @Override
//    public JavaSE.String toString(){
//        return "name : " + name + " year : " + year + " no : " + no;
//    }
    //2.idea 自动覆写 alt + insert/enter
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", no=" + no +
                '}';
    }
    //1.idea自动覆写equals方法
    //输入equals即可
//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }
    //2.需要自身掌握覆写方法
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        //向下转型，如果这个类不是student类，则不具备可比性
        if(!(obj instanceof Student)){
            return false;
        }
        //走到此可以明确知道，该对象是Student类，且两者之间不是同一对象
        Student stu = (Student) obj;
        return this.name.equals(stu.getName()) && (this.year == stu.getYear()) && (this.no == stu.getNo());
    }
}
