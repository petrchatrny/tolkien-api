package cz.mendelu.pef.xchatrny.tolkienapi.feature.language;

import cz.mendelu.pef.xchatrny.tolkienapi.common.architecture.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LanguageService extends BaseService<
        UUID,
        Language,
        LanguageDto.Create,
        LanguageDto.Update,
        LanguageDto.Response,
        LanguageRepository,
        LanguageMapper> {

}
