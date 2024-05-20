package ru.nabokovsg.gateway.controller.laboratoryNK.documentTemplate;

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
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.TableTemplateClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.table.NewTableTemplateDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.table.UpdateTableTemplateDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/template/table",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон таблицы",
        description="API для работы с данными шаблона таблицы")
public class TableTemplateController {

    private final TableTemplateClient client;

    @Operation(summary = "Добавление нового шаблона таблицы")
    @PostMapping
    public Mono<Object> save(@RequestBody @Valid
                             @Parameter(description = "Данные шаблона таблицы") NewTableTemplateDto tableDto) {
        return client.save(tableDto);
    }

    @Operation(summary = "Изменение информации в шаблоне таблицы")
    @PatchMapping
    public Mono<Object> update(@RequestBody @Valid
                               @Parameter(description = "Данные шаблона таблицы") UpdateTableTemplateDto tableDto) {
        return client.update(tableDto);
    }

    @Operation(summary = "Получить таблицу")
    @GetMapping("/{id}")
    public Mono<Object> get(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.get(id);
    }

    @Operation(summary = "Удалить таблицу")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.delete(id);
    }
}