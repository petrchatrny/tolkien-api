package cz.mendelu.pef.xchatrny.tolkienapi.feature.source;

import cz.mendelu.pef.xchatrny.tolkienapi.shared.repository.BaseRelationalRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SourceRepositoryImpl
        extends BaseRelationalRepository<Source, UUID>
        implements SourceRepository {

    /**
     * AllArgsConstructor used for dependency injection
     *
     * @param entityManager hibernate entity manager
     */
    public SourceRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Source.class);
    }
}
