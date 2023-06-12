package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto;

public record SyncDTO(
        DictionaryDTO created,
        DictionaryDTO updated,
        SyncDeletedDTO deleted
) {
}