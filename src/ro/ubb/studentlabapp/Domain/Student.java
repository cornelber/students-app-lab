package ro.ubb.studentlabapp.Domain;

import java.util.UUID;

/**
 * This class represents a student with first name, last name, and email.
 * It extends BaseEntity, which provides the unique ID functionality.
 */
public class Student extends BaseEntity<UUID> {
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
        super(); // Call the BaseEntity constructor
        this.setId(UUID.randomUUID());  // Set the UUID in the BaseEntity
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
        return String.format("%s %-15s | %-15s | %-30s |",
                super.toString(),  // Use the formatted ID string from BaseEntity
                getFirstName(),
                getLastName(),
                getEmail()
        );
    }
}
