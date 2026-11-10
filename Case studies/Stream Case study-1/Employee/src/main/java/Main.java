import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Aarav Sharma", 26, Employee.Departments.Development));
        employeeList.add(new Employee("Ananya Iyer", 31, Employee.Departments.HR));
        employeeList.add(new Employee("Rohan Verma", 24, Employee.Departments.Testing));
        employeeList.add(new Employee("Diya Joshi", 29, Employee.Departments.Finance));
        employeeList.add(new Employee("Kabir Malhotra", 35, Employee.Departments.Development));
        employeeList.add(new Employee("Ishaan Nair", 28, Employee.Departments.Marketing));
        employeeList.add(new Employee("Meera Kulkarni", 33, Employee.Departments.Testing));

        // Printing the employee details
        System.out.println("--- Employee Directory ---");
        for (Employee emp : employeeList) {
            System.out.println("Name: " + emp.getEmpName() +
                    " | Age: " + emp.getAge() +
                    " | Department: " + emp.getDepartment());
        }
        

        //Grouping based on age

    }
}
