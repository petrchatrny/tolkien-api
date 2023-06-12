package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto;

import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model.Language;
import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.util.DateTimeUtil;

import java.util.function.Function;

public class LanguageMapper implements Function<Language, LanguageDTO> {
    @Override
    public LanguageDTO apply(Language language) {
        return new LanguageDTO(language.getId(), language.getName(), DateTimeUtil.LocalDateTimeToUnix(language.getCreatedAt()), language.getIcon());
    }
}
