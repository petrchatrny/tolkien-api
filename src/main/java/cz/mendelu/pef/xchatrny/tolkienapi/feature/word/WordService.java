package cz.mendelu.pef.xchatrny.tolkienapi.feature.word;

import cz.mendelu.pef.xchatrny.tolkienapi.common.architecture.BaseService;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.Language;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.LanguageRepository;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.Source;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.SourceRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class WordService extends BaseService<
        UUID,
        Word,
        WordDto.Create,
        WordDto.Update,
        WordDto.Response,
        WordRepository,
        WordMapper> {

    private final LanguageRepository languageRepository;
    private final SourceRepository sourceRepository;

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

        Word word = new Word();
        word.setCzechMeaning(dto.getCzechMeaning());
        word.setTranslation(dto.getTranslation());
        word.setTengwar(dto.getTengwar());
        word.setLanguage(language);
        word.setSource(source);

        repository.create(word);
        return mapper.toResponse(word);
    }

    @Transactional
    @Override
    public WordDto.Response update(UUID id, WordDto.Update dto) {
        Word word = repository.findById(id);
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

        return mapper.toResponse(word);
    }
}
