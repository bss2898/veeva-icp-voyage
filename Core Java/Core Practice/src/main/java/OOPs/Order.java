package OOPs;
class Parent1 {
    static { System.out.print("A"); }
    { System.out.print("B"); }
    public Parent1() { System.out.print("C"); }
}
class Child1 extends Parent1 {
    static { System.out.print("D"); }
    { System.out.print("E"); }
    public Child1() { System.out.print("F"); }
}
public class Order{
    public static void main(String[] args) {
        new Child1();
    }
}
