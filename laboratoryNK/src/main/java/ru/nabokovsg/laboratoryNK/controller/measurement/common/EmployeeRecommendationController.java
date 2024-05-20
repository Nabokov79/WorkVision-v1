package ru.nabokovsg.laboratoryNK.controller.measurement.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.employeeRecommendation.EmployeeRecommendationDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.employeeRecommendation.ResponseEmployeeRecommendationDto;
import ru.nabokovsg.laboratoryNK.service.measurement.common.EmployeeRecommendationService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/recommendation/employee",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Рекомендации сотрудников по устранению дефектов элементов оборудования, замечаний",
        description="API для работы с данными рекомендациями по устранению дефектов элементов оборудования, замечаний")
public class EmployeeRecommendationController {

    private final EmployeeRecommendationService service;

    @Operation(summary = "Новая рекомендация для раздела отчета")
    @PostMapping
    public ResponseEntity<ResponseEmployeeRecommendationDto> save(
            @RequestBody @Parameter(name = "Рекомендация") EmployeeRecommendationDto recommendationDto) {
        return ResponseEntity.ok().body(service.save(recommendationDto));
    }

    @Operation(summary = "Изменение рекомендации")
    @PatchMapping
    public ResponseEntity<ResponseEmployeeRecommendationDto> update(
            @RequestBody @Parameter(name = "Рекомендация") EmployeeRecommendationDto recommendationDto) {
        return ResponseEntity.ok().body(service.update(recommendationDto));
    }

    @Operation(summary = "Получить рекомендации диагностируемого оборудования")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ResponseEmployeeRecommendationDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор диагностируемого оборудования") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить рекомендацию")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Рекомендация успешно удалена.");
    }
}