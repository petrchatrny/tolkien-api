package cz.mendelu.pef.xchatrny.tolkienapi.feature.word;

import cz.mendelu.pef.xchatrny.tolkienapi.common.architecture.BaseRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class WordRepository extends BaseRepository<UUID, Word> {

    public WordRepository(EntityManager entityManager) {
        super(entityManager, Word.class);
    }
}
