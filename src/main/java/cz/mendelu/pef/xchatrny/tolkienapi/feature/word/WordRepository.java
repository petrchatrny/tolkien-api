package cz.mendelu.pef.xchatrny.tolkienapi.feature.word;

import cz.mendelu.pef.xchatrny.tolkienapi.shared.repository.SoftDeleteRepository;
import cz.mendelu.pef.xchatrny.tolkienapi.shared.repository.SyncRepository;

import java.util.UUID;

/**
 * abstract repository for work with Word entity
 */
public interface WordRepository extends
        SoftDeleteRepository<Word, UUID>,
        SyncRepository<Word> {
}
