package ro.ubb.studentlabapp.Repository;

public interface ICRUDRepository<Entity> {
    boolean save(Entity entity);
}
