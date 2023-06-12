package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.service;

import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class DictionaryService {
    private final LanguageService languageService;
    private final SourceService sourceService;
    private final WordService wordService;

    public DictionaryService(LanguageService languageService, SourceService sourceService, WordService wordService) {
        this.languageService = languageService;
        this.sourceService = sourceService;
        this.wordService = wordService;
    }

    public DictionaryDTO download() {
        Collection<LanguageDTO> languages = languageService.getAllLanguages();
        Collection<SourceDTO> sources = sourceService.getAllSources();
        Collection<WordDTO> words = wordService.getAllWords();

        return new DictionaryDTO(words, languages, sources);
    }

    public SyncDTO sync(Long lastSync) {
        // gather deleted entities
        Collection<UUID> deletedWords = wordService.getDeletedAfter(lastSync);
        Collection<UUID> deletedLanguages = languageService.getDeletedAfter(lastSync);
        Collection<UUID> deletedSources = sourceService.getDeletedAfter(lastSync);
        SyncDeletedDTO deletedEntities = new SyncDeletedDTO(deletedWords, deletedLanguages, deletedSources);

        // gather updated entities
        Collection<WordDTO> updatedWords = wordService.getUpdatedAfter(lastSync);
        Collection<LanguageDTO> updatedLanguages = languageService.getUpdatedAfter(lastSync);
        Collection<SourceDTO> updatedSources = sourceService.getUpdatedAfter(lastSync);
        DictionaryDTO updatedEntities = new DictionaryDTO(updatedWords, updatedLanguages, updatedSources);

        // gather created entities
        Collection<WordDTO> createdWords = wordService.getCreatedAfter(lastSync);
        Collection<LanguageDTO> createdLanguages = languageService.getCreatedAfter(lastSync);
        Collection<SourceDTO> createdSources = sourceService.getCreatedAfter(lastSync);
        DictionaryDTO createdEntities = new DictionaryDTO(createdWords, createdLanguages, createdSources);

        // return result
        return new SyncDTO(createdEntities, updatedEntities, deletedEntities);
    }
}
