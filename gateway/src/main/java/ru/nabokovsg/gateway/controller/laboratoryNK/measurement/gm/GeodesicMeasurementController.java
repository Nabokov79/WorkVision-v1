package ru.nabokovsg.gateway.controller.laboratoryNK.measurement.gm;

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
import ru.nabokovsg.gateway.client.laboratoryNK.measurement.GeodesicMeasurementClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.geodesicMeasurement.NewGeodeticMeasurementEquipmentDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.geodesicMeasurement.UpdateGeodesyMeasurementDto;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/measurement/geodesic",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные измерений при выполнении геодезической съемки(нивелировании) оборудования",
       description="API для работы с данными измерений при выполнении геодезической съемки(нивелировании) оборудования")
public class GeodesicMeasurementController {

    private final GeodesicMeasurementClient client;

    @Operation(summary = "Добавить данные геодезический съемки оборудования")
    @PostMapping
    public Flux<Object> save(@RequestBody @Valid @Parameter(name = "Данные измерений геодезической съемки")
                                                      NewGeodeticMeasurementEquipmentDto measurementsDto) {
        return client.save(measurementsDto);
    }

    @Operation(summary = "Измененить данные геодезический съемки одного места измерения")
    @PatchMapping
    public Flux<Object> update(@RequestBody @Valid @Parameter(name = "Данные измерений геодезической съемки")
                               List<UpdateGeodesyMeasurementDto> measurementsDto) {
        return client.update(measurementsDto);
    }

    @Operation(summary = "Получить данные геодезических измерений по индентификатору записи журнала задач")
    @GetMapping("/{id}")
    public Flux<Object> getAll(@PathVariable @NotNull @Positive
                                             @Parameter(name = "Индентификатор оборудования") Long id) {
        return client.getAll(id);
    }

    @Operation(summary = "Удалить результаты геодезической съемки")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return  client.delete(id);
    }
}