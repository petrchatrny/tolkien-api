package cz.mendelu.pef.xchatrny.tolkienapi.feature.language;

import cz.mendelu.pef.xchatrny.tolkienapi.shared.repository.SoftDeleteRepository;

import java.util.UUID;

/**
 * abstract repository for work with Language entity
 */
public interface LanguageRepository extends SoftDeleteRepository<Language, UUID> {

}
