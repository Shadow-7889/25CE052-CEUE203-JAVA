import java.io.*;
class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    int id;
    String name;
    transient String password;

    Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name
                + ", Password: " + password;
    }
}

public class SerializationDemo {
    public static void main(String[] args) {
        Student[] students = {
            new Student(1, "Mann", "abc123"),
            new Student(2, "Rahul", "xyz456")
        };

        try (ObjectOutputStream out =
                new ObjectOutputStream(
                    new FileOutputStream("students.dat"))) {

            out.writeObject(students);
            System.out.println("Objects saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
            return;
        }

        try (ObjectInputStream in =
                new ObjectInputStream(
                    new FileInputStream("students.dat"))) {

            Student[] loaded = (Student[]) in.readObject();

            System.out.println("\nLoaded objects:");
            for (Student s : loaded) {
                System.out.println(s);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
        System.out.println("25CE052-Mann");
    }
}
