package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto;

import java.util.UUID;

public record WordDTO(
        UUID id,
        String czechMeaning,
        String translation,
        String tengwar,
        Long createdAt,
        UUID languageId,
        UUID sourceId
) {
}