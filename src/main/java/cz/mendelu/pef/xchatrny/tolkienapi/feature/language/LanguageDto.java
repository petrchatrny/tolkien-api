package cz.mendelu.pef.xchatrny.tolkienapi.feature.language;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
        @NotBlank(message = "name is mandatory")
        private String name;

        @Schema(example = "Elvish language", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String description;
    }

    @Getter
    @Setter
    @Schema(name = "LanguageDtoUpdate")
    public static class Update {
        @Schema(example = "Quenya", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "name is mandatory")
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
        private LocalDateTime updatedAt;
    }
}