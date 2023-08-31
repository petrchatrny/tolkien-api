package cz.mendelu.pef.xchatrny.tolkienapi.feature.language;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.dto.LanguageDto;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.dto.LanguageMapper;
import cz.mendelu.pef.xchatrny.tolkienapi.shared.service.BaseEntityService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LanguageServiceImpl
        extends BaseEntityService<Language, UUID, LanguageDto.Create, LanguageDto.Update, LanguageDto.Response>
        implements LanguageService {

    private final LanguageRepository repository;
    private final LanguageMapper mapper;

    protected LanguageServiceImpl(LanguageRepository repository, LanguageMapper mapper) {
        super(repository);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<LanguageDto.Response> findAll() {
        return mapper.toResponseListDto(repository.findAll());
    }

    @Override
    public LanguageDto.Response findById(UUID id) {
        Language language = repository.findById(id);
        return mapper.toResponseDto(language);
    }

    @Transactional
    @Override
    public LanguageDto.Response create(LanguageDto.Create dto) {
        Language language = mapper.toDomain(dto);
        return mapper.toResponseDto(repository.create(language));
    }

    @Transactional
    @Override
    public LanguageDto.Response update(UUID id, LanguageDto.Update dto) {
        checkEntityExists(id);

        Language language = repository.findById(id);

        language.setName(dto.getName());
        language.setDescription(dto.getDescription());

        return mapper.toResponseDto(repository.update(language));
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        checkEntityExists(id);
        repository.delete(id);
    }

    @Override
    public List<Language> getCreatedAfter(LocalDateTime dateTime) {
        return repository.findCreatedAfter(dateTime);
    }

    @Override
    public List<Language> getUpdatedAfter(LocalDateTime dateTime) {
        return repository.findUpdatedAfter(dateTime);
    }

    @Override
    public List<Language> getDeletedAfter(LocalDateTime dateTime) {
        return repository.findDeletedAfter(dateTime);
    }
}
