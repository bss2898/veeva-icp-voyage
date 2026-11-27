package QuestionEmp;
/*
Create an abstract class Employee with private fields id (int), name (String), and department (String).
Include a constructor, getters, and an abstract method double calculateSalary().
Create two subclasses:
FullTimeEmployee: Adds baseSalary (double) and bonus (double). calculateSalary() returns baseSalary + bonus.
ContractEmployee: Adds hourlyRate (double) and hoursWorked (int). calculateSalary() returns hourlyRate * hoursWorked.
*/
public abstract class Employee {
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public abstract double calculateSalary();
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}

class FullTimeEmployee extends Employee{
    double base;
    double bonus;
    public FullTimeEmployee(int id, String name, String department,double base,double bonus) {
        super(id, name, department);
        this.base=base;
        this.bonus=bonus;
    }
    @Override
    public double calculateSalary() {
        return base+bonus;
    }


}
class ContractEmployee extends Employee
{
    double hourly;
    int hours;

    public ContractEmployee(int id, String name, String department,double hourly,int hours) {
        super(id, name, department);
        this.hourly=hourly;
        this.hours=hours;
    }

    @Override
    public double calculateSalary() {
        return hourly*hours;
    }

}

