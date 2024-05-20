package ru.nabokovsg.laboratoryNK.controller.documentTemplate.protocol;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.protocolControl.ProtocolControlTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.protocolControl.ResponseProtocolControlTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.protocolControl.ShortResponseProtocolControlTemplateDto;
import ru.nabokovsg.laboratoryNK.service.documentTemplate.protocol.ProtocolControlTemplateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/template/protocol/control",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Шаблон протокола/заключения по контролю качества",
        description="API для работы с данными шаблона протокола/заключения по контролю качества")
public class ProtocolControlTemplateController {

    private final ProtocolControlTemplateService service;

    @Operation(summary = "Данные нового шаблона протокола/заключения по контролю качества")
    @PostMapping
    public ResponseEntity<ShortResponseProtocolControlTemplateDto> save(
                                            @RequestBody
                                            @Parameter(description = "Шаблон протокола/заключения по контролю качества")
                                            ProtocolControlTemplateDto protocolDto) {
        return ResponseEntity.ok().body(service.save(protocolDto));
    }

    @Operation(summary = "Данные для изменения информации в шаблоне протокола/заключения по контролю качества")
    @PatchMapping
    public ResponseEntity<ShortResponseProtocolControlTemplateDto> update(
                                            @RequestBody
                                            @Parameter(description = "Шаблон протокола/заключения по контролю качества")
                                            ProtocolControlTemplateDto protocolDto) {
        return ResponseEntity.ok().body(service.update(protocolDto));
    }

    @Operation(summary = "Получить данные шаблона протокола/заключения по контролю качества")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseProtocolControlTemplateDto> get(
                                                            @PathVariable @Parameter(name = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить краткие данные шаблонов протоколов/заключений по контролю качества")
    @GetMapping("/all")
    public ResponseEntity<List<ShortResponseProtocolControlTemplateDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удаление шаблона протокола/заключения по контролю качества")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Шаблон протокола/заключения по контролю качества удалены.");
    }
}
