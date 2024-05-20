package ru.nabokovsg.laboratoryNK.controller.norms;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.norms.geodesy.AcceptableDeviationsGeodesyDto;
import ru.nabokovsg.laboratoryNK.dto.norms.geodesy.ResponseAcceptableDeviationsGeodesyDto;
import ru.nabokovsg.laboratoryNK.service.norms.AcceptableDeviationsGeodesyService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/norms/geodesy",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Данные допустимых значений при проведении геодезии(нивелирования)",
        description="API для работы с данными норм при проведении геодезии(нивелирования)")
public class AcceptableDeviationsGeodesyController {

    private final AcceptableDeviationsGeodesyService service;

    @Operation(summary = "Новая рекомендация для раздела отчета")
    @PostMapping
    public ResponseEntity<ResponseAcceptableDeviationsGeodesyDto> save(
            @RequestBody
            @Parameter(name = "Нормы по геодезии(нивелированию)") AcceptableDeviationsGeodesyDto geodesyDto) {
        return ResponseEntity.ok().body(service.save(geodesyDto));
    }

    @Operation(summary = "Изменение рекомендации")
    @PatchMapping
    public ResponseEntity<ResponseAcceptableDeviationsGeodesyDto> update(
            @RequestBody
            @Parameter(name = "Нормы по геодезии(нивелированию)") AcceptableDeviationsGeodesyDto geodesyDto) {
        return ResponseEntity.ok().body(service.update(geodesyDto));
    }

    @Operation(summary = "Получить рекомендации по типу объекта")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ResponseAcceptableDeviationsGeodesyDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор типа оборудования") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить рекомендацию")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Нормы по геодезии(нивелированию) успешно удалены.");
    }
}