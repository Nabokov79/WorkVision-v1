package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.protocol.SurveyProtocol;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Report;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.AppendicesTemplate;

import java.util.Set;

public interface AppendicesService {

    void saveForReport(Report report, Set<AppendicesTemplate> templates);

    void saveForSurveyProtocol(SurveyProtocol protocol, Set<AppendicesTemplate> templates);
}