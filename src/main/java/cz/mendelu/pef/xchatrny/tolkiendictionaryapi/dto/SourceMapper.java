package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.dto;

import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model.Source;
import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.util.DateTimeUtil;

import java.util.function.Function;

public class SourceMapper implements Function<Source, SourceDTO> {

    @Override
    public SourceDTO apply(Source source) {
        return new SourceDTO(source.getId(), source.getName(), DateTimeUtil.localDateTimeToUnix(source.getCreatedAt()), source.getUrl());
    }
}
