package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record LanguageDTO(
        UUID id,
        String name,
        LocalDateTime createdAt,
        Byte[] icon
) {
}
