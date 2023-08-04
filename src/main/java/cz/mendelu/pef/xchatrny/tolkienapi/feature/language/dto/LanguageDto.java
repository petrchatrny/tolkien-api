package cz.mendelu.pef.xchatrny.tolkienapi.feature.language.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

public final class LanguageDto {
    @Getter
    @Setter
    @Schema(name = "LanguageDtoCreate")
    public static class Create {
        @Schema(example = "Quenya", requiredMode = Schema.RequiredMode.REQUIRED)
        private String name;

        @Schema(example = "Elvish language", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String description;
    }

    @Getter
    @Setter
    @Schema(name = "LanguageDtoUpdate")
    public static class Update {
        @Schema(example = "Quenya", requiredMode = Schema.RequiredMode.REQUIRED)
        private String name;

        @Schema(example = "Elvish language", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String description;
    }

    @Getter
    @Setter
    @Schema(name = "LanguageDtoResponse")
    public static class Response {
        private UUID id;
        private String name;
        private String description;
        private LocalDateTime createdAt;
    }
}