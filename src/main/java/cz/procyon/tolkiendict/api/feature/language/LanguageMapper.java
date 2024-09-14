package cz.procyon.tolkiendict.api.feature.language;

import cz.procyon.tolkiendict.api.common.architecture.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LanguageMapper extends BaseMapper<
        Language,
        LanguageDto.Create,
        LanguageDto.Update,
        LanguageDto.Response> {
}