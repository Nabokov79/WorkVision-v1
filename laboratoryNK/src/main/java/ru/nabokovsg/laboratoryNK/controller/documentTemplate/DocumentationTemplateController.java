package ru.nabokovsg.laboratoryNK.controller.documentTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentationTemplate.DocumentationTemplateDto;
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

    @Operation(summary = "Добавление шаблона нормативно-технической документации")
    @PostMapping
    public ResponseEntity<List<ResponseDocumentationTemplateDto>> save(
                                                          @RequestBody @Parameter(description = "Данные документации")
                                                                        List<DocumentationTemplateDto> templatesDto) {
        return ResponseEntity.ok().body(service.save(templatesDto));
    }

    @Operation(summary = "Изменение шаблона нормативно-технической документации")
    @PatchMapping
    public ResponseEntity<ResponseDocumentationTemplateDto> update(
                                                          @RequestBody @Parameter(description = "Данные документации")
                                                                       DocumentationTemplateDto templateDto) {
        return ResponseEntity.ok().body(service.update(templateDto));
    }

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