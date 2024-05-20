package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.DocumentTable;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.TableTemplate;

public interface CellTableService {

    void saveEquipmentInspection(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal);

    void saveEquipmentRepair(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal);

    void saveReferencePointMeasurement(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal);

    void saveControlPointMeasurement(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal);

    void saveHardnessMeasurement(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal);

    void saveUTMeasurement(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal);

    void saveVMSMeasurement(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal);
}