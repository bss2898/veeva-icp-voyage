package OOPs;

import java.util.ArrayList;
import java.util.List;

class Animal{
    String name;
    String foodtype;
}
class Cat extends Animal{
    boolean isPet;
}
class Dog extends Animal{

    String breed;
    public Dog(String a){
        breed=a;
    }

}
class Lion{
    String name;
}
public class VarCovar{
    public static void main(String args[]){
        List<Animal> animals=new ArrayList<>();
        List<Dog> dogs=new ArrayList<>();
        List<Cat> cats=new ArrayList<>();
        List<Lion> lions=new ArrayList<>();
        //List<Animal> lis3=(Animal)lis2;
        List<String> strings = new ArrayList<>();

        //List<Object> objects = strings;
        Object t1=throwObj();
//        System.out.println(t1+" "+ t1 instanceof String);//true
//        System.out.println(t1+" "+ t1 instanceof Object);//true
        List<?> unknown;
        unknown=new ArrayList<>();
        //System.out.println(unknown.add((Object) 10));
        //System.out.println((unknown));

        //List<Dog> dogs = new ArrayList<>();
        //List<Animal> animals = dogs; // ERROR
        List<? extends Animal> animAll=dogs;
        //List<? extends Animal> animCat=lions;
        //Lion not extended Animal
        Dog d1=new Dog("DhruvRathee");
        Dog d2=new Dog("Mamitha");
        Cat c1=new Cat();
        Cat c2=new Cat();
        Lion l1=new Lion();
        Animal a1=new Animal();
        Animal a2=new Animal();
        dogs.add(d1);
        dogs.add(d2);
        List<? extends Animal> unknown1=dogs;
        Animal dofAni1=unknown1.get(0);



    }
    static String throwObj(){
        return "HI";
    }

}
