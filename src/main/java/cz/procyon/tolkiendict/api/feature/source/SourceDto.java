package cz.procyon.tolkiendict.api.feature.source;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public final class SourceDto {

    @Getter
    @Setter
    @Schema(name = "SourceDtoCreate")
    public static final class Create {
        @Schema(example = "angrenost", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "name is mandatory")
        private String name;

        @Schema(example = "http://www.angrenost.cz/angrenost", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String url;
    }

    @Getter
    @Setter
    @Schema(name = "SourceDtoUpdate")
    public static final class Update {
        @Schema(example = "angrenost", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "name is mandatory")
        private String name;

        @Schema(example = "http://www.angrenost.cz/angrenost", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String url;
    }

    @Getter
    @Setter
    @Schema(name = "SourceDtoResponse")
    public static final class Response {
        private UUID id;
        private String name;
        private String url;
    }
}