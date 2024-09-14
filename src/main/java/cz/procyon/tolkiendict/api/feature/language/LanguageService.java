package cz.procyon.tolkiendict.api.feature.language;

import cz.procyon.tolkiendict.api.common.architecture.BaseService;
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
