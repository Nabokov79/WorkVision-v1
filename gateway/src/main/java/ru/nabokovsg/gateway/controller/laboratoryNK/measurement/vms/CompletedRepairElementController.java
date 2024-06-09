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
import ru.nabokovsg.gateway.client.laboratoryNK.measurement.CompletedRepairElementClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.vms.completedRepairElement.NewCompletedRepairElementDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/measurement/repair",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Ремонт элементов оборудования",
        description="API для работы с данными ремонта элементов оборудования")
public class CompletedRepairElementController {

    private final CompletedRepairElementClient client;

    @Operation(summary = "Добавить выполненный ремонт элемента")
    @PostMapping
    public Mono<Object> save(@RequestBody @Valid @Parameter(description = "Выполненный ремонт элемента")
                                                               NewCompletedRepairElementDto repairDto) {
        return client.save(repairDto);
    }

    @Operation(summary = "Получить выполненные ремонты элементов оборудования по записи журнала обследований")
    @GetMapping("/{id}")
    public Flux<Object> getAll(@PathVariable @NotNull @Positive
                                             @Parameter(name = "Индентификатор записи в журнале задач") Long id) {
        return client.getAll(id);
    }

    @Operation(summary = "Удалить ремонт элемента")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.delete(id);
    }
}