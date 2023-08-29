package cz.mendelu.pef.xchatrny.tolkienapi.feature.word;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.dto.WordDto;
import cz.mendelu.pef.xchatrny.tolkienapi.shared.service.CrudService;
import cz.mendelu.pef.xchatrny.tolkienapi.shared.service.SyncService;

import java.util.UUID;

public interface WordService extends
        CrudService<UUID, WordDto.Create, WordDto.Update, WordDto.Response>,
        SyncService<Word> {
}