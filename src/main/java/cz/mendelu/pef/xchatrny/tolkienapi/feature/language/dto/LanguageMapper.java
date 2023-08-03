package cz.mendelu.pef.xchatrny.tolkienapi.feature.language.dto;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.Language;
import cz.mendelu.pef.xchatrny.tolkienapi.util.DateTimeUtil;

import java.util.function.Function;

public class LanguageMapper implements Function<Language, LanguageDTO> {
    @Override
    public LanguageDTO apply(Language language) {
        return new LanguageDTO(language.getId(), language.getName(), DateTimeUtil.localDateTimeToUnix(language.getCreatedAt()));
    }
}
