package ru.nabokovsg.gateway.controller.laboratoryNK.measurement.common;

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
import ru.nabokovsg.gateway.client.laboratoryNK.measurement.EquipmentRepairClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.common.equipmentRepair.NewEquipmentRepairDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.common.equipmentRepair.UpdateEquipmentRepairDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/measurement/equipment/repair",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные выполненного ремонта диагностируемого оборудования",
        description="API для работы с данными ремонта диагностируемого оборудования")
public class EquipmentRepairController {

    private final EquipmentRepairClient client;

    @Operation(summary = "Добавить данные выполненного ремонта оборудования")
    @PostMapping
    public Mono<Object> save(@RequestBody @Valid @Parameter(name = "Выполненный ремонт")
                                                      NewEquipmentRepairDto repairDto) {
        return client.save(repairDto);
    }

    @Operation(summary = "Изменить данные выполненного ремонта оборудования")
    @PatchMapping
    public Mono<Object> update(@RequestBody @Valid @Parameter(name = "Выполненный ремонт")
                                                   UpdateEquipmentRepairDto repairDto) {
        return client.update(repairDto);
    }

    @Operation(summary = "Получить данные всех выполненныхо ремонтов оборудования")
    @GetMapping("/{id}")
    public Flux<Object> getAll(@PathVariable @NotNull @Positive
                                           @Parameter(name = "Индентификатор диагностируемого оборудования") Long id) {
        return client.getAll(id);
    }

    @Operation(summary = "Удалить данные ремонта оборудования")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        return client.delete(id);
    }
}