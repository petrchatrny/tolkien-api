package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto;

import java.util.Collection;
import java.util.UUID;

public record SyncDTO(
        Collection<DictionaryDTO> created,
        Collection<DictionaryDTO> updated,
        Collection<SyncDeletedDTO> deleted
) {

    static class SyncDeletedDTO {
        Collection<UUID> words;
        Collection<UUID> languages;
        Collection<UUID> sources;
    }
}