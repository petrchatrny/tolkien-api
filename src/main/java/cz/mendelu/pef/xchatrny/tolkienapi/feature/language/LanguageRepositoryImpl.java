package cz.mendelu.pef.xchatrny.tolkienapi.feature.language;

import cz.mendelu.pef.xchatrny.tolkienapi.shared.repository.BaseRelationalRepository;
import cz.mendelu.pef.xchatrny.tolkienapi.shared.repository.SyncRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * implementation of {@link LanguageRepository} for relational database
 */
@Repository
public class LanguageRepositoryImpl
        extends BaseRelationalRepository<Language, UUID>
        implements LanguageRepository, SyncRepository<Language> {
    public LanguageRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Language.class);
    }
}