package ru.nabokovsg.laboratoryNK.controller.measurement.vms;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.visualInspection.ResponseVisualInspectionDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.visualInspection.VisualInspectionDto;
import ru.nabokovsg.laboratoryNK.service.measurement.vms.VisualInspectionService;

@RestController
@RequestMapping(
        value = "/measurement/visual/inspection",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Данные визуального осмотра элементов оборудования",
        description="API для работы с данными визуального осмотра элементов оборудования")
public class VisualInspectionController {

    private final VisualInspectionService service;

    @Operation(summary = "Добавить данные визуального осмотра")
    @PostMapping
    public ResponseEntity<ResponseVisualInspectionDto> save(@RequestBody
                                                            @Parameter(name = "Данные визуального осмотра")
                                                            VisualInspectionDto inspectionDto) {
        return ResponseEntity.ok().body(service.save(inspectionDto));
    }

    @Operation(summary = "Измененить данные визуального осмотра")
    @PatchMapping
    public ResponseEntity<ResponseVisualInspectionDto> update(@RequestBody
                                                              @Parameter(name = "Данные визуального осмотра")
                                                              VisualInspectionDto inspectionDto) {
        return ResponseEntity.ok().body(service.update(inspectionDto));
    }
}