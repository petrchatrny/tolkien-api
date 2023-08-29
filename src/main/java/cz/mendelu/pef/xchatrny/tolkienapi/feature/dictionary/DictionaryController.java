package cz.mendelu.pef.xchatrny.tolkienapi.feature.dictionary;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.dictionary.dto.DictionaryDto;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.dictionary.dto.SyncDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "dictionaries")
@RestController
@RequestMapping("/v1/dictionaries")
public class DictionaryController {
    private final DictionaryService service;

    public DictionaryController(DictionaryService service) {
        this.service = service;
    }

    @GetMapping("/")
    @Operation(summary = "Download dictionaries")
    DictionaryDto.Entities download() {
        return service.download();
    }

    @GetMapping("/sync")
    @Operation(summary = "Synchronize dictionaries")
    SyncDto sync(@RequestParam @Parameter(description = "unix time in millis") Long lastSync) {
        return service.sync(lastSync);
    }
}
