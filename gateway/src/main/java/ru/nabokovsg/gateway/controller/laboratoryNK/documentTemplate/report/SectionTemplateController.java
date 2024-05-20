package ru.nabokovsg.gateway.controller.laboratoryNK.documentTemplate.report;

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
import ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.report.SectionTemplateClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.report.section.*;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/template/section",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон раздела отчета",
        description="API для работы с данными шаблона раздела")
public class SectionTemplateController {

    private final SectionTemplateClient client;

    @Operation(summary = "Добавить новые разделы")
    @PostMapping("/passport")
    public Mono<Object> saveWithEquipmentPassport(@RequestBody @Valid @Parameter(description = "Данные шаблона раздела")
                                                  NewSectionWithEquipmentPassportDto sectionDto) {
        return client.saveWithEquipmentPassport(sectionDto);
    }

    @Operation(summary = "Изменение данных разделов")
    @PatchMapping("/passport")
    public Mono<Object> updateWithEquipmentPassport(@RequestBody @Valid @Parameter(description = "Данные шаблона разделф")
                                                    UpdateSectionWithEquipmentPassportDto sectionDto) {
        return client.updateWithEquipmentPassport(sectionDto);
    }
    @Operation(summary = "Добавить новые разделы")
    @PostMapping("/subsection")
    public Mono<Object> saveWithSubsection(@RequestBody @Valid @Parameter(description = "Данные шаблона раздела")
                                           NewSectionWithSubsectionTemplateDto sectionDto) {
        return client.saveWithSubsection(sectionDto);
    }

    @Operation(summary = "Изменение данных разделов")
    @PatchMapping("/subsection")
    public Mono<Object> updateWithSubsection(@RequestBody @Valid @Parameter(description = "Данные шаблона разделф")
                                             UpdateSectionWithSubsectionTemplateDto sectionDto) {
        return client.updateWithSubsection(sectionDto);
    }

    @Operation(summary = "Добавить новые разделы")
    @PostMapping("/protocol")
    public Mono<Object> saveWithProtocol(@RequestBody @Valid @Parameter(description = "Данные шаблона раздела")
                                                                    NewSectionWithProtocolTemplateDto sectionDto) {
        return client.saveWithProtocol(sectionDto);
    }

    @Operation(summary = "Изменение данных разделов")
    @PatchMapping("/protocol")
    public Mono<Object> updateWithProtocol( @RequestBody @Valid @Parameter(description = "Данные шаблона разделф")
                                            UpdateSectionWithProtocolTemplateDto sectionDto) {
        return client.updateWithProtocol(sectionDto);
    }

    @Operation(summary = "Получить раздел")
    @GetMapping("/{id}")
    public Mono<Object> get(@PathVariable @NotNull @Positive @Parameter(description = "Индентификатор") Long id) {
        return client.get(id);
    }

    @Operation(summary = "Получить разделы отчета")
    @GetMapping("/all/report/{id}")
    public Flux<Object> getAll(@PathVariable @NotNull @Positive
                               @Parameter(description = "Индентификатор шаблона отчета") Long id) {
        return client.getAll(id);
    }

    @Operation(summary = "Удалить раздел")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return client.delete(id);
    }
}