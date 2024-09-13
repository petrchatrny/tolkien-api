package cz.mendelu.pef.xchatrny.tolkienapi.feature.source;

import cz.mendelu.pef.xchatrny.tolkienapi.common.architecture.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceMapper extends BaseMapper<
        Source,
        SourceDto.Create,
        SourceDto.Update,
        SourceDto.Response> {
}