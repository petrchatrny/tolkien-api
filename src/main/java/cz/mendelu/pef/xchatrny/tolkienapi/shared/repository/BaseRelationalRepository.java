package cz.mendelu.pef.xchatrny.tolkienapi.shared.repository;

import cz.mendelu.pef.xchatrny.tolkienapi.shared.model.SoftDeletableEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of {@link SoftDeleteRepository softDeleteRepository} for relational database that is using
 * hibernate EntityManager {@link EntityManager}.
 *
 * @param <E> entity type
 * @param <K> primary key type
 */
public abstract class BaseRelationalRepository<E extends SoftDeletableEntity, K extends Serializable>
        implements SoftDeleteRepository<E, K> {
    protected final EntityManager entityManager;
    private final Class<E> entityClass;

    /**
     * AllArgsConstructor used for dependency injection
     *
     * @param entityManager hibernate entity manager
     * @param entityClass   class of entity
     */
    public BaseRelationalRepository(EntityManager entityManager, Class<E> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    /**
     * {@inheritDoc}
     *
     * @param includeDeleted flag for including deleted entities
     * @return list of found entities
     */
    @Override
    public List<E> findAll(Boolean includeDeleted) {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e ";

        if (!includeDeleted) {
            jpql += "WHERE e.deletedAt is NULL ";
        }

        TypedQuery<E> query = entityManager.createQuery(jpql, entityClass);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     *
     * @param key            primary key of entity
     * @param includeDeleted flag for including deleted entities
     * @return found entity
     */
    @Override
    public E findById(K key, Boolean includeDeleted) {
        E entity = entityManager.find(entityClass, key);
        if (entity == null || (!includeDeleted && entity.getDeletedAt() != null)) {
            return null;
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     *
     * @param item entity to be created
     * @return created entity
     */
    @Override
    public E create(E item) {
        entityManager.persist(item);
        return item;
    }

    /**
     * {@inheritDoc}
     *
     * @param item entity to be created
     * @return updated entity
     */
    @Override
    public E update(E item) {
        return entityManager.merge(item);
    }

    /**
     * {@inheritDoc}
     *
     * @param key primary key of entity
     */
    @Override
    public void delete(K key) {
        E entity = findById(key);
        if (entity != null) {
            entity.setDeletedAt(LocalDateTime.now());
            entityManager.merge(entity);
        }
    }
}
