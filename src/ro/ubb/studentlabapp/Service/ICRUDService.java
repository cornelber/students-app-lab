package ro.ubb.studentlabapp.Service;

import java.util.List;

public interface ICRUDService<Entity> {
    boolean add(Entity entity);
    List<Entity> getAll();
}
