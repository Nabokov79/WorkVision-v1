package ru.nabokovsg.laboratoryNK.controller.diagnosticDocuments;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.diagnosticDocuments.DiagnosticDocumentTypeDto;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.common.DiagnosticDocumentTypeService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/document/type",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Вид документа диагностики",
        description="API для работы с данными вида документа по результатам диагностики")
public class DiagnosticDocumentTypeController {

    private final DiagnosticDocumentTypeService service;

    @Operation(summary = "Добавление нового вида документа")
    @PostMapping
    public ResponseEntity<DiagnosticDocumentTypeDto> save(
                     @RequestBody @Parameter(description = "Вид документа") DiagnosticDocumentTypeDto documentTypeDto) {
        return ResponseEntity.ok().body(service.save(documentTypeDto));
    }

    @Operation(summary = "Изменение информации в шаблоне заголовка")
    @PatchMapping
    public ResponseEntity<DiagnosticDocumentTypeDto> update(
          @RequestBody @Parameter(description = "Данные шаблона заголовка") DiagnosticDocumentTypeDto documentTypeDto) {
        return ResponseEntity.ok().body(service.update(documentTypeDto));
    }

    @Operation(summary = "Получить шаблон заголовка")
    @GetMapping
    public ResponseEntity<List<DiagnosticDocumentTypeDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удалить шаблон заголовка")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Таблица успешно удалена.");
    }
}