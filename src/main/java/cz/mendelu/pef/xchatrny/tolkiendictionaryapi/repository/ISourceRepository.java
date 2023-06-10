package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.repository;

import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public interface ISourceRepository extends JpaRepository<Source, UUID> {
    @Query("SELECT s FROM Source s WHERE s.deletedAt IS NULL")
    Collection<Source> findUndeleted();

    @Query("SELECT s FROM Source s WHERE s.updatedAt >= :updatedAt")
    Collection<Source> findUpdatedAtAfter(@Param("updatedAt") LocalDateTime updatedAt);
}
