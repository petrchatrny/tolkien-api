package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record WordDTO(
        UUID id,
        String czechMeaning,
        String translation,
        String tengwar,
        LocalDateTime createdAt,
        UUID languageId,
        UUID sourceId
) {
}