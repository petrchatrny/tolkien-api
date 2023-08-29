package cz.mendelu.pef.xchatrny.tolkienapi.feature.word.dto;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.Source;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.dto.SourceDto;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.Word;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WordMapper {
    Word toDomain(WordDto.Create dto);

    WordDto.Response toResponseDto(Word word);

    List<SourceDto.Response> toResponseListDto(List<Source> languages);
}
