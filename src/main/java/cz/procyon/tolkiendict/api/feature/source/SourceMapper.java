package cz.procyon.tolkiendict.api.feature.source;

import cz.procyon.tolkiendict.api.common.architecture.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceMapper extends BaseMapper<
        Source,
        SourceDto.Create,
        SourceDto.Update,
        SourceDto.Response> {
}