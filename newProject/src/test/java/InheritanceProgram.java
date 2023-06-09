public class InheritanceProgram {
    String firstName="himanshu";
    String lastName="yadav";

}
     class Student extends InheritanceProgram {

        int age=25;
        String schoolName="creative";

        public static void main(String[] args) {
         Student student=new Student();
            System.out.println(student.firstName);
            System.out.println(student.lastName);
            System.out.println(student.age);
            System.out.println(student.schoolName);
        }
}