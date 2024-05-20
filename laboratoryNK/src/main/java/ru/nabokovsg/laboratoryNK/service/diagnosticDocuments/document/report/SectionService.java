package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.report;

import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Report;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ReportTemplate;

public interface SectionService {

    void save(Report report, ReportTemplate template, SurveyJournal surveyJournal);
}