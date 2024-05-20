package ru.nabokovsg.laboratoryNK.service.measurement.utm;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measurement.utm.ResponseUltrasonicThicknessMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.utm.UltrasonicThicknessMeasurementDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.measurement.utm.UltrasonicThicknessMeasurementMapper;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.QUltrasonicThicknessMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.UltrasonicThicknessMeasurement;
import ru.nabokovsg.laboratoryNK.repository.measurement.utm.UltrasonicThicknessMeasurementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UltrasonicThicknessMeasurementServiceImpl implements UltrasonicThicknessMeasurementService {

    private final UltrasonicThicknessMeasurementRepository repository;
    private final UltrasonicThicknessMeasurementMapper mapper;
    private final ResultUltrasonicThicknessMeasurementService calculatingUltrasonicThicknessMeasurementService;
    private final EntityManager em;

    @Override
    public ResponseUltrasonicThicknessMeasurementDto save(UltrasonicThicknessMeasurementDto measurementDto) {
        UltrasonicThicknessMeasurement measurement = getByPredicate(measurementDto);
        if (measurement == null) {
            measurement = repository.save(mapper.mapToUltrasonicThicknessMeasurement(measurementDto));
            calculatingUltrasonicThicknessMeasurementService.save(measurement);
        }
        return mapper.mapToResponseUltrasonicThicknessMeasurementDto(measurement);
    }

    @Override
    public ResponseUltrasonicThicknessMeasurementDto update(UltrasonicThicknessMeasurementDto measurementDto) {
        if (repository.existsById(measurementDto.getId())) {
            return mapper.mapToResponseUltrasonicThicknessMeasurementDto(
                    repository.save(mapper.mapToUltrasonicThicknessMeasurement(measurementDto)));
        }
        throw new NotFoundException(
                String.format("UltrasonicThicknessMeasurement with id%s not found for update", measurementDto.getId()));
    }

    @Override
    public List<ResponseUltrasonicThicknessMeasurementDto> getAll(Long id) {
        return repository.findAllBySurveyJournalId(id)
                .stream()
                .map(mapper::mapToResponseUltrasonicThicknessMeasurementDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("UltrasonicThicknessMeasurement with id%s not found delete", id));
    }

    private UltrasonicThicknessMeasurement getByPredicate(UltrasonicThicknessMeasurementDto measurementDto) {
        QUltrasonicThicknessMeasurement measurement = QUltrasonicThicknessMeasurement.ultrasonicThicknessMeasurement;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(measurement.surveyJournalId.eq(measurementDto.getSurveyJournalId()));
        booleanBuilder.and(measurement.elementId.eq(measurementDto.getElementId()));
        booleanBuilder.and(measurement.maxMeasurementValue.eq(measurementDto.getMaxMeasurementValue()));
        booleanBuilder.and(measurement.minMeasurementValue.eq(measurementDto.getMinMeasurementValue()));
        if (measurementDto.getPartElementId() != null) {
            booleanBuilder.and(measurement.partElementId.eq(measurementDto.getPartElementId()));
        }
        if (measurementDto.getDiameter() != null) {
            booleanBuilder.and(measurement.diameter.eq(measurementDto.getDiameter()));
        }
        if (measurementDto.getMeasurementNumber() != null) {
            booleanBuilder.and(measurement.measurementNumber.eq(measurementDto.getMeasurementNumber()));
        }
        return new JPAQueryFactory(em).from(measurement)
                .select(measurement)
                .where(booleanBuilder)
                .fetchOne();
    }
}