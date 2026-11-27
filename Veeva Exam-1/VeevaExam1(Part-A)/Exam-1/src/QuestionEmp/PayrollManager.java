package QuestionEmp;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class PayrollManager {
    public static void main(String[] args) {
        List<Employee> employeeList=new ArrayList<>(
                Arrays.asList(
                        new ContractEmployee(101,"A","HR",3000,2),
                        new FullTimeEmployee(102,"B","HR",10000,3000),
                        new ContractEmployee(103,"C","IT",4000,5),
                        new FullTimeEmployee(104,"D","IT",20000,6000),
                        new ContractEmployee(105,"E","DEV",9000,6),
                        new FullTimeEmployee(106,"F","HR",20000,4000)
                )
        );
        //employeeList.forEach(System.out::println);
        //First each employee salry
        Map<String,Double> eachSalary = employeeList.stream()
                .collect(
                        Collectors.toMap(
                                Employee::getName,
                                Employee::calculateSalary,
                                (prev,curr)->prev
                        )
                );
        System.out.println(eachSalary);
        //Map<String, Double> getDepartmentWiseTotalSalary(): Returns a map of each department
        // to its total salary payout.
        Map<String,Double> deptSalary =employeeList.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,//keymapper
                                HashMap::new,//map factOry
                                Collectors.summingDouble(Employee::calculateSalary)//downstream collector
                        )
                );

        System.out.println("Department wise salary total:");
        for(String dept:deptSalary.keySet()){
            System.out.println(dept+":"+deptSalary.get(dept));
        }

        //Greater than threshold
        double thres=19000;
        List<String> list2=employeeList.stream().
                    filter(
                        employee -> {
                           return employee.calculateSalary()>thres?true:false;
                        }
                    )
                .map(Employee::getName)
                .sorted()
                .toList();

        System.out.println(list2);

    }
}
