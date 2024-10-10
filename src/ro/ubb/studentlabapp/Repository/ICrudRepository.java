package ro.ubb.studentlabapp.Repository;

import java.util.List;
import java.util.UUID;

public interface ICrudRepository<Entity> {
    boolean save(Entity entity);
    boolean update(UUID id, Entity entity);
    boolean delete(UUID id);
    List<Entity> findAll();
    Entity findById(UUID id);

}
