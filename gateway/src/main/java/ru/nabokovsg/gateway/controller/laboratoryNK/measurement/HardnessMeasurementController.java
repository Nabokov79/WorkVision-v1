package ru.nabokovsg.gateway.controller.laboratoryNK.measurement;

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
import ru.nabokovsg.gateway.client.laboratoryNK.measurement.HardnessMeasurementClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.hardnessMeasurement.NewHardnessMeasurementDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.hardnessMeasurement.UpdateHardnessMeasurementDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/measurement/hardness",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные измерений твердости металла оборудования",
        description="API для работы с данными измерений твердости металла оборудования")
public class HardnessMeasurementController {

    private final HardnessMeasurementClient client;

    @Operation(summary = "Добавить данные измерений твердости металла елементов, подэлементов оборудования")
    @PostMapping
    public Flux<Object> save(@RequestBody @Valid @Parameter(name = "Данные измерений твердости металла")
                                                             NewHardnessMeasurementDto measurementDto) {
        return client.save(measurementDto);
    }

    @Operation(summary = "Измененить дданные измерений твердости металла елементов, подэлементов оборудования")
    @PatchMapping
    public Flux<Object> update(@RequestBody @Valid @Parameter(name = "Данные измерений твердости металла")
                                                            UpdateHardnessMeasurementDto measurementDto) {
        return client.update(measurementDto);
    }

    @Operation(summary = "Получить данные данные измерений твердости металла елементов, подэлементов оборудования" +
                                                                            " по индентификатору записи журнала задач")
    @GetMapping("/{id}")
    public Flux<Object> getAll(@PathVariable @NotNull @Positive
                                             @Parameter(name = "Индентификатор записи в журнале задач") Long id) {
        return client.getAll(id);
    }

    @Operation(summary = "Удалить результат измерения твердости")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.delete(id);
    }
}