public class Employee {
    private String EmpName;
    private int age;
    private Departments department;
    public Employee(String empName, int age,Departments dep) {
        EmpName = empName;
        this.age = age;
        department=dep;

    }
    enum Departments{
        HR,Development,Testing,Finance,Marketing
    }
    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmpName='" + EmpName + '\'' +
                ", age=" + age +
                ", department=" + department +
                '}';
    }
}


