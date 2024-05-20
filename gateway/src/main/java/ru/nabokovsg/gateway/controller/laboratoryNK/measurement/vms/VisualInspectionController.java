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
import ru.nabokovsg.gateway.client.laboratoryNK.measurement.VisualInspectionClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.vms.visualInspection.NewVisualInspectionDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.vms.visualInspection.UpdateVisualInspectionDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/measurement/inspection",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные визуального осмотра элементов оборудования",
        description="API для работы с данными визуального осмотра элементов оборудования")
public class VisualInspectionController {

    private final VisualInspectionClient client;

    @Operation(summary = "Добавить данные визуального осмотра")
    @PostMapping
    public Mono<Object> save(@RequestBody @Valid @Parameter(name = "Данные визуального осмотра")
                                                        NewVisualInspectionDto inspectionDto) {
        return client.save(inspectionDto);
    }

    @Operation(summary = "Измененить данные визуального осмотра")
    @PatchMapping
    public Mono<Object> update(@RequestBody @Valid @Parameter(name = "Данные визуального осмотра")
                                                        UpdateVisualInspectionDto inspectionDto) {
        return client.update(inspectionDto);
    }

    @Operation(summary = "Получить данные визуального осмотра элементов оборудования")
    @GetMapping("/{id}")
    public Flux<Object> getAll(@PathVariable @NotNull @Positive
                                             @Parameter(name = "Индентификатор записи в журнале задач") Long id) {
        return client.getAll(id);
    }

    @Operation(summary = "Удалить данные визуального осмотра")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.delete(id);
    }
}