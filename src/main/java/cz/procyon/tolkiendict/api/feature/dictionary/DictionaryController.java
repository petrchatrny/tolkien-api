package cz.procyon.tolkiendict.api.feature.dictionary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Dictionaries")
@RestController
@RequestMapping("/dictionary")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService service;

    @GetMapping
    @Operation(summary = "Download dictionaries")
    DictionaryDto.Entities download() {
        return service.download();
    }

    @GetMapping("/sync")
    @Operation(summary = "Synchronize dictionaries")
    DictionaryDto.Sync sync(@RequestParam @Parameter(description = "unix time in millis") Long lastSync) {
        return service.sync(lastSync);
    }
}
