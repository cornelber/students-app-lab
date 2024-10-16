package ro.ubb.studentlabapp.Repository;

import java.util.List;

public interface ICRUDRepository<Entity> {
    boolean save(Entity entity);
    List<Entity> findAll();
}
