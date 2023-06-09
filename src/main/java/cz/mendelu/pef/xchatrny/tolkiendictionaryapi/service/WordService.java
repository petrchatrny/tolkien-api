package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.service;

import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto.WordDTO;
import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto.WordMapper;
import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model.Language;
import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model.Source;
import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model.Word;
import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.repository.IWordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Service
public class WordService {
    private final IWordRepository repository;
    private final LanguageService languageService;
    private final SourceService sourceService;

    public WordService(
            IWordRepository repository,
            LanguageService languageService,
            SourceService sourceService
    ) {
        this.repository = repository;
        this.languageService = languageService;
        this.sourceService = sourceService;
    }

    public Collection<WordDTO> getAllWords() {
        return repository
                .findAll()
                .stream()
                .map(new WordMapper())
                .toList();
    }

    public WordDTO getWordById(UUID id) {
        Word word = repository
                .findById(id)
                .orElseThrow(null);

        return new WordMapper().apply(word);
    }

    public WordDTO createWord(WordDTO dto) {
        System.out.println(dto.languageId());
        Language language = languageService.getLanguageById(dto.languageId());
        Source source = null;

        try {
            source = sourceService.getSourceById(dto.sourceId());
        } catch (Exception e) {
            System.out.println(e);
        }

        Word word = Word.builder()
                .id(dto.id())
                .czechMeaning(dto.czechMeaning())
                .translation(dto.translation())
                .tengwar(dto.tengwar())
                .addedByAdmin(true)
                .language(language)
                .source(source)
                .build();

        return new WordMapper().apply(repository.save(word));
    }

    public WordDTO updateWord(UUID id, WordDTO dto) {
        Word word = repository.findById(id).orElseThrow();
        Language language = languageService.getLanguageById(dto.languageId());
        Source source = sourceService.getSourceById(dto.sourceId());

        word.setCzechMeaning(dto.czechMeaning());
        word.setTranslation(dto.translation());
        word.setTengwar(dto.tengwar());
        word.setLanguage(language);
        word.setSource(source);

        return new WordMapper().apply(repository.save(word));
    }

    public void deleteWord(UUID id) {
        Word word = repository.findById(id).orElseThrow();

        word.setDeletedAt(LocalDateTime.now());
        repository.save(word);
    }
}
