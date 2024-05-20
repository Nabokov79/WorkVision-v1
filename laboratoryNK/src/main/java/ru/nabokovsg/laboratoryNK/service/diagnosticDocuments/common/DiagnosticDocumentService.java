package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.common;

import ru.nabokovsg.laboratoryNK.dto.surveyJournal.ResponseSurveyJournalDto;
import ru.nabokovsg.laboratoryNK.dto.surveyJournal.SurveyJournalDto;
import ru.nabokovsg.laboratoryNK.dto.diagnosticDocuments.DiagnosticDocumentDto;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocument;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DocumentStatus;

import java.time.LocalDate;
import java.util.List;

public interface DiagnosticDocumentService {

    void save(SurveyJournalDto journalDto, ResponseSurveyJournalDto journal);

    void update(SurveyJournalDto journalDto, ResponseSurveyJournalDto journal);

    List<DiagnosticDocumentDto> getAll(LocalDate startPeriod, LocalDate endPeriod, boolean week, boolean month);

    String create(Long id);

   void validateByStatus(Long taskJournalId);

    DiagnosticDocument getById(Long id);

    void updateStatus(DiagnosticDocument document, DocumentStatus status);
}