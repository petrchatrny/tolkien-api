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
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.Word;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.WordService;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.dto.WordDto;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.dto.WordMapper;
import cz.mendelu.pef.xchatrny.tolkienapi.shared.service.SyncService;
import cz.mendelu.pef.xchatrny.tolkienapi.util.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Service
public class DictionaryService {
    private final LanguageService languageService;
    private final SourceService sourceService;
    private final WordService wordService;

    private final LanguageMapper languageMapper;
    private final SourceMapper sourceMapper;
    private final WordMapper wordMapper;

    public DictionaryService(
            LanguageService languageService,
            SourceService sourceService,
            WordService wordService,
            LanguageMapper languageMapper,
            SourceMapper sourceMapper,
            WordMapper wordMapper
    ) {
        this.languageService = languageService;
        this.sourceService = sourceService;
        this.wordService = wordService;
        this.languageMapper = languageMapper;
        this.sourceMapper = sourceMapper;
        this.wordMapper = wordMapper;
    }

    public DictionaryDto.Entities download() {
        List<LanguageDto.Response> languages = languageService.findAll();
        List<SourceDto.Response> sources = sourceService.findAll();
        List<WordDto.Response> words = wordService.findAll();

        return new DictionaryDto.Entities(words, languages, sources);
    }

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


    private <E, O> List<O> gatherEntityList(SyncService<E> service, LocalDateTime lastSync, SyncType syncType, Function<E, O> mapper) {
        return switch (syncType) {
            case CREATED -> service.getCreatedAfter(lastSync).stream().map(mapper).toList();
            case UPDATED -> service.getUpdatedAfter(lastSync).stream().map(mapper).toList();
            case DELETED -> service.getDeletedAfter(lastSync).stream().map(mapper).toList();
        };
    }

    private DictionaryDto.Entities gatherCreatedEntities(LocalDateTime lastSync) {
        List<WordDto.Response> createdWords = gatherEntityList(wordService, lastSync, SyncType.CREATED, wordMapper::toResponseDto);
        List<LanguageDto.Response> createdLanguages = gatherEntityList(languageService, lastSync, SyncType.CREATED, languageMapper::toResponseDto);
        List<SourceDto.Response> createdSources = gatherEntityList(sourceService, lastSync, SyncType.CREATED, sourceMapper::toResponseDto);

        return new DictionaryDto.Entities(createdWords, createdLanguages, createdSources);
    }

    private DictionaryDto.Entities gatherUpdatedEntities(LocalDateTime lastSync) {
        List<WordDto.Response> updatedWords = gatherEntityList(wordService, lastSync, SyncType.UPDATED, wordMapper::toResponseDto);
        List<LanguageDto.Response> updatedLanguages = gatherEntityList(languageService, lastSync, SyncType.UPDATED, languageMapper::toResponseDto);
        List<SourceDto.Response> updatedSources = gatherEntityList(sourceService, lastSync, SyncType.UPDATED, sourceMapper::toResponseDto);

        return new DictionaryDto.Entities(updatedWords, updatedLanguages, updatedSources);
    }

    private DictionaryDto.References gatherDeletedEntities(LocalDateTime lastSync) {
        List<UUID> deletedWords = gatherEntityList(wordService, lastSync, SyncType.DELETED, Word::getId);
        List<UUID> deletedLanguages = gatherEntityList(languageService, lastSync, SyncType.DELETED, Language::getId);
        List<UUID> deletedSources = gatherEntityList(sourceService, lastSync, SyncType.DELETED, Source::getId);

        return new DictionaryDto.References(deletedWords, deletedLanguages, deletedSources);
    }
}
