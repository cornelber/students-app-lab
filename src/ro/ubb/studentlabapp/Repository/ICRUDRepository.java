package ro.ubb.studentlabapp.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Generic interface for basic CRUD (Create, Read, Update, Delete) operations on a repository.
 *
 * @param <Entity> The type of the entity to be handled by the repository.
 */
public interface ICRUDRepository<Entity> {
    /**
     * Saves a new entity to the repository.
     *
     * @param entity The entity to be saved.
     * @return true if the entity was successfully saved, false otherwise.
     */
    boolean save(Entity entity);

    /**
     * Updates an existing entity in the repository.
     *
     * @param id     The UUID of the entity to be updated.
     * @param entity The updated entity data.
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
    List<Entity> findAll();

    /**
     * Finds an entity by its unique UUID.
     *
     * @param id The UUID of the entity to be found.
     * @return The entity if found, or null if not found.
     */
    Entity findById(UUID id);
}
