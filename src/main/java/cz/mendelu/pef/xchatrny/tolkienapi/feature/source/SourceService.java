package cz.mendelu.pef.xchatrny.tolkienapi.feature.source;

import cz.mendelu.pef.xchatrny.tolkienapi.common.architecture.BaseService;
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
