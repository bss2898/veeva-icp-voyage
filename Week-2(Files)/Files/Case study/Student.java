
class Student{
    int rollno;
    String name;
    int age;
    int m1,m2,m3;
    char grade;

    Student(int rollno,String name,int age,int m1,int m2,int m3){
        this.rollno=rollno;
        this.name=name;
        this.age=age;
        this.m1=m1;
        this.m2=m2;
        this.m3=m3;
        calculateGrade();
    }

    void calculateGrade(){
        int total=m1+m2+m3;

        if(total>=270){
            grade='A';
        }
        else if(total>=240){
            grade='B';
        }
        else if(total>=210){
            grade='C';
        }
        else{
            grade='D';
        }
    }

    String toFileFormat(){
        return rollno+";"+name+";"+age+";"+m1+";"+m2+";"+m3+";"+grade;
    }

    static Student fromFileFormat(String line){
        String[] parts=line.split(";");

        int rollno=Integer.parseInt(parts[0]);
        String name=parts[1];
        int age=Integer.parseInt(parts[2]);
        int m1=Integer.parseInt(parts[3]);
        int m2=Integer.parseInt(parts[4]);
        int m3=Integer.parseInt(parts[5]);

        return new Student(rollno,name,age,m1,m2,m3);
    }

    void display(){
        System.out.println("Roll No: "+rollno);
        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
        System.out.println("Marks: "+m1+", "+m2+", "+m3);
        System.out.println("Grade: "+grade);
    }
}
