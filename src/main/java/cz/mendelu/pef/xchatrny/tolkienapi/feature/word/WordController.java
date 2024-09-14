package cz.mendelu.pef.xchatrny.tolkienapi.feature.word;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Words")
@RestController
@RequestMapping("/word")
@RequiredArgsConstructor
public class WordController {

    private final WordService service;

    @GetMapping
    @Operation(summary = "Get list of words")
    public List<WordDto.Response> getWords() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new word")
    public WordDto.Response createWord(@RequestBody @Valid WordDto.Create dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update existing word by its id")
    public WordDto.Response updateWord(@PathVariable UUID id, @RequestBody @Valid WordDto.Update dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete existing word by its id")
    public void deleteWord(@PathVariable UUID id) {
        service.delete(id);
    }
}