package ro.ubb.studentlabapp.Domain;

import java.util.UUID;

/**
 * This class represents a student with a unique ID, first name, last name, and email.
 */
public class Student {
    private final UUID id;
    private String firstName;
    private String lastName;
    private String email;

//    /**
//     * Default constructor to create an empty Student.
//     * The UUID will be generated automatically.
//     */
//    public Student() {
//        this.id = UUID.randomUUID();
//    }

    /**
     * Constructor to create a new Student with a random UUID.
     *
     * @param firstName  The first name of the student
     * @param lastName   The last name of the student
     * @param email      The email of the student
     */
    public Student(String firstName, String lastName, String email) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getter for student ID
    public UUID getId() {
        return id;
    }

    // Getter for first name
    public String getFirstName() {
        return firstName;
    }

    // Getter for second name
    public String getLastName() {
        return lastName;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Setter for second name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a formatted string representation of the student.
     *
     * @return A string representing the student details.
     */
    @Override
    public String toString() {
        return String.format("| %-36s | %-15s | %-15s | %-30s |",
                getId(),
                getFirstName(),
                getLastName(),
                getEmail()
        );
    }
}
