package cz.procyon.tolkiendict.api.common.architecture;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Base repository for performing CRUD operations upon relational database
 * <p>
 * Implemented using hibernate EntityManager {@link EntityManager}.
 *
 * @param <ENTITY> entity type
 * @param <ID>     primary key type
 */
public abstract class BaseRepository<ID extends Serializable, ENTITY extends BaseEntity<ID>> {

    protected final EntityManager entityManager;
    private final Class<ENTITY> entityClass;

    /**
     * AllArgsConstructor used for dependency injection
     *
     * @param entityManager hibernate entity manager
     * @param entityClass   class of entity
     */
    public BaseRepository(EntityManager entityManager, Class<ENTITY> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    /**
     * Gets all entities
     *
     * @return list of found entities
     */
    public List<ENTITY> findAll() {
        return findAll(false);
    }

    /**
     * Gets all entities
     * <p>
     * Allows to find also soft-deleted entities.
     *
     * @param includeDeleted flag for including deleted entities
     * @return list of found entities
     */
    public List<ENTITY> findAll(Boolean includeDeleted) {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e ";

        if (!includeDeleted) {
            jpql += "WHERE e.deletedAt IS NULL ";
        }

        TypedQuery<ENTITY> query = entityManager.createQuery(jpql, entityClass);
        return query.getResultList();
    }

    /**
     * Gets one entity by its primary key
     *
     * @param id primary key
     * @return found entity
     */
    public ENTITY findById(ID id) {
        return findById(id, false);
    }

    /**
     * Gets one entity by its primary key
     * <p>
     * Allows to find also soft-deleted entities.
     *
     * @param key            primary key of entity
     * @param includeDeleted flag for including deleted entities
     * @return found entity
     */
    public ENTITY findById(ID key, Boolean includeDeleted) {
        ENTITY entity = entityManager.find(entityClass, key);
        if (entity == null || (!includeDeleted && entity.getDeletedAt() != null)) {
            return null;
        }

        return entity;
    }

    /**
     * Creates new entity
     *
     * @param item entity to be created
     * @return created entity
     */
    public ENTITY create(ENTITY item) {
        entityManager.persist(item);
        return item;
    }

    /**
     * Updates existing entity
     *
     * @param item entity to be created
     * @return updated entity
     */
    public ENTITY update(ENTITY item) {
        ENTITY entity = entityManager.merge(item);
        entityManager.flush();

        return entity;
    }

    /**
     * Deletes entity by its primary key
     *
     * @param key primary key of entity
     */
    public void delete(ID key) {
        ENTITY entity = findById(key);
        if (entity != null) {
            entity.setDeletedAt(LocalDateTime.now());
            entityManager.merge(entity);
            entityManager.flush();
        }
    }

    /**
     * Method for obtaining entities that are not deleted and are created after target timestamp
     *
     * @param dateTime target timestamp
     * @return list of entities created after target timestamp
     */
    public List<ENTITY> findCreatedAfter(LocalDateTime dateTime) {
        String jpql = "SELECT e " +
                      "FROM " + entityClass.getSimpleName() + " e " +
                      "WHERE e.createdAt > :targetDate " +
                      "  AND e.deletedAt IS NULL";

        TypedQuery<ENTITY> query = entityManager.createQuery(jpql, entityClass);
        query.setParameter("targetDate", dateTime);

        return query.getResultList();
    }

    /**
     * Method for obtaining entities that are not deleted and were updated after target timestamp
     *
     * @param dateTime target timestamp
     * @return list of entities updated after target timestamp
     */
    public List<ENTITY> findUpdatedAfter(LocalDateTime dateTime) {
        String jpql = "SELECT e " +
                      "FROM " + entityClass.getSimpleName() + " e " +
                      "WHERE e.updatedAt > :targetDate " +
                      "  AND e.updatedAt != e.createdAt " +
                      "  AND e.deletedAt IS NULL ";

        TypedQuery<ENTITY> query = entityManager.createQuery(jpql, entityClass);
        query.setParameter("targetDate", dateTime);

        return query.getResultList();
    }

    /**
     * Method for obtaining entities that are deleted and were deleted after target timestamp
     *
     * @param dateTime target timestamp
     * @return list of entities deleted after target timestamp
     */
    public List<ENTITY> findDeletedAfter(LocalDateTime dateTime) {
        String jpql = "SELECT e " +
                      "FROM " + entityClass.getSimpleName() + " e " +
                      "WHERE e.deletedAt > :targetDate";

        TypedQuery<ENTITY> query = entityManager.createQuery(jpql, entityClass);
        query.setParameter("targetDate", dateTime);

        return query.getResultList();
    }
}
