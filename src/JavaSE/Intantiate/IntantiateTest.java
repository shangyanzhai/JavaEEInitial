package JavaSE.ObjectOriented_For_Java.Intantiate;

class HelloA{
    public HelloA(){
        System.out.println("I am A class ");
    }

    static
    {
        System.out.println("static A");
    }
}
class HelloB{
    HelloA A = new HelloA();
    public HelloB()
    {
        System.out.println("I am B class ");
    }

    static
    {
        System.out.println("static B");
    }
}


public class IntantiateTest extends HelloB{
    HelloA A = new HelloA();
    public IntantiateTest()
    {
        System.out.println("I am C class ");
    }

    static
    {
        System.out.println("static C");
    }

    public static void main(String[] args) {
        new IntantiateTest();
    }
}