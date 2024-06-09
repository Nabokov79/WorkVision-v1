package ru.nabokovsg.laboratoryNK.controller.vms.measurement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.defectMeasurement.DefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.defectMeasurement.ResponseDefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.service.vms.measurement.DefectMeasurementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/measurement/defect",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Данные измерений дефектов элементов, подэлементов оборудования",
        description="API для работы с данными измерений дефектов элементов, подэлементов оборудования")
public class DefectMeasurementController {

    private final DefectMeasurementService service;

    @Operation(summary = "Добавить данные измеренного дефекта")
    @PostMapping
    public ResponseEntity<ResponseDefectMeasurementDto> save(@RequestBody
                                                             @Parameter(name = "Данные измеренного дефекта")
                                                             DefectMeasurementDto defectMeasurementDto) {
        return ResponseEntity.ok().body(service.save(defectMeasurementDto));
    }

    @Operation(summary = "Получить данные измеренных дефектов оборудования")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ResponseDefectMeasurementDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор записи в журнале задач") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить измеренный дефект")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные дефекта успешно удалены.");
    }
}