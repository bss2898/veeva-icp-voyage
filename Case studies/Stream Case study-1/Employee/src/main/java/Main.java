import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String args[]){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Aarav Sharma", 26, Employee.Departments.Development));
        employeeList.add(new Employee("Ananya Iyer", 31, Employee.Departments.HR));
        employeeList.add(new Employee("Rohan Verma", 24, Employee.Departments.Testing));
        employeeList.add(new Employee("Diya Joshi", 29, Employee.Departments.Finance));
        employeeList.add(new Employee("Kabir Malhotra", 32, Employee.Departments.Development));
        employeeList.add(new Employee("Ishaan Nair", 35, Employee.Departments.Marketing));
        employeeList.add(new Employee("Meera Kulkarni", 40, Employee.Departments.Testing));

        // Printing the employee details
//        System.out.println("--- Employee Directory ---");
//        for (Employee emp : employeeList) {
//            System.out.println("Name: " + emp.getEmpName() +
//                    " | Age: " + emp.getAge() +
//                    " | Department: " + emp.getDepartment());
//        }


        //duplicate list
        List<Employee> temp=employeeList.stream()
        .collect(Collectors.toList());

       // temp.stream().forEach(System.out::println);

        //Grouping based on age
//        System.out.println("Grouping based on age");
//        Map<Integer,List<Employee>> empByAge=employeeList.
//                stream().
//                collect(Collectors.groupingBy(Employee::getAge));
//
//        empByAge.forEach((age,emps)->{
//            System.out.println("Age "+age+": "+emps);
//        });
//        TreeMap<Integer,List<Employee>> sortByAge=employeeList.stream()
//                .collect(Collectors.groupingBy(
//                   Employee::getAge,
//                   TreeMap::new,
//                   Collectors.toList()
//                ));
//        sortByAge.forEach((age,list)->System.out.println("Age:"+age+":"+list.size()+" employee(s)"));


//    Map<String,List<Employee>> groupByRange=employeeList.stream()
//            .collect(Collectors.groupingBy(
//                    emp->{
//                        int age=emp.getAge();
//                        if(age<=27) return "under 28";
//                        else if(age<=32) return "28-32";
//                        else return "Above 32";
//                    },
//                    TreeMap::new,
//                    Collectors.toList()
//            ));
//
//        groupByRange.forEach((range, list) -> {
//            System.out.println("\nAge Bracket: " + range);
//            list.forEach(e -> System.out.println(" - " + e.getEmpName() + " (Age: " + e.getAge() + ")"));
//        });
//            int bucketSize=5;
//            Map<String,List<Employee>> groupedByRange5=
//                    employeeList.stream()
//                            .collect(Collectors.groupingBy(
//                                    emp->{
//                                        int age=emp.getAge();
//                                        int lb=(age/bucketSize)*bucketSize;
//                                        int ub=lb+bucketSize;
//                                        return lb+"-"+ub;
//                                    },
//                                    TreeMap::new,
//                                    Collectors.toList()
//                            ));
//
//            groupedByRange5.forEach((range,emps)->{
//                System.out.println(range+": "+emps);
//            });
        //int eldest=-1;
//        Optional<Employee> eldestEmp=employeeList.stream()
//                .max(Comparator.comparingInt(Employee::getAge));
//        if (eldestEmp.isPresent()) {
//            Employee eldest = eldestEmp.get();
//            System.out.println("The eldest employee is: " + eldest.getEmpName() +
//                    " (Age: " + eldest.getAge() + ")");
//        } else {
//            System.out.println("The employee list is empty.");
//        }
//        Employee youngest=employeeList.stream()
//                .min(Comparator.comparingInt(Employee::getAge))
//                .orElse(null);
//        if(youngest!=null){
//            System.out.printf("Youngets employee\n"+youngest);
//        }
//        else{
//            System.out.println("No employees are present@@@!");
//        }


    }
}

