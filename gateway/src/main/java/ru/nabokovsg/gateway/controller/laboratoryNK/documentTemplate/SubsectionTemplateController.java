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
import ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.SubsectionTemplateClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.subsection.*;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/template/subsection",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон подраздела",
        description="API для работы с данными шаблона подраздела раздела")
public class SubsectionTemplateController {

    private final SubsectionTemplateClient client;

    @Operation(summary = "Добавление нового шаблона подраздела c текстом пользователя")
    @PostMapping("/text")
    public Mono<Object> saveWithUserText(@RequestBody @Valid@Parameter(description = "Шаблон подраздела")
                                                                NewSubsectionWithUseTextTemplateDto subsectionsDto) {
        return client.saveWithUserText(subsectionsDto);
    }

    @Operation(summary = "Изменение данных шаблона подраздела c текстом пользователя")
    @PatchMapping("/text")
    public Mono<Object> updateWithUserText(@RequestBody @Valid @Parameter(description = "Шаблон подраздела")
                                           UpdateSubsectionWithUseTextTemplateDto subsectionsDto) {
        return client.updateWithUserText(subsectionsDto);
    }

    @Operation(summary = "Добавление нового шаблона подраздела раздела с данными структурного подразделения")
    @PostMapping("/division")
    public Mono<Object> saveWithDivisionData(@RequestBody @Valid @Parameter(description = "Шаблон подраздела")
                                             NewSubsectionWithDivisionDataTemplateDto subsectionsDto) {
        return client.saveWithDivisionData(subsectionsDto);
    }

    @Operation(summary = "Изменение данных шаблона подраздела с данными структурного подразделения")
    @PatchMapping("/division")
    public Mono<Object> updateWithDivisionData(@RequestBody @Valid @Parameter(description = "Шаблон подраздела")
                                               UpdateSubsectionWithDivisionDataTemplateDto subsectionsDto) {
        return client.updateWithDivisionData(subsectionsDto);
    }

    @Operation(summary = "Добавление нового шаблона подраздела раздела с данными нормативно-технической документации")
    @PostMapping("/documentation")
    public Mono<Object> saveWithDocumentation(@RequestBody @Valid @Parameter(description = "Шаблон подраздела")
                                                    NewSubsectionWithDocumentationTemplateDto subsectionsDto) {
        return client.saveWithDocumentation(subsectionsDto);
    }

    @Operation(summary = "Изменение данных шаблона подраздела с данными нормативно-технической документации")
    @PatchMapping("/documentation")
    public Mono<Object> updateWithDocumentation(@RequestBody @Valid @Parameter(description = "Шаблон подраздела")
                                                UpdateSubsectionWithDocumentationTemplateDto subsectionsDto) {
        return client.updateWithDocumentation(subsectionsDto);
    }

    @Operation(summary = "Добавление нового шаблона подраздела раздела с таблицей")
    @PostMapping("/table")
    public Mono<Object> saveWithTable(@RequestBody @Valid @Parameter(description = "Шаблон подраздела")
                                                                    NewSubsectionWithTableTemplateDto subsectionsDto) {
        return client.saveWithTable(subsectionsDto);
    }

    @Operation(summary = "Изменение данных шаблона подраздела с таблицей")
    @PatchMapping("/table")
    public Mono<Object> updateWithTable(@RequestBody @Valid @Parameter(description = "Шаблон подраздела")
                                                                UpdateSubsectionWithTableTemplateDto subsectionsDto) {
        return client.updateWithTable(subsectionsDto);
    }

    @Operation(summary = "Добавление нового шаблона подраздела раздела с данными приборов измерений и средств контроля")
    @PostMapping("/measuring-tool")
    public Mono<Object> saveWithMeasuringTool(@RequestBody @Valid @Parameter(description = "Шаблон подраздела")
                                                            NewSubsectionWitMeasuringToolTemplateDto subsectionsDto) {
        return client.saveWithMeasuringTool(subsectionsDto);
    }

    @Operation(summary = "Изменение данных шаблона подраздела с данными приборов измерений и средств контроля")
    @PatchMapping("/measuring-tool")
    public Mono<Object> updateWithMeasuringTool(@RequestBody @Valid @Parameter(description = "Шаблон подраздела")
                                                        UpdateSubsectionWitMeasuringToolTemplateDto subsectionsDto) {
        return client.updateWithMeasuringTool(subsectionsDto);
    }

    @Operation(summary = "Добавление нового шаблона подраздела раздела с данными приборов измерений и средств контроля")
    @PostMapping("/conclusion")
    public Mono<Object> saveWithConclusionTemplate(@RequestBody @Valid @Parameter(description = "Шаблон подраздела")
                                              NewSubsectionWitConclusionTemplateDto subsectionsDto) {
        return client.saveWithConclusionTemplate(subsectionsDto);
    }

    @Operation(summary = "Изменение данных шаблона подраздела с данными приборов измерений и средств контроля")
    @PatchMapping("/conclusion")
    public Mono<Object> updateWithConclusionTemplate(@RequestBody @Valid @Parameter(description = "Шаблон подраздела")
                                        UpdateSubsectionWitConclusionTemplateDto subsectionsDto) {
        return client.updateWithConclusionTemplate(subsectionsDto);
    }

    @Operation(summary = "Получить данные шаблона подраздела")
    @GetMapping("/{id}")
    public Mono<Object> get(@PathVariable @NotNull @Positive @Parameter(description = "Индентификатор") Long id) {
        return client.get(id);
    }

    @Operation(summary = "Удалить подраздел")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(description = "Индентификатор") Long id) {
        return client.delete(id);
    }
}
