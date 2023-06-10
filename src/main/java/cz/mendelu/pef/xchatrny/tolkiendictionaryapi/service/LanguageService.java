package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.service;

import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto.LanguageDTO;
import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto.LanguageMapper;
import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model.Language;
import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.repository.ILanguageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Service
public class LanguageService {
    private final ILanguageRepository repository;

    public LanguageService(ILanguageRepository repository) {
        this.repository = repository;
    }

    public Collection<LanguageDTO> getAllLanguages() {
        return repository
                .findUndeleted()
                .stream()
                .map(new LanguageMapper())
                .toList();
    }

    public LanguageDTO getById(UUID id) {
        Language language = repository
                .findById(id)
                .orElseThrow(null);

        return new LanguageMapper().apply(language);
    }

    public Language getLanguageById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    public LanguageDTO createLanguage(LanguageDTO dto) {
        Language language = Language.builder()
                .id(dto.id())
                .name(dto.name())
                .icon(dto.icon())
                .build();

        return new LanguageMapper().apply(repository.save(language));
    }

    public LanguageDTO updateLanguage(UUID id, LanguageDTO dto) {
        Language language = repository.findById(id).orElseThrow();

        language.setName(dto.name());
        language.setIcon(dto.icon());

        return new LanguageMapper().apply(repository.save(language));
    }

    public void deleteLanguage(UUID id) {
        Language language = repository.findById(id).orElseThrow();

        language.setDeletedAt(LocalDateTime.now());
        repository.save(language);
    }
}
