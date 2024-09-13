package cz.mendelu.pef.xchatrny.tolkienapi.feature.language;

import cz.mendelu.pef.xchatrny.tolkienapi.common.architecture.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LanguageMapper extends BaseMapper<
        Language,
        LanguageDto.Create,
        LanguageDto.Update,
        LanguageDto.Response> {
}