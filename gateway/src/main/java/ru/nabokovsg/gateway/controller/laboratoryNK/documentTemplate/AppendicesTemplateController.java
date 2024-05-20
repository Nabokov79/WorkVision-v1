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
import ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.AppendicesTemplateClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.appendices.NewAppendicesTemplateDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.appendices.UpdateAppendicesTemplateDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/template/appendices",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
@Tag(name="Приложения документа",
        description="API для работы с данными приложений документа")
public class AppendicesTemplateController {

    private final AppendicesTemplateClient client;

    @Operation(summary = "Сохранить новое приложение для документа")
    @PostMapping
    public Mono<Object> save(@RequestBody @Valid @Parameter(name = "Приложение") NewAppendicesTemplateDto appendicesDto) {
        return client.save(appendicesDto);
    }

    @Operation(summary = "Изменение данных приложения документа")
    @PatchMapping
    public Mono<Object> update(@RequestBody @Valid @Parameter(name = "Приложение") UpdateAppendicesTemplateDto appendicesDto) {
        return client.update(appendicesDto);
    }

    @Operation(summary = "Удалить приложение")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.delete(id);
    }
}