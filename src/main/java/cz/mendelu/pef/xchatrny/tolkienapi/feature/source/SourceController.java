package cz.mendelu.pef.xchatrny.tolkienapi.feature.source;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.dto.SourceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@Tag(name = "sources")
@RestController
@RequestMapping("/api/sources")
public class SourceController {
    private final SourceService service;

    public SourceController(SourceService service) {
        this.service = service;
    }

    @GetMapping("/")
    @Operation(summary = "Get list of all sources")
    Collection<SourceDTO> getSources() {
        return service.getAllSources();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new source")
    SourceDTO createSource(@RequestBody SourceDTO dto) {
        return service.createSource(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update existing source by its id")
    SourceDTO updateSource(@PathVariable UUID id, @RequestBody SourceDTO dto) {
        return service.updateSource(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete existing source by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Source deleted"),
            @ApiResponse(responseCode = "403", description = "Unauthenticated access"),
            @ApiResponse(responseCode = "404", description = "Source not found")
    })
    void deleteSource(@PathVariable UUID id) {
        service.deleteSource(id);
    }
}
