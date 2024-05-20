package ru.nabokovsg.gateway.controller.laboratoryNK.common;

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
import ru.nabokovsg.gateway.client.laboratoryNK.common.MeasuringToolClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.common.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.common.measuringTool.SearchParameters;
import ru.nabokovsg.gateway.dto.laboratoryNK.common.measuringTool.UpdateMeasuringToolDto;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/instrument",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Измерительный инструмент(прибор)",
     description="API для работы с данными измерительных инструментов(приборов)")
public class MeasuringToolController {

    private final MeasuringToolClient client;

    @Operation(summary = "Добавление данных нового интструмента(прибора)")
    @PostMapping
    public Mono<Object> save(
                              @RequestBody @Valid
                              @Parameter(description = "Инструмент(прибор)") NewMeasuringToolDto measuringToolDto) {
        return client.save(measuringToolDto);
    }

    @Operation(summary = "Изменение данных инструмента(прибора)")
    @PatchMapping
    public Mono<Object> update(
                           @RequestBody @Valid
                           @Parameter(description = "Инструмент(прибор)") UpdateMeasuringToolDto measuringToolDto) {
        return client.update(measuringToolDto);
    }

    @Operation(summary = "Получение инструментов(приборов) по заданным параметрам")
    @GetMapping
    public Flux<Object> getAll(
            @RequestParam(name = "id", required = false)
            @Parameter(description = "Индентификаторы документов") List<Long> ids,
            @RequestParam(required = false) @Parameter(description = "Название") String toll,
            @RequestParam(required = false) @Parameter(description = "Модель") String model,
            @RequestParam(required = false) @Parameter(description = "Заводской номер") String workNumber,
            @RequestParam(required = false) @Parameter(description = "Дата изготовления") LocalDate manufacturing,
            @RequestParam(required = false) @Parameter(description = "Дата начала эксплуатации") LocalDate exploitation,
            @RequestParam(required = false) @Parameter(description = "Завод-изготовитель") String manufacturer,
            @RequestParam(required = false) @Parameter(description = "Метрологическая организация") String organization,
            @RequestParam(required = false) @Parameter(description = "Вид контроля") String controlType,
            @RequestParam(required = false) @Parameter(description = "Индентификатор сотрудника") Long employeeId) {
        return client.getAll(new SearchParameters(ids, toll, model, workNumber, manufacturing,
                                                                           exploitation, manufacturer, organization,
                                                                           controlType, employeeId));
    }

    @Operation(summary = "Удаление инструмента(прибора)")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор инструмента(прибора)") Long id) {
        return client.delete(id);
    }
}