package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto;

import java.util.Collection;
import java.util.UUID;

public record SyncDeletedDTO(
        Collection<UUID> words,
        Collection<UUID> languages,
        Collection<UUID> sources
) {
}