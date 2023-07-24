package cz.mendelu.pef.xchatrny.tolkienapi.feature.source;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public interface ISourceRepository extends JpaRepository<Source, UUID> {
    @Query("SELECT s FROM Source s WHERE s.deletedAt IS NULL")
    Collection<Source> findUndeleted();

    @Query("SELECT s " +
            "FROM Source s " +
            "WHERE s.createdAt > :dateTime " +
            "  AND s.deletedAt is NULL"
    )
    Collection<Source> findCreatedAtAfter(@Param("dateTime") LocalDateTime dateTime);

    @Query("SELECT s " +
            "FROM Source s " +
            "WHERE s.updatedAt > :dateTime " +
            "  AND s.updatedAt != s.createdAt " +
            "  AND s.deletedAt is NULL "
    )
    Collection<Source> findUpdatedAtAfter(@Param("dateTime") LocalDateTime dateTime);

    @Query("SELECT s FROM Source s WHERE s.deletedAt > :dateTime")
    Collection<Source> findDeletedAtAfter(LocalDateTime dateTime);
}
