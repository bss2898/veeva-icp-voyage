class FinalClass {}

interface Trial{
    abstract public void show();
}

public class InstanceOF{
    public static void main(String[] args) {
        String s = "Veeva";
        FinalClass obj=new FinalClass();
       //if (s instanceof FinalClass)
            System.out.println("Match");

        if(obj instanceof FinalClass){
            System.out.println("Yess");
        }
        Trial obj1=new Trial(){
            @Override
            public void show(){
                System.out.println("Abstract overriden");
            }
        };
        obj1.show();
        if(obj1 instanceof Trial){
            System.out.println("Interface instance of Trial");
        }
    }
}
