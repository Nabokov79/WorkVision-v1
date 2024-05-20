package ru.nabokovsg.laboratoryNK.controller.equipmentDiagnosed;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.passport.EquipmentPassportDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.passport.ResponseEquipmentPassportDto;
import ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed.EquipmentPassportService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/equipments/passport",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Паспорт оборудования",
        description="API для работы с данными паспорта оборудования")
public class EquipmentPassportController {

    private final EquipmentPassportService service;

    @Operation(summary = "Добавление данных паспорта")
    @PostMapping
    public ResponseEntity<ResponseEquipmentPassportDto> save(@RequestBody
                                               @Parameter(description = "Пасспорт") EquipmentPassportDto passportDto) {
        return ResponseEntity.ok().body(service.save(passportDto));
    }

    @Operation(summary = "Изменение данных паспорта")
    @PatchMapping
    public ResponseEntity<ResponseEquipmentPassportDto> update(@RequestBody
                                                 @Parameter(description = "Паспорт") EquipmentPassportDto passportDto) {
        return ResponseEntity.ok().body(service.update(passportDto));
    }

    @Operation(summary = "Получить данные паспорта оборудования")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ResponseEquipmentPassportDto>> getAll(
                                       @PathVariable @Parameter(description = "Индентификатор оборудования") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удаление данных паспорта")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные паспорта успешно удалены.");
    }
}