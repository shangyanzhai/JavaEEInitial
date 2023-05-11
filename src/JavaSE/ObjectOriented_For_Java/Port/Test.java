package JavaSE.ObjectOriented_For_Java.Port;

public class Test {
        //在IDEA爆红的地方一键修正代码，alt + enter

        /**
         * 调用sort方法时，不知道也不关心具体的子类是什么类型，只要子类实现了Comparable接口
         * 都可以调用sort方法
         * 这就是向上转型的参数统一化
         * @param arr
         */
        public static void sort(Comparable[] arr){
            for (int i = 0; i < arr.length - 1; i++) {
                boolean isSwapped = false;//引入标识位
                for (int j = 0; j < arr.length - i -1; j++) {
                    if(arr[j].compareTo(arr[j + 1]) > 0){
                        Comparable tmp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = tmp;
                        isSwapped = true;
                    }
                }
                if(!isSwapped){
                    break;
                }
            }
        }


        public static void main(String[] age) {
//            Animal animal1 = new Dog();
            //
//            int[] data = new int[]{1,9,5,2,4,7,8,3,6};
//            //调用JDK的默认排序方法，默认升序排序
//            Arrays.sort(data);
//            System.out.println(Arrays.toString(data));
            //
            Student[] stu1 = new Student[3];
            stu1[0] = new Student("小黄",20,01);
            stu1[1] = new Student("小红",21,07);
            stu1[2] = new Student("小文",17,05);
//            Arrays.sort(stu1);
            sort(stu1);
            for(Student stu : stu1){
                stu.printf();
            }
            //不是student对象，两者之间无法比较，抛出异常
            String a = "黄";
            System.out.println(stu1[0].compareTo(a));
            //
        }

}
