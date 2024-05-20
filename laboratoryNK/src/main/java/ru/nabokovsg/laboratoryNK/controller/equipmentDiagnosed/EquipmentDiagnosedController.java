package ru.nabokovsg.laboratoryNK.controller.equipmentDiagnosed;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipments.EquipmentDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipments.ResponseEquipmentDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipments.ResponseShortEquipmentDto;
import ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed.EquipmentDiagnosedService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/equipments",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Оборудование",
        description="API для работы с данными оборудования котельных, ЦТП")
public class EquipmentDiagnosedController {

    private final EquipmentDiagnosedService service;

    @Operation(summary = "Добавление данных оборудования")
    @PostMapping
    public ResponseEntity<ResponseShortEquipmentDto> save(@RequestBody
                                                 @Parameter(description = "Оборудование") EquipmentDto equipmentDto) {
        return ResponseEntity.ok().body(service.save(equipmentDto));
    }

    @Operation(summary = "Изменение данных оборудования")
    @PatchMapping
    public ResponseEntity<ResponseShortEquipmentDto> update(@RequestBody
                                                   @Parameter(description = "Оборудование") EquipmentDto equipmentDto) {
        return ResponseEntity.ok().body(service.update(equipmentDto));
    }

    @Operation(summary = "Получить оборудование")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEquipmentDto> get(@PathVariable @Parameter(description = "Индентификатор") Long id
    , @RequestParam(value = "full", required = false) Boolean full) {
        return ResponseEntity.ok().body(service.get(id, full));
    }

    @Operation(summary = "Получить все оборудование котельной, ЦТП")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ResponseShortEquipmentDto>> getAll(@PathVariable
                                                    @Parameter(description = "Индентификатор котельной, ЦТП") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удаление оборудования")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Оборудование успешно удалено.");
    }
}