package cz.mendelu.pef.xchatrny.tolkienapi.feature.language;

import cz.mendelu.pef.xchatrny.tolkienapi.common.architecture.BaseRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class LanguageRepository extends BaseRepository<UUID, Language> {

    public LanguageRepository(EntityManager entityManager) {
        super(entityManager, Language.class);
    }
}
