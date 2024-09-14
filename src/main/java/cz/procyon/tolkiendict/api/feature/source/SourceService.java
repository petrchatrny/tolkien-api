package cz.procyon.tolkiendict.api.feature.source;

import cz.procyon.tolkiendict.api.common.architecture.BaseService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SourceService extends BaseService<
        UUID,
        Source,
        SourceDto.Create,
        SourceDto.Update,
        SourceDto.Response,
        SourceRepository,
        SourceMapper> {
}
