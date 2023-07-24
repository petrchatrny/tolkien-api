package cz.mendelu.pef.xchatrny.tolkienapi.feature.source.dto;

import java.util.UUID;

public record SourceDTO(
        UUID id,
        String name,
        Long createdAt,
        String url
) {
}