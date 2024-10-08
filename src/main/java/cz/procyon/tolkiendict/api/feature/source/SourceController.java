package cz.procyon.tolkiendict.api.feature.source;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Sources")
@RestController
@RequestMapping("/source")
@RequiredArgsConstructor
public class SourceController {

    private final SourceService service;

    @GetMapping
    @Operation(summary = "Get list of all sources")
    List<SourceDto.Response> getSources() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new source")
    SourceDto.Response createSource(@RequestBody @Valid SourceDto.Create dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update existing source by its id")
    SourceDto.Response updateSource(@PathVariable UUID id, @RequestBody @Valid SourceDto.Update dto) {
        return service.update(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete existing source by its id")
    void deleteSource(@PathVariable UUID id) {
        service.delete(id);
    }
}
