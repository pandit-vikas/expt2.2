//Create a menu-based Java application with the following options. 1.Add an Employee 2. Display All 3. Exit If option 1 is selected, the application should gather details of the employee like employee name, employee id, designation and salary and store it in a file. If option 2 is selected, the application should display all the employee details. If option 3 is selected the application should exit
import java.io.*;
import java.util.*;

public class EmployeeManagement {
    private static final String FILE_NAME = "employees.dat";
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n==== Employee Management System ====");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addEmployee(sc);
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 3);

        sc.close();
    }

    private static void addEmployee(Scanner sc) {
        try {
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline

            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Designation: ");
            String designation = sc.nextLine();

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            Employee emp = new Employee(name, id, designation, salary);

            // Append employee to file
            FileOutputStream fos = new FileOutputStream(FILE_NAME, true);
            ObjectOutputStream oos;
            if (new File(FILE_NAME).length() == 0) {
                oos = new ObjectOutputStream(fos);
            } else {
                oos = new AppendableObjectOutputStream(fos);
            }
            oos.writeObject(emp);
            oos.close();

            System.out.println("Employee added successfully!");

        } catch (IOException e) {
            System.out.println("Error saving employee: " + e.getMessage());
        }
    }

    private static void displayEmployees() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists() || file.length() == 0) {
                System.out.println("No employees found.");
                return;
            }

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.println("\n--- Employee Details ---");
            while (true) {
                try {
                    Employee emp = (Employee) ois.readObject();
                    System.out.println(emp);
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading employees: " + e.getMessage());
        }
    }
}

// Helper class to append objects to existing file
class AppendableObjectOutputStream extends ObjectOutputStream {
    public AppendableObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        reset(); // Avoid writing a new header
    }
}
