package cz.procyon.tolkiendict.api.feature.word;

import cz.procyon.tolkiendict.api.common.architecture.BaseRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class WordRepository extends BaseRepository<UUID, Word> {

    public WordRepository(EntityManager entityManager) {
        super(entityManager, Word.class);
    }
}
