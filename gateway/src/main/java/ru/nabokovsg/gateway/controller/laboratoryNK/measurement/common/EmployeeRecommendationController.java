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
import ru.nabokovsg.gateway.client.laboratoryNK.measurement.EmployeeRecommendationClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.common.employeeRecommendation.NewEmployeeRecommendationDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.common.employeeRecommendation.UpdateEmployeeRecommendationDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/measurement/recommendation/employee",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Рекомендации сотрудников по устранению дефектов элементов оборудования, замечаний",
        description="API для работы с данными рекомендациями по устранению дефектов элементов оборудования, замечаний")
public class EmployeeRecommendationController {

    private final EmployeeRecommendationClient client;

    @Operation(summary = "Новая рекомендация для раздела отчета")
    @PostMapping
    public Mono<Object> save(@RequestBody @Valid @Parameter(name = "Рекомендация")
                                 NewEmployeeRecommendationDto recommendationDto) {
        return client.save(recommendationDto);
    }

    @Operation(summary = "Изменение рекомендации")
    @PatchMapping
    public Mono<Object> update(@RequestBody @Valid @Parameter(name = "Рекомендация")
                                UpdateEmployeeRecommendationDto recommendationDto) {
        return client.update(recommendationDto);
    }

    @Operation(summary = "Получить рекомендации диагностируемого оборудования")
    @GetMapping("/{id}")
    public Flux<Object> getAll(@PathVariable @NotNull @Positive
                                            @Parameter(name = "Индентификатор диагностируемого оборудования") Long id) {
        return client.getAll(id);
    }

    @Operation(summary = "Удалить рекомендацию")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.delete(id);
    }
}