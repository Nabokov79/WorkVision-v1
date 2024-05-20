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
import ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.ConclusionTemplateClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.conclusion.NewConclusionTemplateDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.conclusion.UpdateConclusionTemplateDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/template/conclusion",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблоны заключений к документам",
        description="API для работы с шаблонами заключений к документам")
public class ConclusionTemplateController {

    private final ConclusionTemplateClient client;

    @Operation(summary = "Добавление нового шаблона заключения")
    @PostMapping
    public Mono<Object> save(
            @RequestBody @Valid @Parameter(description = "Шаблон заключения") NewConclusionTemplateDto conclusionDto) {
        return client.save(conclusionDto);
    }

    @Operation(summary = "Изменение шаблона заключения")
    @PatchMapping
    public Mono<Object> update(
          @RequestBody @Valid @Parameter(description = "Шаблон заключения") UpdateConclusionTemplateDto conclusionDto) {
        return client.update(conclusionDto);
    }

    @Operation(summary = "Получить шаблон заключения")
    @GetMapping("/{id}")
    public Mono<Object> get(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.get(id);
    }

    @Operation(summary = "Удалить шаблон заключений")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.delete(id);
    }
}