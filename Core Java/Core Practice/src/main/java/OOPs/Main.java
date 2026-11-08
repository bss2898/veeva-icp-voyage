class Parent {
    static int x = initialize("Parent static field x");
    int y = initialize("Parent instance field y");

    static {
        System.out.println("Parent static block");
    }

    Parent() {
        System.out.println("Parent constructor");
    }

    static int initialize(String msg) {
        System.out.println(msg);
        return 1;
    }
}

class Child extends Parent {
    static int a = initialize("Child static field a");
    int b = initialize("Child instance field b");

    static {
        System.out.println("Child static block");
    }

    Child() {
        System.out.println("Child constructor");
    }
}

class Outer {
    private int instanceVar = 10;
    private static int staticVar = 2001;

    class StaticNested {
        void display() {
            System.out.println(instanceVar); // Line 6
            System.out.println(staticVar);   // Line 7
        }
    }
    static class StaticNested1 {
        void display() {
           // System.out.println(instanceVar); // Line 6
            System.out.println(staticVar);   // Line 7
        }
    }
}


public class Main {
    public static void main(String[] args) {
        //Child c = new Child();
        Outer obj=new Outer();
        Outer.StaticNested obj3=new Outer().new StaticNested(){
            @Override
            void display(){
                System.out.println("Method overriding");
            }
        };


        obj3.display();
        Outer.StaticNested obj1= obj.new StaticNested();
        Outer.StaticNested1 obj2=new Outer.StaticNested1();
        Outer.StaticNested1 obj4=new Outer.StaticNested1();
        obj2.display();
        //Outer.staticVar=1000;
        obj1.display();
    }

}

