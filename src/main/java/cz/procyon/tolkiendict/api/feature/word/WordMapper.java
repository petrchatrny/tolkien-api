package cz.procyon.tolkiendict.api.feature.word;

import cz.procyon.tolkiendict.api.common.architecture.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WordMapper extends BaseMapper<
        Word,
        WordDto.Create,
        WordDto.Update,
        WordDto.Response> {

    @Override
    @Mapping(source = "language.id", target = "languageId")
    @Mapping(source = "source.id", target = "sourceId")
    WordDto.Response toResponse(Word word);
}
