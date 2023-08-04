package cz.mendelu.pef.xchatrny.tolkienapi.feature.language;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.dto.LanguageDto;
import cz.mendelu.pef.xchatrny.tolkienapi.shared.service.CrudService;

import java.util.UUID;

public interface LanguageService extends CrudService<UUID, LanguageDto.Create, LanguageDto.Update, LanguageDto.Response> {
}
