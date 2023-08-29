package cz.mendelu.pef.xchatrny.tolkienapi.shared.service;

import java.util.List;

/**
 * @param <K> primary key type
 * @param <C> EntityDtoCreate
 * @param <U> EntityDtoUpdate
 * @param <R> EntityDtoResponse
 */
public interface CrudService<K, C, U, R> {
    /**
     * Find all entities that aren't marked as deleted.
     *
     * @return list of all entities mapped to list of DTOs
     */
    List<R> findAll();

    /**
     * Find one entity by its primary key.
     *
     * @param id primary key of entity
     * @return one entity found by primary key mapped to DTO
     */
    R findById(K id);

    /**
     * Creates new entity.
     *
     * @param dto of entity to be created
     * @return created entity mapped to DTO
     */
    R create(C dto);

    /**
     * Updates existing entity.
     *
     * @param id primary key of entity
     * @param dto of entity to be updated
     * @return updated entity mapped to DTO
     */
    R update(K id, U dto);

    /**
     * Deletes entity by its primary key.
     *
     * @param id primary key of entity
     */
    void delete(K id);
}
