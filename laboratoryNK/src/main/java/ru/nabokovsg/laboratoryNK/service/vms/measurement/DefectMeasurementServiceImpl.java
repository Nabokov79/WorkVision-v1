package ru.nabokovsg.laboratoryNK.service.vms.measurement;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.defectMeasurement.DefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.defectMeasurement.ResponseDefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.vms.measurement.DefectMeasurementMapper;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.UltrasonicThicknessMeasurement;
import ru.nabokovsg.laboratoryNK.model.vms.QVMSurvey;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.QCalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.QDefectMeasurement;
import ru.nabokovsg.laboratoryNK.model.vms.norm.Defect;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.DefectMeasurement;
import ru.nabokovsg.laboratoryNK.repository.vms.measurement.DefectMeasurementRepository;
import ru.nabokovsg.laboratoryNK.service.vms.VMSurveyService;
import ru.nabokovsg.laboratoryNK.service.vms.norm.DefectService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefectMeasurementServiceImpl implements DefectMeasurementService {

    private final DefectMeasurementRepository repository;
    private final DefectMeasurementMapper mapper;
    private final ParameterMeasurementService parameterMeasurementService;
    private final EntityManager em;
    private final DefectService defectsService;
    private final VMSurveyService vmSurveyService;

    @Override
    public ResponseDefectMeasurementDto save(DefectMeasurementDto measurementDto) {
        DefectMeasurement defectMeasurement = getByPredicate(measurementDto);
        Defect defect = defectsService.getById(measurementDto.getDefectId());
        if (defectMeasurement == null) {
            defectMeasurement = repository.save(mapper.mapWithEquipmentElement(measurementDto, defect
                    , vmSurveyService.save(measurementDto.getSurveyJournalId(), measurementDto.getEquipmentId()
                                         , measurementDto.getElementId(), measurementDto.getPartElementId())));
        }
        defectMeasurement.setParameterMeasurements(
                parameterMeasurementService.saveForDefectMeasurement(defect
                                                                   , defectMeasurement
                                                                   , measurementDto.getParameterMeasurements()));
        return mapper.mapToResponseDefectMeasurementDto(defectMeasurement);
    }

    @Override
    public List<ResponseDefectMeasurementDto> getAll(Long id) {
        QDefectMeasurement defect = QDefectMeasurement.defectMeasurement;
        QVMSurvey vm = QVMSurvey.vMSurvey;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(vm.surveyJournalId.eq(id));
        return new JPAQueryFactory(em).from(defect)
                                      .select(defect)
                                      .where(booleanBuilder)
                                      .fetch()
                                      .stream()
                                      .map(mapper::mapToResponseDefectMeasurementDto)
                                      .toList();
    }

    @Override
    public Set<DefectMeasurement> getAllByIds(Long surveyJournalId, Long equipmentId) {
        QDefectMeasurement defect = QDefectMeasurement.defectMeasurement;
        QVMSurvey vm = QVMSurvey.vMSurvey;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(vm.surveyJournalId.eq(surveyJournalId));
        booleanBuilder.and(vm.equipmentId.eq(equipmentId));
        Set<DefectMeasurement> measurements = new HashSet<>(new JPAQueryFactory(em).from(defect)
                                                                                    .select(defect)
                                                                                    .where(booleanBuilder)
                                                                                    .fetch());
        if (measurements.isEmpty()) {
            throw new NotFoundException(
                    String.format("DefectMeasurement with surveyJournalId=%s and surveyJournalId=%s not found"
                            , surveyJournalId, equipmentId));
        }
        return measurements;
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Defect measurement with id=%s not found for delete", id));
    }

    @Override
    public Double getMaxCorrosionValueByPredicate(UltrasonicThicknessMeasurement measurement) {
        QVMSurvey vm = QVMSurvey.vMSurvey;
        QDefectMeasurement defect = QDefectMeasurement.defectMeasurement;
        QCalculationParameterMeasurement parameter = QCalculationParameterMeasurement.calculationParameterMeasurement;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(vm.surveyJournalId.eq(measurement.getSurveyJournalId()));
        booleanBuilder.and(vm.equipmentId.eq(measurement.getEquipmentId()));
        booleanBuilder.and(vm.elementId.eq(measurement.getElementId()));
        booleanBuilder.and(defect.useCalculateThickness.eq(true));
        if (measurement.getPartElementId() != null) {
            booleanBuilder.and(vm.partElementId.eq(measurement.getPartElementId()));
        }
        booleanBuilder.and(parameter.defectMeasurement.id.eq(defect.id));
        Double corrosion = new JPAQueryFactory(em).from(parameter)
                .select(parameter.firstValue)
                .where(booleanBuilder)
                .fetchFirst();
        if (corrosion == null) {
            throw new NotFoundException(String.format("Max corrosion value not found corrosion=%s", corrosion));
        }
        return corrosion;
    }

    private DefectMeasurement getByPredicate(DefectMeasurementDto defectMeasurementDto) {
        QDefectMeasurement defect = QDefectMeasurement.defectMeasurement;
        QVMSurvey vm = QVMSurvey.vMSurvey;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(defect.defectId.eq(defectMeasurementDto.getDefectId()));
        booleanBuilder.and(vm.surveyJournalId.eq(defectMeasurementDto.getSurveyJournalId()));
        booleanBuilder.and(vm.equipmentId.eq(defectMeasurementDto.getEquipmentId()));
        booleanBuilder.and(vm.elementId.eq(defectMeasurementDto.getElementId()));
        if (defectMeasurementDto.getPartElementId() != null && defectMeasurementDto.getPartElementId() > 0) {
            booleanBuilder.and(vm.partElementId.eq(defectMeasurementDto.getPartElementId()));
        }
        return new JPAQueryFactory(em).from(defect)
                                      .select(defect)
                                      .innerJoin(vm).on(defect.vmSurvey.id.eq(vm.id))
                                      .where(booleanBuilder)
                                      .fetchOne();
    }
}