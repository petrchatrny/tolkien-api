package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.repository;

import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ILanguageRepository extends JpaRepository<Language, UUID> {
}
