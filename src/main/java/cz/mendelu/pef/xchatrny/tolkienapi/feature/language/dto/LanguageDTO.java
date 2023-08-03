package cz.mendelu.pef.xchatrny.tolkienapi.feature.language.dto;

import java.util.UUID;

public record LanguageDTO(
        UUID id,
        String name,
        Long createdAt
) {
}
