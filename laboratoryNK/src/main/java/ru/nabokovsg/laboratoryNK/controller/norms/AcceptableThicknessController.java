package ru.nabokovsg.laboratoryNK.controller.norms;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.norms.acceptableThickness.AcceptableThicknessDto;
import ru.nabokovsg.laboratoryNK.dto.norms.acceptableThickness.ResponseAcceptableThicknessDto;
import ru.nabokovsg.laboratoryNK.service.norms.AcceptableThicknessService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/norms/thickness",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Данные минимальных допустимых толщин стенок элементов оборудования",
        description="API для работы с данными норм минимальных допустимых стенок элементов оборудования")
public class AcceptableThicknessController {

    private final AcceptableThicknessService service;

    @Operation(summary = "Добавить новые данные допустимых толщины стенки")
    @PostMapping
    public ResponseEntity<ResponseAcceptableThicknessDto> save(
            @RequestBody @Parameter(name = "Данные допустимой толщины стенки") AcceptableThicknessDto thicknessDto) {
        return ResponseEntity.ok().body(service.save(thicknessDto));
    }

    @Operation(summary = "Изменение данных допустимых толщины стенки")
    @PatchMapping
    public ResponseEntity<ResponseAcceptableThicknessDto> update(
            @RequestBody @Parameter(name = "Данные допустимой толщины стенки") AcceptableThicknessDto thicknessDto) {
        return ResponseEntity.ok().body(service.update(thicknessDto));
    }

    @Operation(summary = "Получить данные допустимых толщин толщины стенки элементов оборудования")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ResponseAcceptableThicknessDto>> getAll(
            @PathVariable @Parameter(name = "Индентификатор типа оборудования") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удалить данные допустимых толщины стенки")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные допустимых толщины стенки элемента оборудования успешно удалены.");
    }
}