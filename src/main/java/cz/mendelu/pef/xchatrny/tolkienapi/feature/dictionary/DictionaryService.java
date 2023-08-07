package cz.mendelu.pef.xchatrny.tolkienapi.feature.dictionary;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.dictionary.dto.DictionaryDTO;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.dictionary.dto.SyncDTO;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.dictionary.dto.SyncDeletedDTO;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.Language;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.LanguageService;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.dto.LanguageDto;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.dto.LanguageMapper;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.SourceService;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.dto.SourceDTO;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.WordService;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.dto.WordDTO;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.UUID;

@Service
public class DictionaryService {
    private final LanguageService languageService;
    private final SourceService sourceService;
    private final WordService wordService;

    private final LanguageMapper languageMapper;

    public DictionaryService(LanguageService languageService, SourceService sourceService, WordService wordService, LanguageMapper languageMapper) {
        this.languageService = languageService;
        this.sourceService = sourceService;
        this.wordService = wordService;
        this.languageMapper = languageMapper;
    }

    public DictionaryDTO download() {
//        Collection<LanguageDto.Response> languages = languageService.getAllLanguages();
        Collection<SourceDTO> sources = sourceService.getAllSources();
        Collection<WordDTO> words = wordService.getAllWords();

        return new DictionaryDTO(words, null, sources);
    }

    // TODO fixme
    public SyncDTO sync(Long lastSync) {
        // gather deleted entities
        Collection<UUID> deletedWords = wordService.getDeletedAfter(lastSync);
        Collection<UUID> deletedLanguages = languageService.getDeletedAfter(unixToLocaleDateTime(lastSync)).stream().map(Language::getId).toList();
        Collection<UUID> deletedSources = sourceService.getDeletedAfter(lastSync);
        SyncDeletedDTO deletedEntities = new SyncDeletedDTO(deletedWords, deletedLanguages, deletedSources);

        // gather updated entities
        Collection<WordDTO> updatedWords = wordService.getUpdatedAfter(lastSync);
        Collection<LanguageDto.Response> updatedLanguages = languageService.getUpdatedAfter(unixToLocaleDateTime(lastSync))
                                                                           .stream()
                                                                           .map(languageMapper::toResponseDto)
                                                                           .toList();
        Collection<SourceDTO> updatedSources = sourceService.getUpdatedAfter(lastSync);
        DictionaryDTO updatedEntities = new DictionaryDTO(updatedWords, updatedLanguages, updatedSources);

        // gather created entities
        Collection<WordDTO> createdWords = wordService.getCreatedAfter(lastSync);
        Collection<LanguageDto.Response> createdLanguages = languageService.getCreatedAfter(unixToLocaleDateTime(lastSync))
                                                                           .stream()
                                                                           .map(languageMapper::toResponseDto)
                                                                           .toList();
        Collection<SourceDTO> createdSources = sourceService.getCreatedAfter(lastSync);
        DictionaryDTO createdEntities = new DictionaryDTO(createdWords, createdLanguages, createdSources);

        // return result
        return new SyncDTO(createdEntities, updatedEntities, deletedEntities);
    }

    public LocalDateTime unixToLocaleDateTime(Long unix) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(unix), ZoneId.systemDefault());
    }
}
