package ru.nabokovsg.gateway.controller.laboratoryNK.documentTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.MeasuringToolTemplateClient;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/template/measuring/tool",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон средства измерения",
        description="API для работы с данными шаблона средства измерения")
public class MeasuringToolTemplateController {

    private final MeasuringToolTemplateClient client;

    @Operation(summary = "Получение данных о средствах измерения в шаблоне")
    @GetMapping
    public Flux<Object> getAll() {
        return client.getAll();
    }

    @Operation(summary = "Удалить шаблон средства измерения")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.delete(id);
    }
}