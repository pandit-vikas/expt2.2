import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int id;
    private String designation;
    private double salary;

    public Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id +
               ", Name: " + name +
               ", Designation: " + designation +
               ", Salary: " + salary;
    }
}
