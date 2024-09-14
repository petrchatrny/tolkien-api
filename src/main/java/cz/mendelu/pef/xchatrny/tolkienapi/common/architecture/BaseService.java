package cz.mendelu.pef.xchatrny.tolkienapi.common.architecture;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public abstract class BaseService<
        ID extends Serializable,
        ENTITY extends BaseEntity<ID>,
        CREATE,
        UPDATE,
        RESPONSE,
        REPOSITORY extends BaseRepository<ID, ENTITY>,
        MAPPER extends BaseMapper<ENTITY, CREATE, UPDATE, RESPONSE>
        > {

    protected REPOSITORY repository;
    protected MAPPER mapper;

    public RESPONSE get(ID id) {
        ENTITY entity = repository.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException("Entity not found for id: " + id.toString());
        }
        return mapper.toResponse(entity);
    }

    public List<RESPONSE> getAll() {
        return repository.findAll().stream()
                .map(entity -> mapper.toResponse(entity))
                .toList();
    }

    @Transactional
    public RESPONSE create(CREATE dto) {
        ENTITY toCreate = mapper.fromCreate(dto);
        ENTITY created = repository.create(toCreate);
        return mapper.toResponse(created);
    }

    @Transactional
    public RESPONSE update(ID id, UPDATE dto) {
        ENTITY toUpdate = repository.findById(id);
        if (toUpdate == null) {
            throw new EntityNotFoundException("Entity not found for id: " + id.toString());
        }

        mapper.fromUpdate(dto, toUpdate);
        ENTITY updated = repository.update(toUpdate);
        return mapper.toResponse(updated);
    }

    @Transactional
    public void delete(ID id) {
        repository.delete(id);
    }

    public List<ENTITY> getCreatedAfter(LocalDateTime dateTime) {
        return repository.findCreatedAfter(dateTime);
    }

    public List<ENTITY> getUpdatedAfter(LocalDateTime dateTime) {
        return repository.findUpdatedAfter(dateTime);
    }

    public List<ENTITY> getDeletedAfter(LocalDateTime dateTime) {
        return repository.findDeletedAfter(dateTime);
    }

    @Autowired
    public void setRepository(REPOSITORY repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(MAPPER mapper) {
        this.mapper = mapper;
    }
}
