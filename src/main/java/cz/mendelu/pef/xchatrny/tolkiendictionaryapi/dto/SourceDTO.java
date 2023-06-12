package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto;

import java.util.UUID;

public record SourceDTO(
        UUID id,
        String name,
        Long createdAt,
        String url
) {
}