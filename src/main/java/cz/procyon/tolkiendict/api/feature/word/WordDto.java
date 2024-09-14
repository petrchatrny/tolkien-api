package cz.procyon.tolkiendict.api.feature.word;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

public final class WordDto {

    @Getter
    @Setter
    @Schema(name = "WordDtoCreate")
    public static final class Create {
        @NotBlank(message = "czechMeaning is mandatory")
        private String czechMeaning;

        @NotBlank(message = "translation is mandatory")
        private String translation;

        private String tengwar;

        @NotNull(message = "languageId is mandatory")
        private UUID languageId;

        private UUID sourceId;
    }

    @Getter
    @Setter
    @Schema(name = "WordDtoUpdate")
    public static final class Update {
        @NotBlank(message = "czechMeaning is mandatory")
        private String czechMeaning;

        @NotBlank(message = "translation is mandatory")
        private String translation;

        private String tengwar;

        @NotNull(message = "languageId is mandatory")
        private UUID languageId;

        private UUID sourceId;
    }

    @Getter
    @Setter
    @Schema(name = "WordDtoResponse")
    public static final class Response {
        private UUID id;
        private String czechMeaning;
        private String translation;
        private String tengwar;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private UUID languageId;
        private UUID sourceId;
    }
}