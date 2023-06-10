package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto;

import java.util.Collection;

public record DictionaryDTO(
        Collection<WordDTO> words,
        Collection<LanguageDTO> languages,
        Collection<SourceDTO> sources
) {
}