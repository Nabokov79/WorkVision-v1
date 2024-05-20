package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.report;

import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocument;

public interface ReportService {

    void save(SurveyJournal surveyJournal, DiagnosticDocument document);
}