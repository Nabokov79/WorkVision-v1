package ru.nabokovsg.laboratoryNK.service.norms;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.norms.acceptableThickness.AcceptableThicknessDto;
import ru.nabokovsg.laboratoryNK.dto.norms.acceptableThickness.ResponseAcceptableThicknessDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.norms.AcceptableThicknessMapper;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.QEquipmentDiagnosed;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.UltrasonicThicknessMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableThickness;
import ru.nabokovsg.laboratoryNK.model.norms.QAcceptableThickness;
import ru.nabokovsg.laboratoryNK.repository.norms.AcceptableThicknessRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AcceptableThicknessServiceImpl implements AcceptableThicknessService {

    private final AcceptableThicknessRepository repository;
    private final AcceptableThicknessMapper mapper;
    private final EntityManager em;

    @Override
    public ResponseAcceptableThicknessDto save(AcceptableThicknessDto thicknessDto) {
        return mapper.mapToResponseAcceptableThicknessDto(
                Objects.requireNonNullElseGet(getByPredicate(thicknessDto)
                        , () -> repository.save(mapper.mapToAcceptableThickness(thicknessDto))));
    }

    @Override
    public ResponseAcceptableThicknessDto update(AcceptableThicknessDto thicknessDto) {
        if (repository.existsById(thicknessDto.getId())) {
            return mapper.mapToResponseAcceptableThicknessDto(
                    repository.save(mapper.mapToAcceptableThickness(thicknessDto))
            );
        }
        throw new NotFoundException(
                String.format("AcceptableThickness with id=%s not found for update", thicknessDto.getId())
        );
    }

    @Override
    public List<ResponseAcceptableThicknessDto> getAll(Long id) {
        return repository.findAllByEquipmentTypeId(id)
                .stream()
                .map(mapper::mapToResponseAcceptableThicknessDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("AcceptableThickness with id=%s not found for delete", id));
    }

    @Override
    public AcceptableThickness getAcceptableThickness(UltrasonicThicknessMeasurement measurement) {
        QAcceptableThickness thickness = QAcceptableThickness.acceptableThickness;
        QEquipmentDiagnosed equipment = QEquipmentDiagnosed.equipmentDiagnosed;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(equipment.equipmentType.id.eq(thickness.equipmentTypeId));
        booleanBuilder.and(thickness.elementId.eq(measurement.getElementId()));
        if (measurement.getPartElementId() != null) {
            booleanBuilder.and(thickness.partElementId.eq(measurement.getPartElementId()));
        }
        if (measurement.getDiameter() != null) {
            booleanBuilder.and(thickness.diameter.eq(measurement.getDiameter()));
        }
        return new JPAQueryFactory(em).from(thickness)
                .select(thickness)
                .where(booleanBuilder)
                .fetchOne();
    }

    private AcceptableThickness getByPredicate(AcceptableThicknessDto thicknessDto) {
        QAcceptableThickness thickness = QAcceptableThickness.acceptableThickness;
        return new JPAQueryFactory(em).from(thickness)
                .select(thickness)
                .where(gePredicate(thicknessDto,thickness))
                .fetchOne();
    }

    private BooleanBuilder gePredicate(AcceptableThicknessDto thicknessDto, QAcceptableThickness thickness) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (thicknessDto.getEquipmentTypeId() != null) {
            booleanBuilder.and(thickness.equipmentTypeId.eq(thicknessDto.getEquipmentTypeId()));
        }
        if (thicknessDto.getElementId() != null) {
            booleanBuilder.and(thickness.elementId.eq(thicknessDto.getElementId()));
        }
        if (thicknessDto.getPartElementId() != null) {
            booleanBuilder.and(thickness.partElementId.eq(thicknessDto.getPartElementId()));
        }
        if (thicknessDto.getDiameter() != null) {
            booleanBuilder.and(thickness.diameter.eq(thicknessDto.getDiameter()));
        }
        return booleanBuilder;
    }
}