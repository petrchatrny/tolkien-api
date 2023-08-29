package cz.mendelu.pef.xchatrny.tolkienapi.feature.word.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

public class WordDto {

    @Getter
    @Setter
    public static class Create {
        @NotBlank(message = "czechMeaning is mandatory")
        private String czechMeaning;

        @NotBlank(message = "translation is mandatory")
        private String translation;

        private String tengwar;

        @NotBlank(message = "languageId is mandatory")
        private String languageId;

        private String sourceId;
    }

    @Getter
    @Setter
    public static class Update {
        @NotBlank(message = "czechMeaning is mandatory")
        private String czechMeaning;

        @NotBlank(message = "translation is mandatory")
        private String translation;

        private String tengwar;

        @NotBlank(message = "languageId is mandatory")
        private String languageId;

        private String sourceId;
    }

    @Getter
    @Setter
    public static class Response {
        private UUID id;
        private String czechMeaning;
        private String translation;
        private String tengwar;
        private LocalDateTime createdAt;
    }
}