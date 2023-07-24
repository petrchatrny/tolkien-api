package cz.mendelu.pef.xchatrny.tolkienapi.feature.dictionary.dto;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.dto.SourceDTO;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.dto.WordDTO;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.dto.LanguageDTO;

import java.util.Collection;

public record DictionaryDTO(
        Collection<WordDTO> words,
        Collection<LanguageDTO> languages,
        Collection<SourceDTO> sources
) {
}