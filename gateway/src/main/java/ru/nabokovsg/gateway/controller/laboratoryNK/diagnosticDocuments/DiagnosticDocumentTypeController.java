package ru.nabokovsg.gateway.controller.laboratoryNK.diagnosticDocuments;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.laboratoryNK.diagnosticDocuments.DiagnosticDocumentTypeClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.diagnosticDocument.diagnosticDocumentsType.NewDiagnosticDocumentTypeDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.diagnosticDocument.diagnosticDocumentsType.UpdateDiagnosticDocumentTypeDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/document/type",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Вид документа диагностики",
        description="API для работы с данными вида документа по результатам диагностики")
public class DiagnosticDocumentTypeController {

    private final DiagnosticDocumentTypeClient client;

    @Operation(summary = "Добавление нового вида документа")
    @PostMapping
    public Mono<Object> save(
                     @RequestBody @Valid @Parameter(description = "Вид документа") NewDiagnosticDocumentTypeDto documentTypeDto) {
        return client.save(documentTypeDto);
    }

    @Operation(summary = "Изменение информации в шаблоне заголовка")
    @PatchMapping
    public Mono<Object> update(
          @RequestBody @Valid @Parameter(description = "Данные шаблона заголовка") UpdateDiagnosticDocumentTypeDto documentTypeDto) {
        return client.update(documentTypeDto);
    }

    @Operation(summary = "Получить шаблон заголовка")
    @GetMapping
    public Flux<Object> getAll() {
        return client.getAll();
    }

    @Operation(summary = "Удалить шаблон заголовка")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.delete(id);
    }
}