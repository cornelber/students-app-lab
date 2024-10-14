package ro.ubb.studentlabapp.Service;

import java.util.List;
import java.util.UUID;

public interface ICrudService<Entity> {

    boolean add(Entity entity);

    boolean update(UUID id, Entity entity);

    boolean delete(UUID id);

    List<Entity> getAll();
}
