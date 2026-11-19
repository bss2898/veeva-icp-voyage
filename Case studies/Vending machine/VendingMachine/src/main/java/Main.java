import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);
        Vending one=new Vending();
        System.out.println(one.productMap);
        boolean isWorking=true;
        while (isWorking){
            System.out.println("Enter 1 to select products and purchase");
            System.out.println("Enter 2 to shut down the Machine(password requied)");
            int ch=sc.nextInt();
            switch (ch){
                case 1->one.showMenu();
                case 2 -> {
                    if(validatePass()){
                        System.out.println("'GoodBye!!-Vending machine");
                        isWorking=false;
                    }
                }
                default -> System.out.println("Enter a valid choice:");
            }

        }

    }
    static boolean validatePass(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter password that you only know:");
        String str=sc.next();
        System.out.println("You have accidently entered correct password!");
        return true;

    }
}
