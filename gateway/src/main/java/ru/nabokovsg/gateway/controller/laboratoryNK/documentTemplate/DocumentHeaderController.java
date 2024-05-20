package ru.nabokovsg.gateway.controller.laboratoryNK.documentTemplate;

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
import ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.DocumentHeaderClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.documentHeader.NewDocumentHeaderForProtocolDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.documentHeader.NewDocumentHeaderForReportDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.documentHeader.UpdateDocumentHeaderForProtocolDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.documentHeader.UpdateDocumentHeaderForReportDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/template/document/header",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Заголовк документа",
        description="API для работы с данными заголовка протоколов и титульных листов отчетов")
public class DocumentHeaderController {

    private final DocumentHeaderClient client;

    @Operation(summary = "Добавление нового заголовка")
    @PostMapping("/report")
    public Mono<Object> saveForReport(@RequestBody @Valid
                                      @Parameter(description = "Данные для формирования заголовка отчета")
                                          NewDocumentHeaderForReportDto headerDto) {
        return client.saveForReport(headerDto);
    }

    @Operation(summary = "Изменение информации в заголовка")
    @PatchMapping("/report")
    public Mono<Object> updateForReport(@RequestBody @Valid
                          @Parameter(description = "Данные для формирования заголовка отчета")
                                            UpdateDocumentHeaderForReportDto headerDto) {
        return client.updateForReport(headerDto);
    }

    @Operation(summary = "Добавление нового заголовка")
    @PostMapping("/protocol")
    public Mono<Object> saveForProtocol(@RequestBody @Valid
                             @Parameter(description = "Данные для формирования заголовка протокола")
                                 NewDocumentHeaderForProtocolDto headerDto) {
        return client.saveForProtocol(headerDto);
    }

    @Operation(summary = "Изменение информации в заголовка")
    @PatchMapping("/protocol")
    public Mono<Object> updateForProtocol(@RequestBody @Valid
                               @Parameter(description = "Данные для формирования заголовка протокола")
                                   UpdateDocumentHeaderForProtocolDto headerDto) {
        return client.updateForProtocol(headerDto);
    }

    @Operation(summary = "Получить заголовки документа")
    @GetMapping("/{id}")
    public Flux<Object> getAll(@PathVariable @NotNull @Positive
                                   @Parameter(name = "Индентификатор типа документа") Long id) {
        return client.getAll(id);
    }

    @Operation(summary = "Удалить заголовок")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.delete(id);
    }
}