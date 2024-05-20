package ru.nabokovsg.gateway.controller.laboratoryNK.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.laboratoryNK.common.DocumentationClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.common.documentation.NewDocumentationDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.common.documentation.UpdateDocumentationDto;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/documentation",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Нормативно-техническая документация",
        description="API для работы с данными нормативно-технической документации")
public class DocumentationController {

    private final DocumentationClient client;

    @Operation(summary = "Добавление данных документа")
    @PostMapping
    public Mono<Object> save(
            @RequestBody @Valid @Parameter(description = "Документ") NewDocumentationDto documentationDto) {
        return client.save(documentationDto);
    }

    @Operation(summary = "Изменение данных документа")
    @PatchMapping
    public Mono<Object> update(
            @RequestBody @Valid @Parameter(description = "Документ") UpdateDocumentationDto documentationDto) {
        return client.update(documentationDto);
    }

    @Operation(summary = "Получение данных документов")
    @GetMapping("/all")
    public Flux<Object> getAll(@RequestParam(name = "id", required = false)
                               @Parameter(description = "Индентификаторы документов") List<Long> ids
                             , @RequestParam(name = "number", required = false)
                               @Parameter(description = "Номер документа") String number
                             , @RequestParam(name = "title", required = false)
                               @Parameter(description = "Название документа") String title) {
        return client.getAll(ids, number, title);
    }

    @Operation(summary = "Удаление данных документа")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return client.delete(id);
    }
}