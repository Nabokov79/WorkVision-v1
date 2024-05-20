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
import ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.DocumentationTemplateClient;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/template/documentation",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон нормативно-технической документации",
        description="API для работы с данными шаблона нормативно-технической документации")
public class DocumentationTemplateController {

    private final DocumentationTemplateClient client;

    @Operation(summary = "Получить шаблоны нормативно-технической документации")
    @GetMapping
    public Flux<Object> getAll() {
        return client.getAll();
    }

    @Operation(summary = "Удалить шаблон нормативно-технической документации")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.delete(id);
    }
}