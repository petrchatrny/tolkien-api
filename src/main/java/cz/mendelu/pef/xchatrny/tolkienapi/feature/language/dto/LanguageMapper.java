package cz.mendelu.pef.xchatrny.tolkienapi.feature.language.dto;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.Language;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LanguageMapper {
    Language toDomain(LanguageDto.Create dto);

    LanguageDto.Response toResponseDto(Language language);

    List<LanguageDto.Response> toResponseListDto(List<Language> language);
}