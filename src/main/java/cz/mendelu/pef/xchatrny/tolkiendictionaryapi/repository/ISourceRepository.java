package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.repository;

import cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ISourceRepository extends JpaRepository<Source, UUID> {
}
