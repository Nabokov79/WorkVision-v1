package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.CellTable;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.DocumentTable;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.DefectMeasurement;

import java.util.List;
import java.util.Map;

public interface CellFactoryService {

    CellTable createStringNumberCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, DocumentTable table);

    CellTable createDateCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createSurveysDescriptionCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createOrganizationNameCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createRepairDescriptionCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createDocumentNumberCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createElementCell(Map<String, Integer> columnHeaders, Integer mergeLines, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createPartElementCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    List<CellTable> createDefectCell(Map<String, Integer> columnHeaders, List<DefectMeasurement> defects, int stringSequentialNumber, DocumentTable table);

    List<CellTable> createRepairElementCell(Map<String, Integer> columnHeaders, List<CompletedRepairElement> repairElements, int stringSequentialNumber, DocumentTable table);

    CellTable createVisualInspectionCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createDesignThicknessCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createMeasuredThicknessCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createMaxCorrosionCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createResidualThicknessCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createMinAllowableThicknessCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createPlaceNumberCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createHeightCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createDeviationCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createPrecipitationCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createDifferenceNeighboringPointsCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createDifferenceDiametricalPointsCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createDiameterCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);

    CellTable createHardnessCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table);
}