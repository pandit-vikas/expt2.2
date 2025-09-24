import java.io.*;

// Student class must implement Serializable
class Student implements Serializable {
    private static final long serialVersionUID = 1L; // for serialization compatibility
    int id;
    String name;
    double gpa;

    // Constructor
    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    // Display student details
    public void display() {
        System.out.println("Student ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("GPA: " + gpa);
    }
}

public class StudentSerialization {
    public static void main(String[] args) {
        String filename = "student.ser";

        // Create a student object
        Student student = new Student(101, "Alice", 3.8);

        // Serialization
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(student);
            System.out.println("Student object has been serialized to " + filename);

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException e) {
            System.out.println("Error during serialization: " + e.getMessage());
        }

        // Deserialization
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            Student deserializedStudent = (Student) in.readObject();
            System.out.println("\nStudent object has been deserialized:");
            deserializedStudent.display();

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found during deserialization.");
        } catch (IOException e) {
            System.out.println("Error during deserialization: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Student class not found.");
        }
    }
}
