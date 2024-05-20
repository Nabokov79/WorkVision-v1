package ru.nabokovsg.laboratoryNK.controller.measurement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.measurement.hardnessMeasurement.HardnessMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.hardnessMeasurement.ResponseHardnessMeasurementDto;
import ru.nabokovsg.laboratoryNK.service.measurement.hardnessMeasurement.HardnessMeasurementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/measurement/hardness",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Данные измерений твердости металла оборудования",
        description="API для работы с данными измерений твердости металла оборудования")
public class HardnessMeasurementController {

    private final HardnessMeasurementService service;

    @Operation(summary = "Добавить данные измерений твердости металла елементов, подэлементов оборудования")
    @PostMapping
    public ResponseEntity<List<ResponseHardnessMeasurementDto>> save(@RequestBody
                                                                 @Parameter(name = "Данные измерений твердости металла")
                                                                     HardnessMeasurementDto measurementDto) {
        return ResponseEntity.ok().body(service.save(measurementDto));
    }

    @Operation(summary = "Измененить дданные измерений твердости металла елементов, подэлементов оборудования")
    @PatchMapping
    public ResponseEntity<List<ResponseHardnessMeasurementDto>> update(@RequestBody
                                                                 @Parameter(name = "Данные измерений твердости металла")
                                                                 HardnessMeasurementDto measurementDto) {
        return ResponseEntity.ok().body(service.update(measurementDto));
    }

    @Operation(summary = "Получить данные данные измерений твердости металла елементов, подэлементов оборудования" +
                                                                            " по индентификатору записи журнала задач")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ResponseHardnessMeasurementDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор записи в журнале задач") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить результат измерения твердости")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Результат измерения твердости успешно удален.");
    }
}