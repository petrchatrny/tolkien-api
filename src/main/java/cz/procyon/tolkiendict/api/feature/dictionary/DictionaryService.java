package cz.procyon.tolkiendict.api.feature.dictionary;

import cz.procyon.tolkiendict.api.common.architecture.BaseService;
import cz.procyon.tolkiendict.api.feature.language.Language;
import cz.procyon.tolkiendict.api.feature.language.LanguageDto;
import cz.procyon.tolkiendict.api.feature.language.LanguageMapper;
import cz.procyon.tolkiendict.api.feature.language.LanguageService;
import cz.procyon.tolkiendict.api.feature.source.Source;
import cz.procyon.tolkiendict.api.feature.source.SourceDto;
import cz.procyon.tolkiendict.api.feature.source.SourceMapper;
import cz.procyon.tolkiendict.api.feature.source.SourceService;
import cz.procyon.tolkiendict.api.feature.word.Word;
import cz.procyon.tolkiendict.api.feature.word.WordDto;
import cz.procyon.tolkiendict.api.feature.word.WordMapper;
import cz.procyon.tolkiendict.api.feature.word.WordService;
import cz.procyon.tolkiendict.api.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class DictionaryService {

    private final LanguageService languageService;
    private final SourceService sourceService;
    private final WordService wordService;

    private final LanguageMapper languageMapper;
    private final SourceMapper sourceMapper;
    private final WordMapper wordMapper;

    public DictionaryDto.Entities download() {
        List<LanguageDto.Response> languages = languageService.getAll();
        List<SourceDto.Response> sources = sourceService.getAll();
        List<WordDto.Response> words = wordService.getAll();

        return new DictionaryDto.Entities(words, languages, sources);
    }

    public DictionaryDto.Sync sync(Long lastSyncUnix) {
        // convert unix to LocalDateTime
        LocalDateTime lastSync = DateTimeUtil.unixToLocalDateTime(lastSyncUnix);

        // gather deleted entities
        DictionaryDto.References deletedEntities = getDeletedEntities(lastSync);

        // gather updated entities
        DictionaryDto.Entities updatedEntities = getUpdatedEntities(lastSync);

        // gather created entities
        DictionaryDto.Entities createdEntities = getCreatedEntities(lastSync);

        // return result
        return new DictionaryDto.Sync(createdEntities, updatedEntities, deletedEntities);
    }


    private <E, O> List<O> getEntities(
            BaseService service,
            LocalDateTime lastSync,
            SyncType syncType,
            Function<E, O> mapper) {

        return switch (syncType) {
            case CREATED -> service.getCreatedAfter(lastSync).stream().map(mapper).toList();
            case UPDATED -> service.getUpdatedAfter(lastSync).stream().map(mapper).toList();
            case DELETED -> service.getDeletedAfter(lastSync).stream().map(mapper).toList();
        };
    }

    private DictionaryDto.Entities getCreatedEntities(LocalDateTime lastSync) {
        List<WordDto.Response> createdWords = getEntities(wordService, lastSync, SyncType.CREATED, wordMapper::toResponse);
        List<LanguageDto.Response> createdLanguages = getEntities(languageService, lastSync, SyncType.CREATED, languageMapper::toResponse);
        List<SourceDto.Response> createdSources = getEntities(sourceService, lastSync, SyncType.CREATED, sourceMapper::toResponse);

        return new DictionaryDto.Entities(createdWords, createdLanguages, createdSources);
    }

    private DictionaryDto.Entities getUpdatedEntities(LocalDateTime lastSync) {
        List<WordDto.Response> updatedWords = getEntities(wordService, lastSync, SyncType.UPDATED, wordMapper::toResponse);
        List<LanguageDto.Response> updatedLanguages = getEntities(languageService, lastSync, SyncType.UPDATED, languageMapper::toResponse);
        List<SourceDto.Response> updatedSources = getEntities(sourceService, lastSync, SyncType.UPDATED, sourceMapper::toResponse);

        return new DictionaryDto.Entities(updatedWords, updatedLanguages, updatedSources);
    }

    private DictionaryDto.References getDeletedEntities(LocalDateTime lastSync) {
        List<UUID> deletedWords = getEntities(wordService, lastSync, SyncType.DELETED, Word::getId);
        List<UUID> deletedLanguages = getEntities(languageService, lastSync, SyncType.DELETED, Language::getId);
        List<UUID> deletedSources = getEntities(sourceService, lastSync, SyncType.DELETED, Source::getId);

        return new DictionaryDto.References(deletedWords, deletedLanguages, deletedSources);
    }
}
