package cz.mendelu.pef.xchatrny.tolkienapi.feature.language;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.dto.LanguageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "languages")
@RestController
@RequestMapping("/v1/languages")
public final class LanguageController {
    private final LanguageService service;

    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @GetMapping("/")
    @Operation(summary = "Get list of all languages")
    List<LanguageDto.Response> getLanguages() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get one language by its id")
    LanguageDto.Response getLanguageById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping("/")
    @Operation(summary = "Create new language")
    @ResponseStatus(HttpStatus.CREATED)
    LanguageDto.Response createLanguage(@Valid @RequestBody LanguageDto.Create dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update existing language by its id")
    LanguageDto.Response updateLanguage(@PathVariable UUID id, @Valid @RequestBody LanguageDto.Update dto) {
        return service.update(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete existing language by its id")
    void deleteLanguage(@PathVariable UUID id) {
        service.delete(id);
    }
}