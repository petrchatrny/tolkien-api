package cz.mendelu.pef.xchatrny.tolkienapi.feature.word;

import cz.mendelu.pef.xchatrny.tolkienapi.common.architecture.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WordMapper extends BaseMapper<
        Word,
        WordDto.Create,
        WordDto.Update,
        WordDto.Response> {
}
