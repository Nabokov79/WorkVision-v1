package ru.nabokovsg.gateway.controller.laboratoryNK.measurement.vms;

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
import ru.nabokovsg.gateway.client.laboratoryNK.measurement.DefectMeasurementClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.vms.defectMeasurement.NewDefectMeasurementDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.vms.defectMeasurement.UpdateDefectMeasurementDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/measurement/defect",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные измерений дефектов элементов, подэлементов оборудования",
        description="API для работы с данными измерений дефектов элементов, подэлементов оборудования")
public class DefectMeasurementController {

    private final DefectMeasurementClient client;

    @Operation(summary = "Добавить данные измеренного дефекта")
    @PostMapping
    public Mono<Object> save(@RequestBody @Valid @Parameter(name = "Данные измеренного дефекта")
                                                       NewDefectMeasurementDto measurementDto) {
        return client.save(measurementDto);
    }

    @Operation(summary = "Изменить данные измеренного дефекта")
    @PatchMapping
    public Mono<Object> update(@RequestBody @Valid @Parameter(name = "Данные измеренного дефекта")
                                                      UpdateDefectMeasurementDto measurementDto) {
        return client.update(measurementDto);
    }

    @Operation(summary = "Получить данные измеренных дефектов оборудования")
    @GetMapping("/{id}")
    public Flux<Object> getAll(@PathVariable @NotNull @Positive
                                             @Parameter(name = "Индентификатор записи в журнале задач") Long id) {
        return client.getAll(id);
    }

    @Operation(summary = "Удалить измеренный дефект")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return  client.delete(id);
    }
}