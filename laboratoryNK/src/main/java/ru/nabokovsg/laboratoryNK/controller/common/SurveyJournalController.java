package ru.nabokovsg.laboratoryNK.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.laboratoryNK.dto.surveyJournal.ResponseSurveyJournalDto;
import ru.nabokovsg.laboratoryNK.dto.surveyJournal.SurveyJournalDto;
import ru.nabokovsg.laboratoryNK.service.common.SurveyJournalService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = "/survey/journal",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Журнал оследования",
        description="API для работы с данными журнала обследований")
public class SurveyJournalController {

    private final SurveyJournalService service;

    @Operation(summary = "Добавление записи в журнал")
    @PostMapping
    public ResponseEntity<ResponseSurveyJournalDto> save(
            @RequestBody @Parameter(description = "Информация об обследовании") SurveyJournalDto journalDto) {
        return ResponseEntity.ok().body(service.save(journalDto));
    }

    @Operation(summary = "Изменение записи в журнале")
    @PatchMapping
    public ResponseEntity<ResponseSurveyJournalDto> update(
            @RequestBody @Parameter(description = "Информация об обследовании") SurveyJournalDto journalDto) {
        return ResponseEntity.ok().body(service.update(journalDto));
    }

    @Operation(summary = "Получение записей журнала")
    @GetMapping("/all")
    public ResponseEntity<List<ResponseSurveyJournalDto>> getAll(
                                                  @RequestParam(value = "startPeriod", required = false)
                                                  @Parameter(description = "Начало периода") LocalDate startPeriod
                                                , @RequestParam(value = "endPeriod", required = false)
                                                  @Parameter(description = "Окончание периода") LocalDate endPeriod) {
        return ResponseEntity.ok().body(service.getAll(startPeriod, endPeriod));
    }

    @Operation(summary = "Удаление записи в журнале")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Запись журнала успешно удалена.");
    }
}