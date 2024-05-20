package ru.nabokovsg.laboratoryNK.repository.diagnosticDocuments;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocument;

public interface DiagnosticDocumentRepository extends JpaRepository<DiagnosticDocument, Long> {

    DiagnosticDocument findBySurveyJournalId(Long surveyJournalId);
}