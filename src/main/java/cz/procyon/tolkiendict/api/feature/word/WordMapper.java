package cz.procyon.tolkiendict.api.feature.word;

import cz.procyon.tolkiendict.api.common.architecture.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WordMapper extends BaseMapper<
        Word,
        WordDto.Create,
        WordDto.Update,
        WordDto.Response> {
}
