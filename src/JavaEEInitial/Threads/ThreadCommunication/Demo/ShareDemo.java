package JavaEEInitial.Threads.ThreadCommunication.Demo;

public class ShareDemo {
//    private static int a = 0;//静态属性位于方法区
//
//    static class SubThread extends Thread {
//        @Override
//        public void run() {
//            a = 100;
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        System.out.println(a);
//
//        SubThread t = new SubThread();
//        t.start();//在子线程中修改
//        t.join();
//
//        System.out.println(a);//反应到主线程中
//    }


    static class Person {
        int id;
        String name;

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static class SubThread extends Thread {
        private final Person person;

        SubThread(Person person) {
            this.person = person;
            //对比
//            this.person = new Person();//此时则不修改
        }

        @Override
        public void run() {
            person.id = 100;
            person.name = "子线程";
        }
    }

    // 对象中的属性是可以共享的，前提是同一份对象
    public static void main(String[] args) throws InterruptedException {
        Person person = new Person();
        person.id = 0;
        person.name = "主线程";

        System.out.println(person);

        SubThread t = new SubThread(person);
        t.start();
        t.join();

        System.out.println(person);
    }

    /**
     * 局部变量,此时不会同步
     */

//    static class SubThread extends Thread {
//        @Override
//        public void run() {
//            int a = 100;
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        int a = 0;
//
//        System.out.println(a);
//        SubThread t = new SubThread();
//        t.start();
//        t.join();
//
//        System.out.println(a);
//    }
}
