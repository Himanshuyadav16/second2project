public class FirstConstructor {
    String name;
    int age;
     FirstConstructor(int personAge, String personName){
       age= personAge;
       name=personName;
         System.out.println(age+" "+name);
    }
    FirstConstructor(){
        System.out.println("hello");
    }
    public void information(String personName,int personAge){
         age=personAge;
         name=personName;
        System.out.println(name+" "+age);
    }
    public void information(){
        System.out.println("Information");
     }

    public static void  main(String[] agr) {
        FirstConstructor secondConstructor = new FirstConstructor();
        FirstConstructor firstConstructor = new FirstConstructor(23, "Ram");
        firstConstructor.information("shyam", 32);
        secondConstructor.information();

    }
}
