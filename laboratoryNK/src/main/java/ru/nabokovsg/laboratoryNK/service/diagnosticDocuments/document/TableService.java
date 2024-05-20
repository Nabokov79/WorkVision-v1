package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.DocumentTable;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.ProtocolReport;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.TableTemplate;

import java.util.Set;

public interface TableService {

    DocumentTable savaForSubsection(TableTemplate tableTemplate, SurveyJournal surveyJournal);

    void savaForProtocolReport(ProtocolReport protocol, Set<TableTemplate> templates, SurveyJournal surveyJournal);
}