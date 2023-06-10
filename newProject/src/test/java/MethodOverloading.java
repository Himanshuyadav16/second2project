public class MethodOverloading {
  static int add(int a,int b,int c){
return a+b+c;
    }

    static double add(double a, double b){
      return a+b;
    }

}
class Testing{
    public static void main(String[] args) {
        System.out.println(MethodOverloading.add(11,12,13));
        System.out.println(MethodOverloading.add(11.2,12.3));
    }

}