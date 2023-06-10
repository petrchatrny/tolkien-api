package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.service;

import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

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

    public SyncDTO sync(LocalDateTime lastSync) {
        return new SyncDTO(null, null, null);
    }
}
