package cz.mendelu.pef.xchatrny.tolkienapi.feature.dictionary;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.LanguageDto;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.SourceDto;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.WordDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

public class DictionaryDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @Schema(name = "DictionaryDtoEntities")
    public static class Entities {
        private List<WordDto.Response> words;
        private List<LanguageDto.Response> languages;
        private List<SourceDto.Response> sources;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Schema(name = "DictionaryDtoReferences")
    public static class References {
        private List<UUID> words;
        private List<UUID> languages;
        private List<UUID> sources;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Schema(name = "DictionaryDtoSync")
    public static class Sync {
        private DictionaryDto.Entities created;
        private DictionaryDto.Entities updated;
        private DictionaryDto.References deleted;
    }
}