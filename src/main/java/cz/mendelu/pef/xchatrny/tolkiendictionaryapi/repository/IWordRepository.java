package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.repository;

import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public interface IWordRepository extends JpaRepository<Word, UUID> {
    @Query("SELECT w FROM Word w WHERE w.deletedAt IS NULL")
    Collection<Word> findUndeleted();

    @Query("SELECT w FROM Word w WHERE w.updatedAt >= :updatedAt")
    Collection<Word> findUpdatedAtAfter(@Param("updatedAt") LocalDateTime updatedAt);
}
