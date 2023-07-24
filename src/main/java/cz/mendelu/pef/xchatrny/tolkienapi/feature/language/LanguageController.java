package cz.mendelu.pef.xchatrny.tolkienapi.feature.language;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.dto.LanguageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@Tag(name = "languages")
@RestController
@RequestMapping("/api/languages")
public class LanguageController {
    private final LanguageService service;

    public LanguageController(LanguageService service) {
        this.service = service;
    }


    @GetMapping("/")
    @Operation(summary = "Get list of all languages")
    Collection<LanguageDTO> getLanguages() {
        return service.getAllLanguages();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get one language by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Language or Source not found")
    })
    LanguageDTO getLanguageById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping("/")
    @Operation(summary = "Create new language")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Language created"),
            @ApiResponse(responseCode = "403", description = "Unauthenticated access")
    })
    LanguageDTO createLanguage(@RequestBody LanguageDTO dto) {
        return service.createLanguage(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update existing language by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Language updated"),
            @ApiResponse(responseCode = "403", description = "Unauthenticated access"),
            @ApiResponse(responseCode = "404", description = "Language not found")
    })
    LanguageDTO updateLanguage(@PathVariable UUID id, @RequestParam LanguageDTO language) {
        return service.updateLanguage(id, language);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete existing language by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Language deleted"),
            @ApiResponse(responseCode = "403", description = "Unauthenticated access"),
            @ApiResponse(responseCode = "404", description = "Language not found")
    })
    void deleteLanguage(@PathVariable UUID id) {
        service.deleteLanguage(id);
    }
}