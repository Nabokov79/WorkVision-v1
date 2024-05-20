package ru.nabokovsg.laboratoryNK.controller.equipmentDiagnosed;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.partElement.PartElementDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.partElement.ResponsePartElementDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.partElement.ShortResponsePartElementDto;
import ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed.PartElementService;
import ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed.PartElementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/equipments/elements/parts",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Подэлемент",
        description="API для работы с данными подэлементов элементов")
public class PartElementController {

    private final PartElementService service;

    @Operation(summary = "Добавление нового подэлемента")
    @PostMapping
    public ResponseEntity<ResponsePartElementDto> save(
            @RequestBody @Parameter(description = "Подэлемент") PartElementDto partElementDto) {
        return ResponseEntity.ok().body(service.save(partElementDto));
    }

    @Operation(summary = "Изменение данных подэлемента")
    @PatchMapping
    public ResponseEntity<ResponsePartElementDto> update(
            @RequestBody @Parameter(description = "Подэлемент") PartElementDto partElementDto) {
        return ResponseEntity.ok().body(service.update(partElementDto));
    }

    @Operation(summary = "Получить подэлемент")
    @GetMapping("/{id}")
    public ResponseEntity<ResponsePartElementDto> get(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все подэлементы элементы")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ShortResponsePartElementDto>> getAll(
            @PathVariable @Parameter(description = "Индентификатор элемента") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удаление подэлемента")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Подэлемент успешно удален.");
    }
}