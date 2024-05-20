package ru.nabokovsg.laboratoryNK.controller.measurement.vms;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.completedRepairElement.CompletedRepairElementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.completedRepairElement.ResponseCompletedRepairElementDto;
import ru.nabokovsg.laboratoryNK.service.measurement.vms.CompletedRepairElementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/measurement/repair",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Ремонт элементов оборудования",
        description="API для работы с данными ремонта элементов оборудования")
public class CompletedRepairElementController {

    private final CompletedRepairElementService service;

    @Operation(summary = "Добавить выполненный ремонт элемента")
    @PostMapping
    public ResponseEntity<ResponseCompletedRepairElementDto> save(
            @RequestBody @Parameter(description = "Выполненный ремонт элемента") CompletedRepairElementDto repairDto) {
        return ResponseEntity.ok().body(service.save(repairDto));
    }

    @Operation(summary = "Изменить выполненный ремонт элемента")
    @PatchMapping
    public ResponseEntity<ResponseCompletedRepairElementDto> update(
            @RequestBody @Parameter(description = "Выполненный ремонт элемента") CompletedRepairElementDto repairDto) {
        return ResponseEntity.ok().body(service.update(repairDto));
    }

    @Operation(summary = "Получить выполненные ремонты элементов оборудования по записи журнала обследований")
    @GetMapping("/{id}")
    public ResponseEntity<List<ResponseCompletedRepairElementDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор записи в журнале задач") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить ремонт элемента")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Выполненный ремонт элемента оборудования успешно удален.");
    }
}