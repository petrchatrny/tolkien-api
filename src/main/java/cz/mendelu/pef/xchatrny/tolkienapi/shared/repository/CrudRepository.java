package cz.mendelu.pef.xchatrny.tolkienapi.shared.repository;

import java.util.List;

/**
 * Interface for performing CRUD operations upon database entity.
 *
 * @param <E> data type of entity
 * @param <K> data type of entity's primary key
 */
public interface CrudRepository<E, K> {
    /**
     * Gets all entities.
     *
     * @return list of all entities from database
     */
    List<E> findAll();

    /**
     * Gets one entity by its primary key.
     *
     * @param key primary key of entity
     * @return one entity found by primary key
     */
    E findById(K key);

    /**
     * Creates new entity.
     *
     * @param item entity to be created
     * @return created entity
     */
    E create(E item);

    /**
     * Updates existing entity.
     *
     * @param item entity to be updated
     * @return updated entity
     */
    E update(E item);

    /**
     * Deletes entity by its primary key.
     *
     * @param key primary key of entity
     */
    void delete(K key);
}