
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main1{
    static Scanner sc=new Scanner(System.in);

    public static void main(String[] args){
        File file=new File("students.txt");

        try{
            if(file.createNewFile()){
                System.out.println("File created");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        while(true){
            System.out.println("\n1.Add Student");
            System.out.println("2.Read All Students");
            System.out.println("3.Search Student");
            System.out.println("4.Update Student");
            System.out.println("5.Delete Student");
            System.out.println("6.Exit");

            int ch=sc.nextInt();

            switch(ch){
                case 1->addStudent(file);
                case 2->readStudents(file);
                case 3->searchStudent(file);
                case 4->updateStudent(file);
                case 5->deleteStudent(file);
                case 6->System.exit(0);
                default->System.out.println("Invalid choice");
            }
        }
    }

    static void addStudent(File file){
        System.out.println("Enter roll no");
        int rollno=sc.nextInt();

        System.out.println("Enter name");
        String name=sc.next();

        System.out.println("Enter age");
        int age=sc.nextInt();

        System.out.println("Enter marks");
        int m1=sc.nextInt();
        int m2=sc.nextInt();
        int m3=sc.nextInt();

        Student s=new Student(rollno,name,age,m1,m2,m3);

        try(FileWriter fw=new FileWriter(file,true)){
            fw.write(s.toFileFormat()+"\n");
            System.out.println("Student added successfully");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    static ArrayList<Student> loadStudents(File file){
        ArrayList<Student> students=new ArrayList<>();

        try(Scanner fs=new Scanner(file)){
            while(fs.hasNextLine()){
                String line=fs.nextLine();

                if(!line.isBlank()){
                    students.add(Student.fromFileFormat(line));
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return students;
    }

    static void readStudents(File file){
        ArrayList<Student> students=loadStudents(file);

        for(Student s:students){
            s.display();
            System.out.println("----------------");
        }
    }

    static void searchStudent(File file){
        System.out.println("Enter roll no");
        int rollno=sc.nextInt();

        ArrayList<Student> students=loadStudents(file);

        for(Student s:students){
            if(s.rollno==rollno){
                s.display();
                return;
            }
        }

        System.out.println("Student not found");
    }

    static void updateStudent(File file){
        System.out.println("Enter roll no to update");
        int rollno=sc.nextInt();

        ArrayList<Student> students=loadStudents(file);
        boolean found=false;

        for(int i=0;i<students.size();i++){
            Student s=students.get(i);

            if(s.rollno==rollno){
                System.out.println("Enter new name");
                String name=sc.next();

                System.out.println("Enter new age");
                int age=sc.nextInt();

                System.out.println("Enter new marks");
                int m1=sc.nextInt();
                int m2=sc.nextInt();
                int m3=sc.nextInt();

                students.set(i,new Student(rollno,name,age,m1,m2,m3));
                found=true;
                break;
            }
        }

        if(found){
            saveStudents(file,students);
            System.out.println("Student updated");
        }
        else{
            System.out.println("Student not found");
        }
    }

    static void deleteStudent(File file){
        System.out.println("Enter roll no to delete");
        int rollno=sc.nextInt();

        ArrayList<Student> students=loadStudents(file);

        boolean removed=students.removeIf(s->s.rollno==rollno);

        if(removed){
            saveStudents(file,students);
            System.out.println("Student deleted");
        }
        else{
            System.out.println("Student not found");
        }
    }

    static void saveStudents(File file,ArrayList<Student> students){
        try(FileWriter fw=new FileWriter(file)){
            for(Student s:students){
                fw.write(s.toFileFormat()+"\n");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
