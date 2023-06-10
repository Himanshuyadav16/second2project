public class EmpolyeeHas{
    int id;
    String name;
    AddressHas addressHas;
public EmpolyeeHas(int id,String name,AddressHas addressHas)
{
    this.name=name;
    this.id=id;
    this.addressHas=addressHas;
}

void display(){
    System.out.println(id+" "+name+" "+addressHas.city+" "+addressHas.state);
}

    public static void main(String[] args) {
        AddressHas address = new AddressHas("M.P.", "Bhopal");
        EmpolyeeHas empolyeeHas = new EmpolyeeHas(23, "himanshu", address);
        empolyeeHas.display();

    }

}
