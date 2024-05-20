package ru.nabokovsg.gateway.controller.laboratoryNK.common;

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
import ru.nabokovsg.gateway.client.laboratoryNK.common.SurveyJournalClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.common.surveyJournal.NewSurveyJournalDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.common.surveyJournal.UpdateSurveyJournalDto;

import java.time.LocalDate;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/survey/journal",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Журнал обследования",
        description="API для работы с данными журнала обследований")
public class SurveyJournalController {

    private final SurveyJournalClient client;

    @Operation(summary = "Добавление записи в журнал")
    @PostMapping
    public Mono<Object> save(
            @RequestBody @Valid @Parameter(description = "Информация об обследовании") NewSurveyJournalDto journalDto) {
        return client.save(journalDto);
    }

    @Operation(summary = "Изменение записи в журнале")
    @PatchMapping
    public Mono<Object> update(
            @RequestBody @Parameter(description = "Информация об обследовании") UpdateSurveyJournalDto journalDto) {
        return client.update(journalDto);
    }

    @Operation(summary = "Получение записей журнала")
    @GetMapping("/all")
    public Flux<Object> getAll(@RequestParam(value = "startPeriod", required = false)
                               @Parameter(description = "Начало периода") LocalDate startPeriod
                             , @RequestParam(value = "endPeriod", required = false)
                               @Parameter(description = "Окончание периода") LocalDate endPeriod) {
        return client.getAll(startPeriod, endPeriod);
    }

    @Operation(summary = "Удаление записи в журнале")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(description = "Индентификатор") Long id) {
        return client.delete(id);
    }
}