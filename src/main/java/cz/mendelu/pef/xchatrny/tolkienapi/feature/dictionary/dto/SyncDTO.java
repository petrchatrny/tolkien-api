package cz.mendelu.pef.xchatrny.tolkienapi.feature.dictionary.dto;

public record SyncDTO(
        DictionaryDTO created,
        DictionaryDTO updated,
        SyncDeletedDTO deleted
) {
}