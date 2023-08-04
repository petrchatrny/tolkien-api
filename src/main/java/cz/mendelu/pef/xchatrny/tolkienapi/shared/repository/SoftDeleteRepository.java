package cz.mendelu.pef.xchatrny.tolkienapi.shared.repository;


import cz.mendelu.pef.xchatrny.tolkienapi.shared.model.SoftDeletableEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Extension of ICRUDRepository interface.
 *
 * <p>It adds new methods for working with SoftDeletableEntity.</p>
 *
 * @see CrudRepository
 * @see cz.mendelu.pef.xchatrny.tolkienapi.shared.model.SoftDeletableEntity
 *
 * @param <E> data type of entity
 * @param <K> data type of entity's primary key
 */
public interface SoftDeleteRepository<E extends SoftDeletableEntity, K extends Serializable> extends CrudRepository<E, K> {
    /**
     * Gets all entities and may include deleted ones.
     *
     * @param includeDeleted flag for including deleted entities
     * @return list of all entities from database
     */
    List<E> findAll(Boolean includeDeleted);

    /**
     * Gets one entity by its primary key. Selection may also include deleted entities.
     *
     * @param key primary key of entity
     * @param includeDeleted flag for including deleted entities
     * @return one entity found by primary key
     */
    E findById(K key, Boolean includeDeleted);

    /**
     * Deletes entity from database by its primary key permanently. Row in database is gone and irrecoverable.
     * Hasta la vista entity!
     *
     * @param key primary key of entity
     */
    void deletePermanently(K key);

    @Override
    default List<E> findAll() {
        return findAll(false);
    }

    @Override
    default E findById(K key) {
        return findById(key, false);
    }
}