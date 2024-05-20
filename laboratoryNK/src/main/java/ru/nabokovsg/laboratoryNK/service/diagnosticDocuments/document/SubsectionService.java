package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.ProtocolReport;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Section;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ProtocolReportTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.SectionTemplate;

public interface SubsectionService {

    void saveForSection(Section section, SectionTemplate sectiontemplate, SurveyJournal surveyJournal);

    void saveForProtocolReport(ProtocolReport protocol, ProtocolReportTemplate template, SurveyJournal surveyJournal);
}