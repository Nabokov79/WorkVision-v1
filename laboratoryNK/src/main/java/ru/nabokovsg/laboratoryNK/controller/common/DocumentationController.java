package ru.nabokovsg.laboratoryNK.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.documentation.DocumentationDto;
import ru.nabokovsg.laboratoryNK.service.common.DocumentationService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/documentation",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Нормативно-техническая документация",
        description="API для работы с данными нормативно-технической документации")
public class DocumentationController {

    private final DocumentationService service;

    @Operation(summary = "Добавление данных документа")
    @PostMapping
    public ResponseEntity<DocumentationDto> save(
            @RequestBody @Parameter(description = "Документ") DocumentationDto documentationDto) {
        return ResponseEntity.ok().body(service.save(documentationDto));
    }

    @Operation(summary = "Изменение данных документа")
    @PatchMapping
    public ResponseEntity<DocumentationDto> update(
            @RequestBody @Parameter(description = "Документ") DocumentationDto documentationDto) {
        return ResponseEntity.ok().body(service.update(documentationDto));
    }

    @Operation(summary = "Получение данных документов")
    @GetMapping("/all")
    public ResponseEntity<List<DocumentationDto>> getAll(
                                                  @RequestParam(name = "id", required = false)
                                                  @Parameter(description = "Индентификаторы документов") List<Long> ids
                                                , @RequestParam(name = "number", required = false)
                                                  @Parameter(description = "Номер документа") String number
                                                , @RequestParam(name = "title", required = false)
                                                  @Parameter(description = "Название документа") String title) {
        return ResponseEntity.ok().body(service.getAll(ids, number, title));
    }

    @Operation(summary = "Удаление данных документа")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Документ успешно удален.");
    }
}