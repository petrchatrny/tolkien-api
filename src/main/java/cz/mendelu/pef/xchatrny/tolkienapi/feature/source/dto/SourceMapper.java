package cz.mendelu.pef.xchatrny.tolkienapi.feature.source.dto;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.Source;
import cz.mendelu.pef.xchatrny.tolkienapi.util.DateTimeUtil;

import java.util.function.Function;

public class SourceMapper implements Function<Source, SourceDTO> {

    @Override
    public SourceDTO apply(Source source) {
        return new SourceDTO(source.getId(), source.getName(), DateTimeUtil.localDateTimeToUnix(source.getCreatedAt()), source.getUrl());
    }
}
