package cz.mendelu.pef.xchatrny.tolkienapi.feature.source;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.dto.SourceDto;
import cz.mendelu.pef.xchatrny.tolkienapi.shared.service.CrudService;
import cz.mendelu.pef.xchatrny.tolkienapi.shared.service.SyncService;

import java.util.UUID;

public interface SourceService extends
        CrudService<UUID, SourceDto.Create, SourceDto.Update, SourceDto.Response>,
        SyncService<Source> {
}
