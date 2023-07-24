package cz.mendelu.pef.xchatrny.tolkienapi.feature.word;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.Language;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.LanguageService;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.Source;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.SourceService;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.dto.WordDTO;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.dto.WordMapper;
import cz.mendelu.pef.xchatrny.tolkienapi.util.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
                .findUndeleted()
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
            System.out.println(e.getMessage());
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

    public Collection<WordDTO> getCreatedAfter(Long unixTime) {
        if (unixTime == null) {
            return getAllWords();
        }

        LocalDateTime dateTime = DateTimeUtil.unixToLocalDateTime(unixTime);
        return repository.findCreatedAtAfter(dateTime)
                .stream()
                .map(new WordMapper())
                .toList();
    }

    public Collection<WordDTO> getUpdatedAfter(Long unixTime) {
        if (unixTime == null) {
            return getAllWords();
        }

        LocalDateTime dateTime = DateTimeUtil.unixToLocalDateTime(unixTime);
        return repository.findUpdatedAtAfter(dateTime)
                .stream()
                .map(new WordMapper())
                .toList();
    }

    public Collection<UUID> getDeletedAfter(Long unixTime) {
        if (unixTime == null) {
            return getAllWords()
                    .stream()
                    .map(WordDTO::id)
                    .toList();
        }

        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTime), ZoneId.systemDefault());

        return repository.findDeletedAtAfter(dateTime)
                .stream()
                .map(Word::getId)
                .toList();
    }
}
