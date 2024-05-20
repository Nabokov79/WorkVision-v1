package ru.nabokovsg.laboratoryNK.controller.norms;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.norms.defects.DefectDto;
import ru.nabokovsg.laboratoryNK.dto.norms.defects.ResponseDefectDto;
import ru.nabokovsg.laboratoryNK.dto.norms.defects.ResponseShortDefectDto;
import ru.nabokovsg.laboratoryNK.service.norms.DefectService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/norms/defect",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Дефекты элемента оборудования",
        description="API для работы с дефектами элементов оборудования")
public class DefectController {

    private final DefectService service;

    @Operation(summary = "Добавление новых дефектов оборудования")
    @PostMapping
    public ResponseEntity<ResponseDefectDto> save(
                                                @RequestBody @Parameter(description = "Дефект") DefectDto defectDto) {
        return ResponseEntity.ok().body(service.save(defectDto));
    }

    @Operation(summary = "Изменение данных дефектов оборудования")
    @PatchMapping
    public ResponseEntity<ResponseDefectDto> update(
                                                 @RequestBody @Parameter(description = "Дефект") DefectDto defectDto) {
        return ResponseEntity.ok().body(service.update(defectDto));
    }

    @Operation(summary = "Получить дефект")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDefectDto> get(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }
    @Operation(summary = "Получить дефекты")
    @GetMapping
    public ResponseEntity<List<ResponseShortDefectDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удалить дефект")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные дефекта успешно удалены.");
    }
}