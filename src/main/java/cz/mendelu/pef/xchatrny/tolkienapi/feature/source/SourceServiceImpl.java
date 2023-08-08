package cz.mendelu.pef.xchatrny.tolkienapi.feature.source;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.dto.SourceDto;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.dto.SourceMapper;
import cz.mendelu.pef.xchatrny.tolkienapi.shared.service.BaseEntityService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SourceServiceImpl
        extends BaseEntityService<Source, UUID, SourceDto.Create, SourceDto.Update, SourceDto.Response>
        implements SourceService{
    private final SourceRepository repository;
    private final SourceMapper mapper;

    public SourceServiceImpl(SourceRepository repository, SourceMapper mapper) {
        super(repository);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<SourceDto.Response> findAll() {
        return mapper.toResponseListDto(repository.findAll());
    }

    @Override
    public SourceDto.Response findById(UUID id) {
        Source source = repository.findById(id);
        return mapper.toResponseDto(source);
    }

    @Transactional
    @Override
    public SourceDto.Response create(SourceDto.Create dto) {
        Source source = mapper.toDomain(dto);
        repository.create(source);

        return mapper.toResponseDto(source);
    }

    @Override
    public SourceDto.Response update(UUID id, SourceDto.Update dto) {
        checkEntityExists(id);

        Source source = repository.findById(id);
        source.setName(dto.getName());
        source.setUrl(dto.getUrl());
        repository.update(source);

        return mapper.toResponseDto(source);
    }

    @Override
    public void delete(UUID id) {
        checkEntityExists(id);
        repository.delete(id);
    }

    @Override
    public List<Source> getCreatedAfter(LocalDateTime dateTime) {
        return repository.findCreatedAfter(dateTime);
    }

    @Override
    public List<Source> getUpdatedAfter(LocalDateTime dateTime) {
        return repository.findUpdatedAfter(dateTime);
    }

    @Override
    public List<Source> getDeletedAfter(LocalDateTime dateTime) {
        return repository.findDeletedAfter(dateTime);
    }
}
