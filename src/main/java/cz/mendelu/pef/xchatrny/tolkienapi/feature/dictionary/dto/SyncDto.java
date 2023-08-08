package cz.mendelu.pef.xchatrny.tolkienapi.feature.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SyncDto {
    private DictionaryDto.Entities created;
    private DictionaryDto.Entities updated;
    private DictionaryDto.References deleted;
}