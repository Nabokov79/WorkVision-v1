package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.equipmentInspection.ResponseEquipmentInspectionDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.equipmentRepair.ResponseEquipmentRepairDto;
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

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CellTableServiceImpl implements CellTableService {

    private final CellTableRepository repository;

    private final CellFactoryService cellFactoryService;
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
        Map<String, Integer> columnHeaders = tableTemplate.getColumnHeaders()
                                                  .stream()
                                                  .collect(Collectors.toMap(ColumnHeaderTemplate::getColumnHeaderType
                                                                          , ColumnHeaderTemplate::getSequentialNumber));
        List<CellTable> cells = new ArrayList<>();
        int stringSequentialNumber = 0;
        for (ResponseEquipmentInspectionDto i : inspections) {
            cells.add(cellFactoryService.createDateCell(columnHeaders, stringSequentialNumber, i.getDate(), table));
            cells.add(cellFactoryService.createSurveysDescriptionCell(columnHeaders, stringSequentialNumber, i.getInspection(), table));
            cells.add(cellFactoryService.createDocumentNumberCell(columnHeaders, stringSequentialNumber, i.getDocumentNumber(), table));
            cells.add(cellFactoryService.createOrganizationNameCell(columnHeaders, stringSequentialNumber, i.getOrganization(), table));
            stringSequentialNumber++;
        }
        repository.saveAll(cells);
    }

    @Override
    public void saveEquipmentRepair(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal) {
        List<ResponseEquipmentRepairDto> repairs = equipmentRepairService.getAll(surveyJournal.getEquipmentId());
        Map<String, Integer> columnHeaders = tableTemplate.getColumnHeaders().stream()
                                                  .collect(Collectors.toMap(ColumnHeaderTemplate::getColumnHeaderType
                                                                          , ColumnHeaderTemplate::getSequentialNumber));
        List<CellTable> cells = new ArrayList<>();
        int stringSequentialNumber = 0;
        for (ResponseEquipmentRepairDto i : repairs) {
            cells.add(cellFactoryService.createDateCell(columnHeaders, stringSequentialNumber, i.getDate(), table));
            cells.add(cellFactoryService.createRepairDescriptionCell(columnHeaders, stringSequentialNumber, i.getDescription(), table));
            cells.add(cellFactoryService.createOrganizationNameCell(columnHeaders, stringSequentialNumber, i.getOrganization(), table));
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
        Set<ControlPoint> controlPoints = controlPointMeasurementService.getAll(surveyJournal.getId()
                                                                              , surveyJournal.getEquipmentId());
        Set<PointDifference> pointDifferences = pointDifferenceService.getAll(surveyJournal.getId()
                                                                            , surveyJournal.getEquipmentId());
    }

    @Override
    public void saveHardnessMeasurement(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal) {
        Set<HardnessMeasurement> measurements = hardnessMeasurementService.getAllByIds(surveyJournal.getId()
                                                                                     , surveyJournal.getEquipmentId());
    }

    @Override
    public void saveUTMeasurement(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal) {
        Set<ResultUltrasonicThicknessMeasurement> measurements = utMeasurementService.getAllByIds(surveyJournal.getId()
                                                                                      , surveyJournal.getEquipmentId());
        Map<String, Integer> columnHeaders = tableTemplate.getColumnHeaders().stream()
                                                  .collect(Collectors.toMap(ColumnHeaderTemplate::getColumnHeaderType
                                                                          , ColumnHeaderTemplate::getSequentialNumber));
        int stringSequentialNumber = 1;
        List<CellTable> cells = new ArrayList<>();
        Map<String, Integer> mergeLines = new HashMap<>();
        measurements.forEach(m -> mergeLines.merge(m.getElementName(), 1, Integer::sum));
        for (ResultUltrasonicThicknessMeasurement measurement : measurements) {
            cells.add(cellFactoryService.createStringNumberCell(columnHeaders, stringSequentialNumber, table));
            cells.add(cellFactoryService.createElementCell(columnHeaders
                                                         , mergeLines.get(measurement.getElementName())
                                                         , stringSequentialNumber
                                                         , measurement.getElementName()
                                                         , table));
            if (measurement.getDiameter() != null) {
                cells.add(cellFactoryService.createDiameterCell(columnHeaders
                                                              , stringSequentialNumber
                                                              , String.valueOf(measurement.getDiameter())
                                                              , table));
            }
            cells.add(cellFactoryService.createMeasuredThicknessCell(columnHeaders
                                      , stringSequentialNumber
                                      , String.join(" - ", String.valueOf(measurement.getMinMeasurementValue())
                                                                 , String.valueOf(measurement.getMaxMeasurementValue()))
                                     , table));
            cells.add(cellFactoryService.createMaxCorrosionCell(columnHeaders
                                                              , stringSequentialNumber
                                                              , String.valueOf(measurement.getMaxCorrosion())
                                                              , table));
            cells.add(cellFactoryService.createMinAllowableThicknessCell(columnHeaders
                                                                   , stringSequentialNumber
                                                                   , String.valueOf(measurement.getMinAcceptableValue())
                                                                   , table));
            if (measurement.getMeasurementNumber() != null) {
                cells.add(cellFactoryService.createPlaceNumberCell(columnHeaders
                                                                 , stringSequentialNumber
                                                                 , String.valueOf(measurement.getMeasurementNumber())
                                                                 , table));
            }
            if (measurement.getPartElementId() != null) {
                cells.add(cellFactoryService.createPartElementCell(columnHeaders
                                                                 , stringSequentialNumber
                                                                 , measurement.getElementName()
                                                                 , table));
            }
            stringSequentialNumber++;
        }
        repository.saveAll(cells);
    }

    @Override
    public void saveVMSMeasurement(DocumentTable table, TableTemplate tableTemplate, SurveyJournal surveyJournal) {
        Map<String, Integer> columnHeaders = tableTemplate.getColumnHeaders().stream()
                                                  .collect(Collectors.toMap(ColumnHeaderTemplate::getColumnHeaderType
                                                                          , ColumnHeaderTemplate::getSequentialNumber));
        List<CellTable> cells = new ArrayList<>();
        Map<String, Integer> mergeLines = new HashMap<>();
        Map<String, List<DefectMeasurement>> defectsMeasurement = new HashMap<>();
        Map<String, List<CompletedRepairElement>> repairElements = new HashMap<>();
        Map<String, String> visualInspections = visualInspectionService.getAllByIds(surveyJournal.getId()
                           , surveyJournal.getEquipmentId()).stream()
                                                            .collect(Collectors.toMap(VisualInspection::getElementName
                                                                                    , VisualInspection::getInspection));
        defectMeasurementService.getAllByIds(surveyJournal.getId(), surveyJournal.getEquipmentId())
                .forEach(d -> {
                                List<DefectMeasurement> defects = defectsMeasurement.get(d.getElementName());
                                defects.add(d);
                                defectsMeasurement.put(d.getElementName(), defects);
                                mergeLines.put(d.getElementName(), d.getParameterMeasurements().size());
                            });
        completedRepairElementService.getAllByIds(surveyJournal.getId(), surveyJournal.getEquipmentId())
                 .forEach(r -> {
                                List<CompletedRepairElement> repairs = repairElements.get(r.getElementName());
                                repairs.add(r);
                                repairElements.put(r.getElementName(), repairs);
                                if (mergeLines.get(r.getElementName()) < r.getParameterMeasurements().size()) {
                                    mergeLines.put(r.getElementName(), r.getParameterMeasurements().size());
                                }
                            });

        mergeLines.keySet().forEach(k -> {
            int stringSequentialNumber = 1;
            cells.add(cellFactoryService.createElementCell(columnHeaders
                                                         , mergeLines.get(k)
                                                         , stringSequentialNumber
                                                         , k
                                                         , table));
            cells.addAll(cellFactoryService.createDefectCell(columnHeaders
                                                           , defectsMeasurement.get(k)
                                                           , stringSequentialNumber
                                                           , table));
            cells.addAll(cellFactoryService.createRepairElementCell(columnHeaders
                                                                  , repairElements.get(k)
                                                                  , stringSequentialNumber
                                                                  , table));
            cells.add(cellFactoryService.createVisualInspectionCell(columnHeaders
                                                                  , stringSequentialNumber
                                                                  , visualInspections.get(k)
                                                                  , table));
            stringSequentialNumber += mergeLines.get(k);
        });
        repository.saveAll(cells);
    }
}