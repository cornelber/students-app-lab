package ro.ubb.studentlabapp.Domain;

import java.util.UUID;

public class Student {
    private UUID id;
    private String name;
    private String email;

    public Student(UUID uuid, String name, String email) {
        this.id = UUID.randomUUID();  // Generare cheie unică
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("| %-36s | %-20s | %-10d",
                getId(),
                getName(),
                getEmail()
        );
    }
}
