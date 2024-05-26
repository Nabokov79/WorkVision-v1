package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.CellTableMapper;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.CellTable;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.DocumentTable;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ColumnHeaderType;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.DefectMeasurement;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CellFactoryServiceImpl implements CellFactoryService {

    private final CellTableMapper mapper;

    @Override
    public CellTable createStringNumberCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.STRING_NUMBER)), stringSequentialNumber, String.valueOf(stringSequentialNumber), table);
    }

    @Override
    public CellTable createDateCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DATE)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createSurveysDescriptionCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.SURVEYS_DESCRIPTION)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createOrganizationNameCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.ORGANIZATION_NAME)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createRepairDescriptionCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.REPAIR_DESCRIPTION)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createDocumentNumberCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DOCUMENT_NUMBER)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createElementCell(Map<String, Integer> columnHeaders, Integer mergeLines, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToElementCell(mergeLines, columnHeaders.get(String.valueOf(ColumnHeaderType.ELEMENT)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createPartElementCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.PART_ELEMENT)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public List<CellTable> createDefectCell(Map<String, Integer> columnHeaders, List<DefectMeasurement> defects, int stringSequentialNumber, DocumentTable table) {
        List<CellTable> cells = new ArrayList<>();
        for(DefectMeasurement defect : defects) {
            cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DEFECTS)), stringSequentialNumber, String.join("", defect.getDefectName(), ":"), table));
            stringSequentialNumber += 1;
            if (defect.getPartName() != null) {
                cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DEFECTS)), stringSequentialNumber, String.join("", defect.getPartName(), ":"), table));
            }
            cells.addAll(createCalculationParameter(columnHeaders.get(String.valueOf(ColumnHeaderType.DEFECTS)), defect.getParameterMeasurements(), stringSequentialNumber, table));
        }
        return cells;
    }

    private List<CellTable> createCalculationParameter(Integer columnSequentialNumber, Set<CalculationParameterMeasurement> parameterMeasurements, int stringSequentialNumber, DocumentTable table) {
        List<CellTable> cells = new ArrayList<>(parameterMeasurements.size());
        int number;
        for (CalculationParameterMeasurement param : parameterMeasurements) {
            number = param.getNumber();
            if (number == param.getNumber()) {
                if (param.getSecondValue() == null) {
                    cells.add(mapper.mapToCellTable(columnSequentialNumber, stringSequentialNumber, String.join(" ", param.getParameterName(), "до", String.valueOf(param.getFirstValue()), param.getUnitMeasurement()), table));
                } else {
                    cells.add(mapper.mapToCellTable(columnSequentialNumber, stringSequentialNumber, String.join(" ", param.getParameterName(), "от", String.valueOf(param.getFirstValue()), "до", String.valueOf(param.getSecondValue()), param.getUnitMeasurement()), table));
                }
            } else {
                String paramNumber = String.join("", String.valueOf(param.getNumber()), ")");
                if (param.getSecondValue() == null) {
                    cells.add(mapper.mapToCellTable(columnSequentialNumber, stringSequentialNumber, String.join(" ", paramNumber, param.getParameterName(), "до", String.valueOf(param.getFirstValue()), param.getUnitMeasurement()), table));
                } else {
                    cells.add(mapper.mapToCellTable(columnSequentialNumber, stringSequentialNumber, String.join(" ", paramNumber, param.getParameterName(), "от", String.valueOf(param.getFirstValue()), "до", String.valueOf(param.getSecondValue()), param.getUnitMeasurement()), table));
                }
            }
        }
        return cells;
    }

    @Override
    public List<CellTable> createRepairElementCell(Map<String, Integer> columnHeaders, List<CompletedRepairElement> repairElements, int stringSequentialNumber, DocumentTable table) {
        List<CellTable> cells = new ArrayList<>();
        for(CompletedRepairElement repair : repairElements) {
            cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.REPAIR_ELEMENT)), stringSequentialNumber, String.join("", repair.getRepairName(), ":"), table));
            stringSequentialNumber += 1;
            if (repair.getPartName() != null) {
                cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.REPAIR_ELEMENT)), stringSequentialNumber, String.join("", repair.getRepairName(), ":"), table));
            }
            cells.addAll(createCalculationParameter(columnHeaders.get(String.valueOf(ColumnHeaderType.REPAIR_ELEMENT)), repair.getParameterMeasurements(), stringSequentialNumber, table));
        }
        return cells;
    }

    @Override
    public CellTable createVisualInspectionCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DESIGN_THICKNESS)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createDesignThicknessCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DESIGN_THICKNESS)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createMeasuredThicknessCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType. MEASURED_THICKNESS)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createMaxCorrosionCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.MAX_CORROSION)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createResidualThicknessCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.RESIDUAL_THICKNESS)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createMinAllowableThicknessCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.MIN_ALLOWABLE_THICKNESS)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createPlaceNumberCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.PLACE_NUMBER)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createHeightCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.HEIGHT)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createDeviationCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DEVIATION)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createPrecipitationCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.PRECIPITATION)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createDifferenceNeighboringPointsCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DIFFERENCE_NEIGHBORING_POINTS)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createDifferenceDiametricalPointsCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DIFFERENCE_DIAMETRICAL_POINTS)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createDiameterCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DIAMETER)), stringSequentialNumber, valid(cellValue), table);
    }

    @Override
    public CellTable createHardnessCell(Map<String, Integer> columnHeaders, int stringSequentialNumber, String cellValue, DocumentTable table) {
        return mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.HARDNESS)), stringSequentialNumber, valid(cellValue), table);
    }

    private String valid(String cellValue) {
        if (cellValue == null) {
            return "-";
        }
        return cellValue;
    }
}
