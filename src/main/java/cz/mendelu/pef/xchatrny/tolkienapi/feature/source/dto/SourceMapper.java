package cz.mendelu.pef.xchatrny.tolkienapi.feature.source.dto;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.Source;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SourceMapper {
    Source toDomain(SourceDto.Create dto);

    SourceDto.Response toResponseDto(Source language);

    List<SourceDto.Response> toResponseListDto(List<Source> language);
}