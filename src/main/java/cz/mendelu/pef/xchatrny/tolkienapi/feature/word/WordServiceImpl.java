package cz.mendelu.pef.xchatrny.tolkienapi.feature.word;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.Language;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.LanguageRepository;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.Source;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.SourceRepository;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.dto.WordDto;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.dto.WordMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
        List<Word> words = wordRepository.findAll();
        return wordMapper.toResponseListDto(words);
    }

    @Override
    public WordDto.Response findById(UUID id) {
        Word word = wordRepository.findById(id);
        return wordMapper.toResponseDto(word);
    }

    @Transactional
    @Override
    public WordDto.Response create(WordDto.Create dto) {
        Language language = languageRepository.findById(dto.getLanguageId());
        if (language == null) {
            throw new EntityNotFoundException();
        }

        Source source = null;
        if (dto.getSourceId() != null) {
            source = sourceRepository.findById(dto.getSourceId());

            if (source == null) {
                throw new EntityNotFoundException();
            }
        }

        Word word = Word.builder()
                        .czechMeaning(dto.getCzechMeaning())
                        .translation(dto.getTranslation())
                        .tengwar(dto.getTengwar())
                        .language(language)
                        .source(source)
                        .build();

        wordRepository.create(word);

        return wordMapper.toResponseDto(word);
    }

    @Transactional
    @Override
    public WordDto.Response update(UUID id, WordDto.Update dto) {
        Word word = wordRepository.findById(id);
        Language language = languageRepository.findById(dto.getLanguageId());
        if (language == null || word == null) {
            throw new EntityNotFoundException();
        }

        Source source = null;
        if (dto.getSourceId() != null) {
            source = sourceRepository.findById(dto.getSourceId());
            if (source == null) {
                throw new EntityNotFoundException();
            }
        }

        word.setCzechMeaning(dto.getCzechMeaning());
        word.setTranslation(dto.getTranslation());
        word.setTengwar(dto.getTengwar());
        word.setLanguage(language);
        word.setSource(source);

        return wordMapper.toResponseDto(word);
    }

    @Transactional
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
