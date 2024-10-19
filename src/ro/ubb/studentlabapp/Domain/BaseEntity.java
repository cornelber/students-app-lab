package ro.ubb.studentlabapp.Domain;


public class BaseEntity<ID> {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("| %-36s |", id);
    }
}
