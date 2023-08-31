package cz.mendelu.pef.xchatrny.tolkienapi.shared.service;

import cz.mendelu.pef.xchatrny.tolkienapi.shared.repository.CrudRepository;
import jakarta.persistence.EntityNotFoundException;

public abstract class BaseEntityService<E, K, C, U, R> implements CrudService<K, C, U, R>{
    private final CrudRepository<E, K> repository;

    protected BaseEntityService(CrudRepository<E, K> repository) {
        this.repository = repository;
    }

    public void checkEntityExists(K key) {
        E entity = repository.findById(key);
        if (entity == null) {
            throw new EntityNotFoundException("resource not found for key: " + key.toString());
        }
    }
}
