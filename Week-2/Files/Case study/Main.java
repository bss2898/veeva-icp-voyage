import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main{
    static Scanner sc=new Scanner(System.in);
       
    public static void main(String[] args) {
        //System.out.println("Hello, World!");
        //Creating an epty text file first

       /// createFile("students.txt");
        File recordFile=new File("students.txt");
        if(!recordFile.exists()){
            System.out.println("File not found");
            System.exit(0);
        }

        while(true){
            System.out.println("1. Add record ");
            System.out.println("2. Read options ");
            System.out.println("3. Delete record ");
            System.out.println("4. Update record ");
            System.out.println("5. Exit ");
            int ch=sc.nextInt();
            switch(ch){
                case 1:
                    addRecord(recordFile);
                    break;
                case 2:
                    //readRecord(recordFile);
                    break;
                case 3:
                    //deleteRecord();
                    break;
                case 4:
                   // updateRecord();
                    break;
                case 5:
                    System.out.println("Exit");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }

        }
    }
    static void addRecord(File recordFile){
        //To add record to the file
        try(FileWriter fw=new FileWriter(recordFile,true)){
            System.out.println("Enter rollno");
            int rollno=sc.nextInt();
            System.out.println("Enter name");
            String name=sc.next();
            System.out.println("Enter age");
            int age=sc.nextInt();
            System.out.println("Enter marks of 3 subjects");
            int m1=sc.nextInt();
            int m2=sc.nextInt();
            int m3=sc.nextInt();
            char grade;
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
            
            fw.write(rollno+";"+name+";"+age+";"+m1+";"+m2+";"+m3+";"+grade+"\n");
        }
        catch(IOException e){
            System.out.println("Error writing to the file");
            e.printStackTrace();    
        }
        
    }
    static void createFile(String fileName) {
        try{
            File file =new File(fileName);
            if(file.createNewFile()){
                System.out.println("File created: "+file.getName());
            }
            else{
                System.out.println("File already exists");
            }
            
        }
        catch(IOException e){
            System.out.println("Error crating the file");
            e.printStackTrace();
        }
        try(FileWriter fw=new FileWriter(fileName,true)){
            fw.write("rollno;name;age;m1;m2;m3;grade\n");
        }
        catch(IOException e){
            System.out.println("Error writing to the file");
            e.printStackTrace();    

        }
    }
}
