package ru.nabokovsg.laboratoryNK.service.common;

import ru.nabokovsg.laboratoryNK.dto.surveyJournal.ResponseSurveyJournalDto;
import ru.nabokovsg.laboratoryNK.dto.surveyJournal.SurveyJournalDto;;
import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;

import java.time.LocalDate;
import java.util.List;

public interface SurveyJournalService {

    ResponseSurveyJournalDto save(SurveyJournalDto journalDto);

    ResponseSurveyJournalDto update(SurveyJournalDto journalDto);

    SurveyJournal getById(Long id);

    List<ResponseSurveyJournalDto> getAll(LocalDate startPeriod, LocalDate endPeriod);

    void delete(Long id);
}