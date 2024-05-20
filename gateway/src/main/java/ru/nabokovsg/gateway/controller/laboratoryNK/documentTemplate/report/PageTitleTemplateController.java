package ru.nabokovsg.gateway.controller.laboratoryNK.documentTemplate.report;

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
import ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.report.PageTitleTemplateClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.report.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.report.pageTitle.UpdatePageTitleTemplateDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/template/title-page",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон титульного листа",
        description="API для работы с данными шаблона титульного листа")
public class PageTitleTemplateController {

    private final PageTitleTemplateClient client;

    @Operation(summary = "Добавление шаблона титульного листа")
    @PostMapping
    public Mono<Object> save(@RequestBody @Valid @Parameter(description = "Данные шаблона титульного листа")
                                 NewPageTitleTemplateDto pageTitleDto) {
        return client.save(pageTitleDto);
    }

    @Operation(summary = "Изменение шаблона титульного листа")
    @PatchMapping
    public Mono<Object> update(@RequestBody @Valid @Parameter(description = "Данные шаблона титульного листа")
                                   UpdatePageTitleTemplateDto pageTitleDto) {
        return client.update(pageTitleDto);
    }

    @Operation(summary = "Получить шаблон титульнуго листа")
    @GetMapping("/{id}")
    public Mono<Object> get(@PathVariable @NotNull @Positive @Parameter(description = "Индентификатор") Long id) {
        return client.get(id);
    }

    @Operation(summary = "Удалить шаблон титульного листа")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(description = "Индентификатор") Long id) {
        return client.delete(id);
    }
}