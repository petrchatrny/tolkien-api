package cz.procyon.tolkiendict.api.feature.dictionary;

import cz.procyon.tolkiendict.api.feature.language.LanguageDto;
import cz.procyon.tolkiendict.api.feature.source.SourceDto;
import cz.procyon.tolkiendict.api.feature.word.WordDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

public final class DictionaryDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @Schema(name = "DictionaryDtoEntities")
    public static final class Entities {
        private List<WordDto.Response> words;
        private List<LanguageDto.Response> languages;
        private List<SourceDto.Response> sources;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Schema(name = "DictionaryDtoReferences")
    public static final class References {
        private List<UUID> words;
        private List<UUID> languages;
        private List<UUID> sources;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Schema(name = "DictionaryDtoSync")
    public static final class Sync {
        private DictionaryDto.Entities created;
        private DictionaryDto.Entities updated;
        private DictionaryDto.References deleted;
    }
}