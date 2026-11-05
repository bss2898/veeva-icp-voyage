import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main{
    static Scanner sc=new Scanner(System.in);
       
    public static void main(String[] args) {
        //System.out.println("Hello, World!");
        //Creating an epty text file first

       createFile("students.txt");
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
            System.out.println("5.Rename the file");
            System.out.println("6. Exit ");
            int ch=sc.nextInt();
            switch(ch){
                case 1:
                    addRecord(recordFile);
                    break;
                case 2:
                    readMenu(recordFile);
                    break;
                case 3:
                    deleteMenu(recordFile);
                    break;
                case 4:
                    updateMenu(recordFile);
                    break;
                case 5:
                    System.out.println("Exit");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }

        }
    }
    static void updateMenu(File recordFile){
        System.out.println("1.Update record by roll no.\n2.Update record on name\n.3.exit");
        int ch=sc.nextInt();
        switch(ch){
            case 1->updateRoll(recordFile);
            case 2->updateName(recordFile);
            default->{
                System.out.println("Invalid choice");
            }
        }

    }
    
static void updateRoll(File recordFile){
    System.out.println("Enter roll no to update");
    int rollno=sc.nextInt();

    System.out.println("Enter new name");
    String name=sc.next();

    System.out.println("Enter new age");
    int age=sc.nextInt();

    System.out.println("Enter new marks of 3 subjects");
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

    try{
        StringBuilder sb=new StringBuilder();
        boolean found=false;

        try(Scanner fileScanner=new Scanner(recordFile)){
            while(fileScanner.hasNextLine()){
                String line=fileScanner.nextLine();
                String[] parts=line.split(";");

                if(parts.length>=7 && parts[0].equals(String.valueOf(rollno))){
                    sb.append(rollno).append(";")
                      .append(name).append(";")
                      .append(age).append(";")
                      .append(m1).append(";")
                      .append(m2).append(";")
                      .append(m3).append(";")
                      .append(grade).append("\n");

                    found=true;
                }
                else{
                    sb.append(line).append("\n");
                }
            }
        }

        if(found){
            try(FileWriter fw=new FileWriter(recordFile)){
                fw.write(sb.toString());
            }
            System.out.println("Record updated successfully");
        }
        else{
            System.out.println("Roll number not found");
        }
    }
    catch(IOException e){
        e.printStackTrace();
    }
}

    
static void updateName(File recordFile){
    System.out.println("Enter name to update");
    String oldName=sc.next();

    System.out.println("Enter new roll no");
    int rollno=sc.nextInt();

    System.out.println("Enter new name");
    String name=sc.next();

    System.out.println("Enter new age");
    int age=sc.nextInt();

    System.out.println("Enter new marks of 3 subjects");
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

    try{
        StringBuilder sb=new StringBuilder();
        boolean found=false;

        try(Scanner fileScanner=new Scanner(recordFile)){
            while(fileScanner.hasNextLine()){
                String line=fileScanner.nextLine();
                String[] parts=line.split(";");

                if(parts.length>=7 && parts[1].equals(oldName)){
                    sb.append(rollno).append(";")
                      .append(name).append(";")
                      .append(age).append(";")
                      .append(m1).append(";")
                      .append(m2).append(";")
                      .append(m3).append(";")
                      .append(grade).append("\n");

                    found=true;
                }
                else{
                    sb.append(line).append("\n");
                }
            }
        }

        if(found){
            try(FileWriter fw=new FileWriter(recordFile)){
                fw.write(sb.toString());
            }
            System.out.println("Record updated successfully");
        }
        else{
            System.out.println("Name not found");
        }
    }
    catch(IOException e){
        e.printStackTrace();
    }
}

    static void deleteMenu(File recordFile){
        System.out.println("1.Delete all records\n2.Delete record by roll no.\n3.Delete record on name\n.4.exit");
        int ch=sc.nextInt();
        switch(ch){
            case 1->deleteAll(recordFile);
            case 2->deleteRoll(recordFile);
            case 3->deleteName(recordFile);
            default->{
                System.out.println("Invalid choice");
            }
        }

    }
    static void deleteRoll(File recordFile){
        System.out.println("Enter roll no");
        int rollno=sc.nextInt();
        try(Scanner sc=new Scanner(recordFile)){
            StringBuilder sb=new StringBuilder();
            while(sc.hasNextLine()){
                String line=sc.nextLine();
                String[] parts=line.split(";");
                if(parts.length>=1 && !parts[0].equals(String.valueOf(rollno))){
                    sb.append(line).append("\n");
                }
            }
            try(FileWriter fw=new FileWriter(recordFile)){
                fw.write(sb.toString());
            }
        }
        catch(IOException e){
            System.out.println("Error reading the file");
            e.printStackTrace();
        }
    }
    static void deleteAll(File recordFile){
        try(FileWriter fw=new FileWriter(recordFile)){
            fw.write("rollno;name;age;m1;m2;m3;grade\n");
        }   
        
        catch(IOException e){
            System.out.println("Error writing to the file");
            e.printStackTrace();    
        }       
    }
    static void deleteName(File recordFile){
        System.out.println("Enter name");
        String name=sc.next();
        try(Scanner sc=new Scanner(recordFile)){
            StringBuilder sb=new StringBuilder();
            while(sc.hasNextLine()){
                String line=sc.nextLine();
                String[] parts=line.split(";");
                if(parts.length>=2&& !parts[1].equals(name)){
                    sb.append(line).append("\n");
                }

            }
            try(FileWriter fw=new FileWriter(recordFile)){
                fw.write(sb.toString());
            }
        }
        catch(IOException e){
            System.out.println("Error reading the file");
            e.printStackTrace();
        }
    }

    static void readMenu(File recordFile){
        System.out.println("1.Read all records\n2.Read record by roll no.\n3.Read record on name\n.4.exit");
        int ch=sc.nextInt();
        switch(ch){
            case 1->readAll(recordFile);
            case 2->readRoll(recordFile);
            case 3->readName(recordFile);
            default->{
                System.out.println("Invalid choice");
            }
        }

    }
    static void readName(File recordFile){
        System.out.println("Enter name");
        String name=sc.next();
        try(Scanner sc=new Scanner(recordFile)){
            while(sc.hasNextLine()){
                String line=sc.nextLine();
                String[] parts=line.split(";");
                if(parts.length>=2&& parts[1].equals(name)){
                    System.out.println(line);
                    break;
                }

            }
        }
        catch(IOException e){
            System.out.println("Error reading the file");
            e.printStackTrace();
        }
    }
    static void readRoll(File recordFile){
        System.out.println("Enter roll no");
        int rollno=sc.nextInt();
        try(Scanner sc=new Scanner(recordFile)){
            while(sc.hasNextLine()){
                String line=sc.nextLine();
                String[] parts=line.split(";");
                if(parts.length>=1 && parts[0].equals(String.valueOf(rollno))){
                    System.out.println(line);
                    break;
                }
            }
        }
        catch(IOException e){
            System.out.println("Error reading the file");
            e.printStackTrace();
        }
    }
            


    static void readAll(File recordFile){
        System.out.println("Reading all records");  
       // try(FileReader fr=new FileReader(recordFile)){
            // while(true){
            //     int i=fr.read();
            //     if(i==-1){
            //         break;
            //     }
            //     System.out.print((char)i);
            // }
            // gmore ways to read the file
            try(Scanner sc=new Scanner(recordFile)){
                while(sc.hasNextLine()){
                    String line=sc.nextLine();
                    System.out.println(line);
                }
            }
            catch(IOException e){
                System.out.println("Error reading the file");
                e.printStackTrace();
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
