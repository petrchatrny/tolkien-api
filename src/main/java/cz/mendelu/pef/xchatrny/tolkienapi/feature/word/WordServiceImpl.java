package cz.mendelu.pef.xchatrny.tolkienapi.feature.word;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.LanguageRepository;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.SourceRepository;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.dto.WordDto;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.dto.WordMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;
    private final LanguageRepository languageRepository;
    private final SourceRepository sourceRepository;
    private final WordMapper wordMapper;

    public WordServiceImpl(
            WordRepository wordRepository,
            LanguageRepository languageRepository,
            SourceRepository sourceRepository,
            WordMapper wordMapper
    ) {
        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
        this.sourceRepository = sourceRepository;
        this.wordMapper = wordMapper;
    }

    @Override
    public List<WordDto.Response> findAll() {
        // TODO
        return null;
    }

    @Override
    public WordDto.Response findById(UUID id) {
        // TODO
        return null;
    }

    @Override
    public WordDto.Response create(WordDto.Create dto) {
        // TODO
        return null;
    }

    @Override
    public WordDto.Response update(UUID id, WordDto.Update dto) {
        // TODO
        return null;
    }

    @Override
    public void delete(UUID id) {
        wordRepository.delete(id);
    }

    @Override
    public List<Word> getCreatedAfter(LocalDateTime dateTime) {
        return wordRepository.findCreatedAfter(dateTime);
    }

    @Override
    public List<Word> getUpdatedAfter(LocalDateTime dateTime) {
        return wordRepository.findUpdatedAfter(dateTime);
    }

    @Override
    public List<Word> getDeletedAfter(LocalDateTime dateTime) {
        return wordRepository.findDeletedAfter(dateTime);
    }
}
