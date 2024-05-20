package ru.nabokovsg.laboratoryNK.controller.measurement.utm;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.measurement.utm.ResponseUltrasonicThicknessMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.utm.UltrasonicThicknessMeasurementDto;
import ru.nabokovsg.laboratoryNK.service.measurement.utm.UltrasonicThicknessMeasurementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/measurement/ut",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Данные ультразвуковой толщинометрии элементов, подэлементов оборудования",
        description="API для работы с данными ультразвуковой толщинометрии элементов, подэлементов оборудования")
public class UltrasonicThicknessMeasurementController {

    private final UltrasonicThicknessMeasurementService service;

    @Operation(summary = "Добавить ультразвуковой толщинометрии")
    @PostMapping
    public ResponseEntity<ResponseUltrasonicThicknessMeasurementDto> save(@RequestBody
                                                                          @Parameter(name = "Данные измерения толщины")
                                                                    UltrasonicThicknessMeasurementDto measurementDto) {
        return ResponseEntity.ok().body(service.save(measurementDto));
    }

    @Operation(summary = "Изменить данные ультразвуковой толщинометрии")
    @PatchMapping
    public ResponseEntity<ResponseUltrasonicThicknessMeasurementDto> update(@RequestBody
                                                                           @Parameter(name = "Данные измерения толщины")
                                                                     UltrasonicThicknessMeasurementDto measurementDto) {
        return ResponseEntity.ok().body(service.update(measurementDto));
    }

    @Operation(summary = "Получить данные измеренных дефектов оборудования")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ResponseUltrasonicThicknessMeasurementDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор записи в журнале задач") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить измеренный дефект")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные ультразвуковой толщинометрии успешно удалены.");
    }
}