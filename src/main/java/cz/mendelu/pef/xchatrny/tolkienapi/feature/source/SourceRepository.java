package cz.mendelu.pef.xchatrny.tolkienapi.feature.source;

import cz.mendelu.pef.xchatrny.tolkienapi.shared.repository.SoftDeleteRepository;
import cz.mendelu.pef.xchatrny.tolkienapi.shared.repository.SyncRepository;

import java.util.UUID;

/**
 * abstract repository for work with Source entity
 */
public interface SourceRepository extends
        SoftDeleteRepository<Source, UUID>,
        SyncRepository<Source> {
}
