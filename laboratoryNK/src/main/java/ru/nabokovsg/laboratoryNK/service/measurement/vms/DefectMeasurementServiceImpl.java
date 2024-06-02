package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.defectMeasurement.DefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.defectMeasurement.ResponseDefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.measurement.vms.DefectMeasurementMapper;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentElement;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.PartElement;
import ru.nabokovsg.laboratoryNK.model.measurement.CalculationParameterMeasurementBuilder;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.UltrasonicThicknessMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.DefectMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.QCalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.QDefectMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.Defect;
import ru.nabokovsg.laboratoryNK.repository.measurement.vms.DefectMeasurementRepository;
import ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed.EquipmentElementService;
import ru.nabokovsg.laboratoryNK.service.norms.DefectService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefectMeasurementServiceImpl implements DefectMeasurementService {

    private final DefectMeasurementRepository repository;
    private final DefectMeasurementMapper mapper;
    private final ParameterMeasurementService parameterMeasurementService;
    private final EntityManager em;
    private final DefectService defectsService;
    private final EquipmentElementService equipmentElementService;

    @Override
    public ResponseDefectMeasurementDto save(DefectMeasurementDto measurementDto) {
        DefectMeasurement measurement = getByPredicate(measurementDto);
        Defect defect = defectsService.getById(measurementDto.getDefectId());
        if (measurement == null) {
            EquipmentElement element = equipmentElementService.getById(measurementDto.getElementId());
            measurement = mapper.mapWithEquipmentElement(measurementDto, defect, element);
            if(measurementDto.getPartElementId() != null) {
                Map<Long, PartElement> partsElement = element.getPartsElement()
                        .stream().collect(Collectors.toMap(PartElement::getId, p -> p));
                measurement = mapper.mapWithPartElement(measurement, partsElement.get(measurementDto.getPartElementId()));
            }
            measurement = repository.save(measurement);
        }
        measurement.setParameterMeasurements(parameterMeasurementService.save(
               new CalculationParameterMeasurementBuilder.Builder()
                                                    .defect(measurement)
                                                    .typeCalculation(defect.getTypeCalculation())
                                                    .measuredParameters(defect.getMeasuredParameters())
                                                    .parameterMeasurements(measurement.getParameterMeasurements())
                                                    .parameterMeasurementsDto(measurementDto.getParameterMeasurements())
                                                    .build()));
        return mapper.mapToResponseDefectMeasurementDto(measurement);
    }

    @Override
    public ResponseDefectMeasurementDto update(DefectMeasurementDto measurementDto) {
        if (repository.existsById(measurementDto.getId())) {
            Defect defect = defectsService.getById(measurementDto.getDefectId());
            EquipmentElement element = equipmentElementService.getById(measurementDto.getElementId());
            Map<Long, PartElement> partsElement = element.getPartsElement()
                    .stream().collect(Collectors.toMap(PartElement::getId, p -> p));
            DefectMeasurement measurement = mapper.mapWithEquipmentElement(measurementDto, defect, element);
            if(measurementDto.getPartElementId() != null) {
                measurement = mapper.mapWithPartElement(measurement, partsElement.get(measurementDto.getPartElementId()));
            }
            DefectMeasurement defectMeasurement = repository.save(measurement);
            measurement.getParameterMeasurements().addAll(parameterMeasurementService.save(
                    new CalculationParameterMeasurementBuilder.Builder()
                            .defect(measurement)
                            .typeCalculation(defect.getTypeCalculation())
                            .measuredParameters(defect.getMeasuredParameters())
                            .parameterMeasurementsDto(measurementDto.getParameterMeasurements())
                            .build()));
            return mapper.mapToResponseDefectMeasurementDto(defectMeasurement);
        }
        throw new NotFoundException(
                String.format("DefectMeasurement with id=%s not found for update", measurementDto.getId()));
    }

    @Override
    public List<ResponseDefectMeasurementDto> getAll(Long id) {
        return repository.findAllBySurveyJournalId(id)
                         .stream()
                         .map(mapper::mapToResponseDefectMeasurementDto)
                         .toList();
    }

    @Override
    public Set<DefectMeasurement> getAllByIds(Long surveyJournalId, Long equipmentId) {
        Set<DefectMeasurement> measurements = repository.findAllBySurveyJournalIdAndEquipmentId(surveyJournalId, equipmentId);
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
        QDefectMeasurement defect = QDefectMeasurement.defectMeasurement;
        QCalculationParameterMeasurement parameter = QCalculationParameterMeasurement.calculationParameterMeasurement;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(defect.surveyJournalId.eq(measurement.getSurveyJournalId()));
        booleanBuilder.and(defect.equipmentId.eq(measurement.getEquipmentId()));
        booleanBuilder.and(defect.elementId.eq(measurement.getElementId()));
        booleanBuilder.and(defect.useCalculateThickness.eq(true));
        if (measurement.getPartElementId() != null) {
            booleanBuilder.and(defect.partElementId.eq(measurement.getPartElementId()));
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
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(defect.surveyJournalId.eq(defectMeasurementDto.getSurveyJournalId()));
        booleanBuilder.and(defect.equipmentId.eq(defectMeasurementDto.getEquipmentId()));
        booleanBuilder.and(defect.elementId.eq(defectMeasurementDto.getElementId()));
        if (defectMeasurementDto.getPartElementId() != null && defectMeasurementDto.getPartElementId() > 0) {
            booleanBuilder.and(defect.partElementId.eq(defectMeasurementDto.getPartElementId()));
        }
        booleanBuilder.and(defect.defectId.eq(defectMeasurementDto.getDefectId()));
        return new JPAQueryFactory(em).from(defect)
                .select(defect)
                .where(booleanBuilder)
                .fetchOne();
    }
}