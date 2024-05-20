package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.report;

import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Section;

import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.SectionTemplate;

public interface ProtocolReportService {

    void save(Section section, SectionTemplate sectiontemplate, SurveyJournal surveyJournal);
}