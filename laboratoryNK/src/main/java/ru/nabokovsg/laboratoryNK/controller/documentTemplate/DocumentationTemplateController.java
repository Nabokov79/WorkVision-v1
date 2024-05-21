package ru.nabokovsg.laboratoryNK.controller.documentTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentationTemplate.ResponseDocumentationTemplateDto;
import ru.nabokovsg.laboratoryNK.service.documentTemplate.DocumentationTemplateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/template/documentation",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Шаблон нормативно-технической документации",
        description="API для работы с данными шаблона нормативно-технической документации")
public class DocumentationTemplateController {

    private final DocumentationTemplateService service;

    @Operation(summary = "Получить шаблоны нормативно-технической документации")
    @GetMapping
    public ResponseEntity<List<ResponseDocumentationTemplateDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удалить шаблон нормативно-технической документации")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Шаблон нормативно-технической документации успешно удален.");
    }
}