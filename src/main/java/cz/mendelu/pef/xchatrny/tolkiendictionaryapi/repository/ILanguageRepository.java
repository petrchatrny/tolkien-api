package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.repository;

import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public interface ILanguageRepository extends JpaRepository<Language, UUID> {
    @Query("SELECT l FROM Language l WHERE l.deletedAt IS NULL")
    Collection<Language> findUndeleted();

    @Query("SELECT l FROM Language l WHERE l.updatedAt >= :updatedAt")
    Collection<Language> findUpdatedAtAfter(@Param("updatedAt") LocalDateTime updatedAt);
}
