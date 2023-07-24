package cz.mendelu.pef.xchatrny.tolkienapi.feature.word;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.word.dto.WordDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@Tag(name = "words")
@RestController
@RequestMapping("/api/words")
public class WordController {
    private final WordService service;

    public WordController(WordService service) {
        this.service = service;
    }

    @GetMapping("/")
    @Operation(summary = "Get list of words")
    Collection<WordDTO> getWords() {
        return service.getAllWords();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new word")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Word created"),
            @ApiResponse(responseCode = "403", description = "Unauthenticated access"),
            @ApiResponse(responseCode = "404", description = "Language or Source not found")
    })
    WordDTO createWord(@RequestBody WordDTO dto) {
        return service.createWord(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update existing word by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Word updated"),
            @ApiResponse(responseCode = "403", description = "Unauthenticated access"),
            @ApiResponse(responseCode = "404", description = "Word or Language or Source not found")
    })
    WordDTO updateWord(@PathVariable UUID id, @RequestBody WordDTO dto) {
        return service.updateWord(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete existing word by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Word deleted"),
            @ApiResponse(responseCode = "403", description = "Unauthenticated access"),
            @ApiResponse(responseCode = "404", description = "Word not found")
    })
    void deleteWord(@PathVariable UUID id) {
        service.deleteWord(id);
    }
}