package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SourceDTO(
        UUID id,
        String name,
        LocalDateTime createdAt,
        String url
) {
}