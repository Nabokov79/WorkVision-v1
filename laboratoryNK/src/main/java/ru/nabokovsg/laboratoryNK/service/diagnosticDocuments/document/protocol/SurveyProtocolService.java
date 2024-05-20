package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.protocol;

import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocument;

public interface SurveyProtocolService {

    void save(SurveyJournal surveyJournal, DiagnosticDocument document);
}