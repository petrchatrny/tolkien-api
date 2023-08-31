package cz.mendelu.pef.xchatrny.tolkienapi.feature.word;

import cz.mendelu.pef.xchatrny.tolkienapi.shared.repository.BaseRelationalRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class WordRepositoryImpl
        extends BaseRelationalRepository<Word, UUID>
        implements WordRepository {

    /**
     * AllArgsConstructor used for dependency injection
     *
     * @param entityManager hibernate entity manager
     */
    public WordRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Word.class);
    }
}
