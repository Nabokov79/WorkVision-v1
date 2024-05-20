package ru.nabokovsg.laboratoryNK.controller.diagnosticDocuments;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.diagnosticDocuments.remark.RemarkDto;
import ru.nabokovsg.laboratoryNK.dto.diagnosticDocuments.remark.ResponseRemarkDto;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.common.RemarkService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/remark",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Замечания к документам по результатам обследования",
        description="API для работы с данными замечаний к выполненным документам")
public class RemarkController {

    private final RemarkService service;

    @Operation(summary = "Добавление нового замечания ")
    @PostMapping
    public ResponseEntity<ResponseRemarkDto> save(
            @RequestBody @Parameter(description = "Замечание") RemarkDto remarkDto) {
        return ResponseEntity.ok().body(service.save(remarkDto));
    }

    @Operation(summary = "Изменение данных замечания")
    @PatchMapping
    public ResponseEntity<ResponseRemarkDto> update(
            @RequestBody @Parameter(description = "Замечание") RemarkDto remarkDto) {
        return ResponseEntity.ok().body(service.update(remarkDto));
    }

    @Operation(summary = "Получение замечаний")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ResponseRemarkDto>> getAll(
                                 @PathVariable @Parameter(description = "Индентификатор сотрудника") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удаление замечания")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Замечание успешно удалено.");
    }
}