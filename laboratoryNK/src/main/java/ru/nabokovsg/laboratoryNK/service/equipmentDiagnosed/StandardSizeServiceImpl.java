package ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.standardSize.StandardSizeDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.equipmentDiagnosed.StandardSizeMapper;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.*;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.ResultUltrasonicThicknessMeasurement;
import ru.nabokovsg.laboratoryNK.repository.equipmentDiagnosed.StandardSizeRepository;

@Service
@RequiredArgsConstructor
public class StandardSizeServiceImpl implements StandardSizeService {

    private final StandardSizeRepository repository;
    private final StandardSizeMapper mapper;
    private final EntityManager em;

    @Override
    public StandardSize save(StandardSizeDto standardSizeDto) {
        return repository.save(mapper.mapToStandardSize(standardSizeDto));
    }

    @Override
    public StandardSize update(StandardSizeDto standardSizeDto) {
        if (repository.existsById(standardSizeDto.getId())) {
            return repository.save(mapper.mapToStandardSize(standardSizeDto));
        }
        throw new NotFoundException(
                String.format("StandardSize with id=%s not found for update", standardSizeDto.getId())
        );
    }

    @Override
    public StandardSize getByPredicate(ResultUltrasonicThicknessMeasurement measurement) {
        QStandardSize standard = QStandardSize.standardSize;
        QEquipmentDiagnosed equipment = QEquipmentDiagnosed.equipmentDiagnosed;
        QEquipmentElement element = QEquipmentElement.equipmentElement;
        QPartElement partElement = QPartElement.partElement;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(equipment.id.eq(measurement.getEquipmentId()));
        booleanBuilder.and(element.id.eq(measurement.getEquipmentId()));
        if (measurement.getPartElementId() != null) {
            booleanBuilder.and(partElement.id.eq(measurement.getPartElementId()));
        }
        if (measurement.getDiameter() != null) {
            booleanBuilder.and(standard.minDiameter.eq(measurement.getDiameter()));
        }
        StandardSize standardSize = new JPAQueryFactory(em).from(standard)
                .select(standard)
                .where(booleanBuilder)
                .fetchOne();
        if (standardSize == null) {
            throw new NotFoundException(String.format("StandardSize for measurement=%s not found", measurement));
        }
        return standardSize;
    }
}