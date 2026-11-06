class Base {
    int value = 10;
    int getValue() { return value; }
}

class Derived extends Base {
    int value = 20;
    int getValue() { return value; }
}
class Derived1 extends Derived{
    static int value=-121;
    static int val2;
    int getValue(){
        return value;
    }

}

public class Test1{
    public static void main(String[] args) {
        Base obj = new Derived();
        System.out.println(obj.value + " " + obj.getValue());
        Derived obj1=new Derived1();
        System.out.println(obj1.value + " " + obj1.getValue()+" "+Derived1.val2);
        //static or not the parent reference wins
    }
}
