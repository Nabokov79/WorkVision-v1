package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.equipmentInspection.ResponseEquipmentInspectionDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.equipmentRepair.ResponseEquipmentRepairDto;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.CellTableMapper;
import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.CellTable;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.DocumentTable;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ColumnHeaderTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ColumnHeaderType;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.TableTemplate;
import ru.nabokovsg.laboratoryNK.model.measurement.HardnessMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.ControlPoint;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.PointDifference;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.ReferencePoint;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.ResultUltrasonicThicknessMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.DefectMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.VisualInspection;
import ru.nabokovsg.laboratoryNK.repository.document.CellTableRepository;
import ru.nabokovsg.laboratoryNK.service.measurement.common.EquipmentInspectionService;
import ru.nabokovsg.laboratoryNK.service.measurement.common.EquipmentRepairService;
import ru.nabokovsg.laboratoryNK.service.measurement.gm.ControlPointMeasurementService;
import ru.nabokovsg.laboratoryNK.service.measurement.gm.PointDifferenceService;
import ru.nabokovsg.laboratoryNK.service.measurement.gm.ReferencePointMeasurementService;
import ru.nabokovsg.laboratoryNK.service.measurement.hardnessMeasurement.HardnessMeasurementService;
import ru.nabokovsg.laboratoryNK.service.measurement.utm.ResultUltrasonicThicknessMeasurementService;
import ru.nabokovsg.laboratoryNK.service.measurement.vms.CompletedRepairElementService;
import ru.nabokovsg.laboratoryNK.service.measurement.vms.DefectMeasurementService;
import ru.nabokovsg.laboratoryNK.service.measurement.vms.VisualInspectionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CellTableServiceImpl implements CellTableService {

    private final CellTableRepository repository;
    private final CellTableMapper mapper;
    private final EquipmentInspectionService inspectionService;
    private final ReferencePointMeasurementService referencePointMeasurementService;
    private final ControlPointMeasurementService controlPointMeasurementService;
    private final PointDifferenceService pointDifferenceService;
    private final HardnessMeasurementService hardnessMeasurementService;
    private final ResultUltrasonicThicknessMeasurementService utMeasurementService;
    private final DefectMeasurementService defectMeasurementService;
    private final CompletedRepairElementService completedRepairElementService;
    private final VisualInspectionService visualInspectionService;
    private final EquipmentRepairService equipmentRepairService;

    @Override
    public void saveEquipmentInspection(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal) {
        List<ResponseEquipmentInspectionDto> inspections = inspectionService.getAll(surveyJournal.getEquipmentId());
        Map<String, Integer> columnHeaders = tableTemplate.getColumnHeaders().stream().collect(Collectors.toMap(ColumnHeaderTemplate::getColumnHeaderType, ColumnHeaderTemplate::getSequentialNumber));
        List<CellTable> cells = new ArrayList<>();
        int stringSequentialNumber = 0;
        if (inspections.isEmpty()) {
            cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DATE)), stringSequentialNumber, "-", table));
            cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.SURVEYS_DESCRIPTION)), stringSequentialNumber, "-", table));
            cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DOCUMENT_NUMBER)), stringSequentialNumber, "-", table));
            cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.ORGANIZATION_NAME)), stringSequentialNumber, "-", table));
        } else {
            for (ResponseEquipmentInspectionDto i : inspections) {
                cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DATE)), stringSequentialNumber, i.getDate(), table));
                cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.SURVEYS_DESCRIPTION)), stringSequentialNumber, i.getInspection(), table));
                cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DOCUMENT_NUMBER)), stringSequentialNumber, i.getDocumentNumber(), table));
                cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.ORGANIZATION_NAME)), stringSequentialNumber, i.getOrganization(), table));
                stringSequentialNumber++;
            }
        }
        repository.saveAll(cells);
    }

    @Override
    public void saveEquipmentRepair(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal) {
        List<ResponseEquipmentRepairDto> repairs = equipmentRepairService.getAll(surveyJournal.getEquipmentId());
        Map<String, Integer> columnHeaders = tableTemplate.getColumnHeaders().stream().collect(Collectors.toMap(ColumnHeaderTemplate::getColumnHeaderType, ColumnHeaderTemplate::getSequentialNumber));
        List<CellTable> cells = new ArrayList<>();
        int stringSequentialNumber = 0;
        for (ResponseEquipmentRepairDto i : repairs) {
            cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DATE)), stringSequentialNumber, i.getDate(), table));
            cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.REPAIR_DESCRIPTION)), stringSequentialNumber, i.getDescription(), table));
            cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.ORGANIZATION_NAME)), stringSequentialNumber,  i.getOrganization(), table));
            stringSequentialNumber++;
        }
        repository.saveAll(cells);
    }

    @Override
    public void saveReferencePointMeasurement(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal) {
        Set<ReferencePoint> points = referencePointMeasurementService.getAll(surveyJournal.getId(), surveyJournal.getEquipmentId());
    }

    @Override
    public void saveControlPointMeasurement(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal) {
        Set<ControlPoint> controlPoints = controlPointMeasurementService.getAll(surveyJournal.getId(), surveyJournal.getEquipmentId());
        Set<PointDifference> pointDifferences = pointDifferenceService.getAll(surveyJournal.getId(), surveyJournal.getEquipmentId());
    }

    @Override
    public void saveHardnessMeasurement(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal) {
        Set<HardnessMeasurement> measurements = hardnessMeasurementService.getAllByIds(surveyJournal.getId(), surveyJournal.getEquipmentId());
    }

    @Override
    public void saveUTMeasurement(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal) {
        Set<ResultUltrasonicThicknessMeasurement> measurements = utMeasurementService.getAllByIds(surveyJournal.getId(), surveyJournal.getEquipmentId());
        Map<String, Integer> columnHeaders = tableTemplate.getColumnHeaders().stream().collect(Collectors.toMap(ColumnHeaderTemplate::getColumnHeaderType, ColumnHeaderTemplate::getSequentialNumber));
        int stringSequentialNumber = 0;
        List<CellTable> cells = new ArrayList<>();
        for (ResultUltrasonicThicknessMeasurement measurement : measurements) {
            cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.STRING_NUMBER)), stringSequentialNumber, String.valueOf(stringSequentialNumber), table));
            cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.ELEMENT)), stringSequentialNumber, measurement.getElementName(), table));
            cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DIAMETER)), stringSequentialNumber, String.valueOf(measurement.getDiameter()), table));
            cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.MEASURED_THICKNESS))
                    , stringSequentialNumber
                    , String.join(" - ", String.valueOf(measurement.getMinMeasurementValue())
                            , String.valueOf(measurement.getMaxMeasurementValue()))
                    , table));
            cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.MAX_CORROSION)), stringSequentialNumber, String.valueOf(measurement.getMaxCorrosion()), table));
            cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.MIN_ALLOWABLE_THICKNESS)), stringSequentialNumber, String.valueOf(measurement.getMinAcceptableValue()), table));
            if (measurement.getMeasurementNumber() != null) {
                cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.PLACE_NUMBER)), stringSequentialNumber, String.valueOf(measurement.getMeasurementNumber()), table));
            }
            if (measurement.getPartElementId() != null) {
                cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.STRING_NUMBER)), stringSequentialNumber, String.valueOf(stringSequentialNumber), table));
            } else {
                cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.PART_ELEMENT)), stringSequentialNumber, measurement.getElementName(), table));
            }
            stringSequentialNumber++;
        }
        repository.saveAll(cells);
    }

    @Override
    public void saveVMSMeasurement(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal) {
        Set<DefectMeasurement> measurements = defectMeasurementService.getAllByIds(surveyJournal.getId(), surveyJournal.getEquipmentId());
        Set<CompletedRepairElement> repairElements = completedRepairElementService.getAllByIds(surveyJournal.getId(), surveyJournal.getEquipmentId());
        Set<VisualInspection> visualInspections = visualInspectionService.getAllByIds(surveyJournal.getId(), surveyJournal.getEquipmentId());
    }


    private List<CellTable> createCell(ColumnHeaderType columnHeaderType, Map<String, Integer> columnHeaders, Integer stringSequentialNumber, String cellValue, DocumentTable table) {
        List<CellTable> cells = new ArrayList<>();
        switch (columnHeaderType) {
            case STRING_NUMBER ->  cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DOCUMENT_NUMBER)), stringSequentialNumber, String.valueOf(stringSequentialNumber), table));
            case DATE -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DATE)), stringSequentialNumber, valid(cellValue), table));
            case SURVEYS_DESCRIPTION -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.SURVEYS_DESCRIPTION)), stringSequentialNumber, valid(cellValue), table));
            case ORGANIZATION_NAME -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.ORGANIZATION_NAME)), stringSequentialNumber, valid(cellValue), table));
            case REPAIR_DESCRIPTION -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.REPAIR_DESCRIPTION)), stringSequentialNumber, valid(cellValue), table));
            case DOCUMENT_NUMBER -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DOCUMENT_NUMBER)), stringSequentialNumber, valid(cellValue), table));
            case ELEMENT -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.ELEMENT)), stringSequentialNumber, valid(cellValue), table));
            case PART_ELEMENT -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.PART_ELEMENT)), stringSequentialNumber, valid(cellValue), table));
            case DEFECTS -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DOCUMENT_NUMBER)), stringSequentialNumber, valid(cellValue), table));
            case REPAIR_ELEMENT -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DOCUMENT_NUMBER)), stringSequentialNumber, valid(cellValue), table));
            case VISUAL_INSPECTION -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.VISUAL_INSPECTION)), stringSequentialNumber, valid(cellValue), table));
            case DESIGN_THICKNESS -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DESIGN_THICKNESS)), stringSequentialNumber, valid(cellValue), table));
            case MEASURED_THICKNESS -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType. MEASURED_THICKNESS)), stringSequentialNumber, valid(cellValue), table));
            case MAX_CORROSION -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.MAX_CORROSION)), stringSequentialNumber, valid(cellValue), table));
            case RESIDUAL_THICKNESS -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.RESIDUAL_THICKNESS)), stringSequentialNumber, valid(cellValue), table));
            case MIN_ALLOWABLE_THICKNESS -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.MIN_ALLOWABLE_THICKNESS)), stringSequentialNumber, valid(cellValue), table));
            case PLACE_NUMBER -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.PLACE_NUMBER)), stringSequentialNumber, valid(cellValue), table));
            case HEIGHT -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.HEIGHT)), stringSequentialNumber, valid(cellValue), table));
            case DEVIATION -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DEVIATION)), stringSequentialNumber, valid(cellValue), table));
            case PRECIPITATION -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.PRECIPITATION)), stringSequentialNumber, valid(cellValue), table));
            case DIFFERENCE_NEIGHBORING_POINTS -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DIFFERENCE_NEIGHBORING_POINTS)), stringSequentialNumber, valid(cellValue), table));
            case DIFFERENCE_DIAMETRICAL_POINTS -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DIFFERENCE_DIAMETRICAL_POINTS)), stringSequentialNumber, valid(cellValue), table));
            case DIAMETER -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.DIAMETER)), stringSequentialNumber, valid(cellValue), table));
            case HARDNESS -> cells.add(mapper.mapToCellTable(columnHeaders.get(String.valueOf(ColumnHeaderType.HARDNESS)), stringSequentialNumber, valid(cellValue), table));
        }
        return cells;
    }

    private String valid(String cellValue) {
        if (cellValue == null) {
            return "-";
        }
        return cellValue;
    }

    private ColumnHeaderType convertToColumnHeaderType(String columnHeaderType) {
        return ColumnHeaderType.from(columnHeaderType)
                .orElseThrow(() -> new BadRequestException(
                        String.format("Unknown data format columnHeaderType=%s", columnHeaderType))
                );
    }
}