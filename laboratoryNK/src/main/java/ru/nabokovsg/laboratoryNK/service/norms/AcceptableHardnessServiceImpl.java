package ru.nabokovsg.laboratoryNK.service.norms;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.norms.acceptableHardness.AcceptableHardnessDto;
import ru.nabokovsg.laboratoryNK.dto.norms.acceptableHardness.ResponseAcceptableHardnessDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.norms.AcceptableHardnessMapper;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.QEquipmentDiagnosed;
import ru.nabokovsg.laboratoryNK.model.measurement.HardnessMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableHardness;
import ru.nabokovsg.laboratoryNK.model.norms.QAcceptableHardness;
import ru.nabokovsg.laboratoryNK.repository.norms.AcceptableHardnessRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AcceptableHardnessServiceImpl implements AcceptableHardnessService {

    private final AcceptableHardnessRepository repository;
    private final AcceptableHardnessMapper mapper;
    private final EntityManager em;

    @Override
    public ResponseAcceptableHardnessDto save(AcceptableHardnessDto hardnessDto) {
        return mapper.mapToResponseAcceptableHardnessDto(
                Objects.requireNonNullElseGet(repository.findByEquipmentTypeIdAndElementId(
                                                                                      hardnessDto.getEquipmentTypeId()
                                                                                    , hardnessDto.getElementId())
                        , () -> repository.save(mapper.mapToAcceptableHardness(hardnessDto))));
    }

    @Override
    public ResponseAcceptableHardnessDto update(AcceptableHardnessDto hardnessDto) {
        if (repository.existsById(hardnessDto.getId())) {
            return mapper.mapToResponseAcceptableHardnessDto(
                    repository.save(mapper.mapToAcceptableHardness(hardnessDto))
            );
        }
        throw new NotFoundException(
                String.format("AcceptableHardness with id=%s not found for update", hardnessDto.getId())
        );
    }

    @Override
    public List<ResponseAcceptableHardnessDto> getAll(Long id) {
        return repository.findAllByEquipmentTypeId(id)
                .stream()
                .map(mapper::mapToResponseAcceptableHardnessDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("AcceptableHardness with id=%s not found for delete", id));
    }

    @Override
    public AcceptableHardness getByPredicate(HardnessMeasurement measurement) {
        QAcceptableHardness acceptableHardness = QAcceptableHardness.acceptableHardness;
        QEquipmentDiagnosed equipment = QEquipmentDiagnosed.equipmentDiagnosed;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(equipment.id.eq(measurement.getEquipmentId()));
        booleanBuilder.and(acceptableHardness.equipmentTypeId.eq(equipment.equipmentType.id));
        booleanBuilder.and(acceptableHardness.elementId.eq(measurement.getElementId()));
        if (measurement.getPartElementId() != null) {
            booleanBuilder.and(acceptableHardness.partElementId.eq(measurement.getPartElementId()));
        }
        return new JPAQueryFactory(em).from(acceptableHardness)
                .select(acceptableHardness)
                .where(booleanBuilder)
                .fetchOne();
    }
}