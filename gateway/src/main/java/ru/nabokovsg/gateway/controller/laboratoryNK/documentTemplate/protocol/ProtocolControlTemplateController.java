package ru.nabokovsg.gateway.controller.laboratoryNK.documentTemplate.protocol;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.protocol.ProtocolControlTemplateClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.protocolControl.NewProtocolControlTemplateDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.protocolControl.UpdateProtocolControlTemplateDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/template/protocol/control",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон протокола по контролю качества",
        description="API для работы с данными шаблона протокола по контролю качества")
public class ProtocolControlTemplateController {

    private final ProtocolControlTemplateClient client;

    @Operation(summary = "Данные нового шаблона протокола/заключения по контролю качества")
    @PostMapping
    public Mono<Object> save(@RequestBody @Valid @Parameter(description = "Шаблон протокола по контролю качества")
                                                                      NewProtocolControlTemplateDto protocolDto) {
        return client.save(protocolDto);
    }

    @Operation(summary = "Данные для изменения информации в шаблоне протокола/заключения по контролю качества")
    @PatchMapping
    public Mono<Object> update(@RequestBody @Valid@Parameter(description = "Шаблон протокола по контролю качества")
                                                                    UpdateProtocolControlTemplateDto protocolDto) {
        return client.update(protocolDto);
    }

    @Operation(summary = "Получить данные шаблона протокола/заключения по контролю качества")
    @GetMapping("/{id}")
    public Mono<Object> get(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.get(id);
    }

    @Operation(summary = "Получить краткие данные шаблонов протоколов/заключений по контролю качества")
    @GetMapping
    public Flux<Object> getAll() {
        return client.getAll();
    }

    @Operation(summary = "Удаление шаблона протокола/заключения по контролю качества")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(description = "Индентификатор") Long id) {
        return client.delete(id);
    }
}
