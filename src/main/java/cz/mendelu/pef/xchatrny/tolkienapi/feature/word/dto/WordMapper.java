package cz.mendelu.pef.xchatrny.tolkienapi.feature.word.dto;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.Word;
import cz.mendelu.pef.xchatrny.tolkienapi.util.DateTimeUtil;

import java.util.UUID;
import java.util.function.Function;

public class WordMapper implements Function<Word, WordDTO> {
    @Override
    public WordDTO apply(Word word) {
        UUID sourceId = null;

        if (word.getSource() != null) {
            sourceId = word.getSource().getId();
        }

        return new WordDTO(
                word.getId(),
                word.getCzechMeaning(),
                word.getTranslation(),
                word.getTengwar(),
                DateTimeUtil.localDateTimeToUnix(word.getCreatedAt()),
                word.getLanguage().getId(),
                sourceId
        );
    }
}
