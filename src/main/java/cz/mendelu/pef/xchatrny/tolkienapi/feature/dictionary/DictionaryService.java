package cz.mendelu.pef.xchatrny.tolkienapi.feature.dictionary;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.dictionary.dto.DictionaryDto;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.dictionary.dto.SyncDto;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.Language;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.LanguageService;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.dto.LanguageDto;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.dto.LanguageMapper;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.Source;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.SourceService;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.dto.SourceDto;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.dto.SourceMapper;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.WordService;
import cz.mendelu.pef.xchatrny.tolkienapi.util.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DictionaryService {
    private final LanguageService languageService;
    private final SourceService sourceService;
    private final WordService wordService;

    private final LanguageMapper languageMapper;
    private final SourceMapper sourceMapper;

    public DictionaryService(LanguageService languageService, SourceService sourceService, WordService wordService, LanguageMapper languageMapper, SourceMapper sourceMapper) {
        this.languageService = languageService;
        this.sourceService = sourceService;
        this.wordService = wordService;
        this.languageMapper = languageMapper;
        this.sourceMapper = sourceMapper;
    }

    public DictionaryDto.Entities download() {
        List<LanguageDto.Response> languages = languageService.findAll();
        List<SourceDto.Response> sources = sourceService.findAll();
//        List<WordDTO> words = wordService.getAllWords();

        return new DictionaryDto.Entities(null, languages, sources);
    }

    // TODO fixme
    public SyncDto sync(Long lastSyncUnix) {
        // convert unix to LocalDateTime
        LocalDateTime lastSync = DateTimeUtil.unixToLocalDateTime(lastSyncUnix);

        // gather deleted entities
        DictionaryDto.References deletedEntities = gatherDeletedEntities(lastSync);

        // gather updated entities
        DictionaryDto.Entities updatedEntities = gatherUpdatedEntities(lastSync);

        // gather created entities
        DictionaryDto.Entities createdEntities = gatherCreatedEntities(lastSync);

        // return result
        return new SyncDto(createdEntities, updatedEntities, deletedEntities);
    }


    private DictionaryDto.Entities gatherCreatedEntities(LocalDateTime lastSync) {
        // TODO add words

        List<LanguageDto.Response> createdLanguages = languageService.getCreatedAfter(lastSync)
                .stream()
                .map(languageMapper::toResponseDto)
                .toList();

        List<SourceDto.Response> createdSources = sourceService.getCreatedAfter(lastSync)
                .stream()
                .map(sourceMapper::toResponseDto)
                .toList();

        return new DictionaryDto.Entities(null, createdLanguages, createdSources);
    }

    private DictionaryDto.Entities gatherUpdatedEntities(LocalDateTime lastSync) {
        // TODO add words

        List<LanguageDto.Response> createdLanguages = languageService.getUpdatedAfter(lastSync)
                .stream()
                .map(languageMapper::toResponseDto)
                .toList();

        List<SourceDto.Response> createdSources = sourceService.getUpdatedAfter(lastSync)
                .stream()
                .map(sourceMapper::toResponseDto)
                .toList();

        return new DictionaryDto.Entities(null, createdLanguages, createdSources);
    }

    private DictionaryDto.References gatherDeletedEntities(LocalDateTime lastSync) {
        // TODO add words

        List<UUID> deletedLanguages = languageService.getDeletedAfter(lastSync)
                .stream()
                .map(Language::getId)
                .toList();

        List<UUID> deletedSources = sourceService.getDeletedAfter(lastSync)
                .stream()
                .map(Source::getId)
                .toList();

        return new DictionaryDto.References(null, deletedLanguages, deletedSources);
    }
}
