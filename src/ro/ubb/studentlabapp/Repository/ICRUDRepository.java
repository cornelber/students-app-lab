package ro.ubb.studentlabapp.Repository;

import java.util.List;
import java.util.UUID;

public interface ICRUDRepository<Entity> {
    boolean save(Entity entity);
    boolean delete(UUID id);
    List<Entity> findAll();
    Entity findById(UUID id);
}
