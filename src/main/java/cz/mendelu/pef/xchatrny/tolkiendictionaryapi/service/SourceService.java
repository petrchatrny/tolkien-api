package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.service;

import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto.SourceDTO;
import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto.SourceMapper;
import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model.Source;
import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.repository.ISourceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Service
public class SourceService {
    private final ISourceRepository repository;

    public SourceService(ISourceRepository repository) {
        this.repository = repository;
    }

    public Collection<SourceDTO> getAllSources() {
        return repository
                .findAll()
                .stream()
                .map(new SourceMapper())
                .toList();
    }

    public SourceDTO getById(UUID id) {
        Source source = repository
                .findById(id)
                .orElseThrow(null);

        return new SourceMapper().apply(source);
    }

    public Source getSourceById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    public SourceDTO createSource(SourceDTO dto) {
        Source source = Source.builder()
                .id(dto.id())
                .name(dto.name())
                .url(dto.url())
                .build();

        return new SourceMapper().apply(repository.save(source));
    }

    public SourceDTO updateSource(UUID id, SourceDTO dto) {
        Source source = repository.findById(id).orElseThrow();

        source.setName(dto.name());
        source.setUrl(dto.url());

        return new SourceMapper().apply(repository.save(source));
    }

    public void deleteSource(UUID id) {
        Source source = repository.findById(id).orElseThrow();

        source.setDeletedAt(LocalDateTime.now());
        repository.save(source);
    }
}
