package ru.nabokovsg.laboratoryNK.controller.equipmentDiagnosed;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.element.ElementDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.element.ResponseElementDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.element.ShortResponseElementDto;
import ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed.EquipmentElementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/equipments/elements",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Элемент",
        description="API для работы с данными элементов оборудования")
public class EquipmentElementController {

    private final EquipmentElementService service;

    @Operation(summary = "Добавление нового элемента")
    @PostMapping
    public ResponseEntity<ShortResponseElementDto> save(
                                            @RequestBody @Parameter(description = "Элемент") ElementDto elementDto) {
        return ResponseEntity.ok().body(service.save(elementDto));
    }

    @Operation(summary = "Изменение данных элемента")
    @PatchMapping
    public ResponseEntity<ShortResponseElementDto> update(
                                              @RequestBody @Parameter(description = "Элемент") ElementDto elementDto) {
        return ResponseEntity.ok().body(service.update(elementDto));
    }

    @Operation(summary = "Получить элемент")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseElementDto> get(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все элементы оборудования")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ResponseElementDto>> getAll(
                                @PathVariable @Parameter(description = "Индентификатор оборудования") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удаление элемента")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Элемент успешно удален.");
    }
}
