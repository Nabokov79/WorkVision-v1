package ru.nabokovsg.laboratoryNK.controller.documentTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.measuringToolTemplate.ResponseMeasuringToolTemplateDto;
import ru.nabokovsg.laboratoryNK.service.documentTemplate.MeasuringToolTemplateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/template/measuring/tool",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Шаблон средства измерения",
        description="API для работы с данными шаблона средства измерения")
public class MeasuringToolTemplateController {

    private final MeasuringToolTemplateService service;

    @Operation(summary = "Получение данных о средствах измерения в шаблоне")
    @GetMapping
    public ResponseEntity<List<ResponseMeasuringToolTemplateDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удалить шаблон средства измерения")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Шаблон средства измерения успешно удален.");
    }
}