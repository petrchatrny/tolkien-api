package cz.mendelu.pef.xchatrny.tolkienapi.feature.source;

import cz.mendelu.pef.xchatrny.tolkienapi.common.architecture.BaseRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SourceRepository extends BaseRepository<UUID, Source> {

    public SourceRepository(EntityManager entityManager) {
        super(entityManager, Source.class);
    }
}
