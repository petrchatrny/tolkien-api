package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.repository;

import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IWordRepository extends JpaRepository<Word, UUID> {
}
