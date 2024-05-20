package ru.nabokovsg.gateway.controller.laboratoryNK.documentTemplate.report;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.report.ReportTemplateClient;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/template/report",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон отчета",
        description="API для работы с данными шаблона отчета")
public class ReportTemplateController {

    private final ReportTemplateClient client;

    @Operation(summary = "Получить шаблон отчета")
    @GetMapping("/{id}")
    public Mono<Object> get(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.get(id);
    }

    @Operation(summary = "Получить все шаблоны отчетов")
    @GetMapping
    public Flux<Object> getAll() {
        return client.getAll();
    }
}