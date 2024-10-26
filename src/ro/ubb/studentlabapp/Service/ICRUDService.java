package ro.ubb.studentlabapp.Service;

import java.util.List;
import java.util.UUID;

/**
 * Generic interface for basic CRUD operations.
 *
 * @param <Entity> The type of the entity to be handled by the CRUD operations.
 */
public interface ICRUDService<Entity> {
    /**
     * Adds a new entity to the repository.
     *
     * @param entity The entity to be added.
     * @return true if the entity was successfully added, false otherwise.
     */
    boolean add(Entity entity);

    /**
     * Updates an existing entity in the repository.
     *
     * @param id     The UUID of the entity to be updated.
     * @param entity The new data for the entity.
     * @return true if the entity was successfully updated, false otherwise.
     */
    boolean update(UUID id, Entity entity);

    /**
     * Deletes an entity from the repository.
     *
     * @param id The UUID of the entity to be deleted.
     * @return true if the entity was successfully deleted, false otherwise.
     */
    boolean delete(UUID id);

    /**
     * Retrieves all entities from the repository.
     *
     * @return A list of all entities.
     */
    List<Entity> getAll();
}
