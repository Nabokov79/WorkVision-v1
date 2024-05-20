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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.report.ProtocolReportTemplateClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.report.reportProtocol.NewProtocolReportTemplateDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.report.reportProtocol.UpdateProtocolReportTemplateDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/template/report/protocol",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон протокола отчета",
        description="API для работы с данными шаблона протокола отчета")
public class ProtocolReportTemplateController {

    private final ProtocolReportTemplateClient client;

    @Operation(summary = "Шаблон нового протокола")
    @PostMapping
    public Mono<Object> save(@RequestBody @Valid @Parameter(name = "Шаблон протокола в составе отчета")
                                                            NewProtocolReportTemplateDto protocolDto) {
        return client.save(protocolDto);
    }

    @Operation(summary = "Изменение шаблона протокола")
    @PatchMapping
    public Mono<Object> update(@RequestBody @Valid @Parameter(name = "Шаблон протокола в составе отчета")
                                                            UpdateProtocolReportTemplateDto protocolDto) {
        return client.update(protocolDto);
    }

    @Operation(summary = "Получить шаблон протоколов")
    @GetMapping("/{id}")
    public Mono<Object> get(@PathVariable @NotNull @Positive @Parameter(description = "Индентификатор") Long id) {
        return client.get(id);
    }

    @Operation(summary = "Получить список протоколов раздела")
    @GetMapping("/all/{id}")
    public Flux<Object> getAll(@PathVariable @NotNull @Positive
                                   @Parameter(description = "Индентификатор раздела") Long id) {
        return client.getAll(id);
    }

    @Operation(summary = "Удалить шаблон протокола")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(description = "Индентификатор") Long id) {
        return client.delete(id);
    }
}