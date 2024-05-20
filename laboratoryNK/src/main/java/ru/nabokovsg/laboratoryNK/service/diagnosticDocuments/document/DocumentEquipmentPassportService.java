package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Section;

public interface DocumentEquipmentPassportService {

    void saveForSection(Section section, SurveyJournal surveyJournal);
}