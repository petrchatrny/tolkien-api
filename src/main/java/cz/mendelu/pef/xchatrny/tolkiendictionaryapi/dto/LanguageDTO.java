package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto;

import java.util.UUID;

public record LanguageDTO(
        UUID id,
        String name,
        Byte[] icon
) {
}