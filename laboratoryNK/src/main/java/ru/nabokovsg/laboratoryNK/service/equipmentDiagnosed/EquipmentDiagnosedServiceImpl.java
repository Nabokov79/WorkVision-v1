package ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipments.EquipmentDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipments.ResponseEquipmentDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipments.ResponseShortEquipmentDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.equipmentDiagnosed.EquipmentDiagnosedMapper;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentDiagnosed;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.QEquipmentDiagnosed;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.QEquipmentType;
import ru.nabokovsg.laboratoryNK.repository.equipmentDiagnosed.EquipmentDiagnosedRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EquipmentDiagnosedServiceImpl implements EquipmentDiagnosedService {

    private final EquipmentDiagnosedRepository repository;
    private final EquipmentDiagnosedMapper mapper;
    private final EntityManager em;
    private final EquipmentTypeService equipmentTypeService;

    @Override
    public ResponseShortEquipmentDto save(EquipmentDto equipmentDto) {
        return mapper.mapToResponseShortEquipmentDto(
                Objects.requireNonNullElseGet(getByPredicate(equipmentDto),
                        () -> repository.save(
                                mapper.mapToEquipment(equipmentDto
                                        , equipmentTypeService.getById(equipmentDto.getEquipmentTypeId())))));
    }

    @Override
    public ResponseShortEquipmentDto update(EquipmentDto equipmentDto) {
        if (repository.existsById(equipmentDto.getId())) {
            return mapper.mapToResponseShortEquipmentDto(
                    repository.save(mapper.mapToEquipment(equipmentDto
                            , equipmentTypeService.getById(equipmentDto.getEquipmentTypeId()))));
        }
        throw new NotFoundException(String.format("Equipment with id=%s not found for update", equipmentDto.getId()));
    }

    @Override
    public ResponseEquipmentDto get(Long id, Boolean full) {
        EquipmentDiagnosed equipmentDiagnosed = getById(id);
        if (full != null) {
            equipmentDiagnosed.setFull(full);
            equipmentDiagnosed = repository.save(equipmentDiagnosed);
        }
        return mapper.mapToResponseEquipmentDto(equipmentDiagnosed);
    }

    @Override
    public List<ResponseShortEquipmentDto> getAll(Long id) {
        return repository.findAllByBuildingId(id).stream()
                .map(mapper::mapToResponseShortEquipmentDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Equipment with id=%s not found for delete", id));
    }

    @Override
    public EquipmentDiagnosed getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Equipment with id=%s not found", id)));
    }

    private EquipmentDiagnosed getByPredicate(EquipmentDto equipmentDto) {
        QEquipmentDiagnosed equipment = QEquipmentDiagnosed.equipmentDiagnosed;
        QEquipmentType equipmentType = QEquipmentType.equipmentType;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(equipmentType.id.eq(equipmentDto.getEquipmentTypeId()));
        booleanBuilder.and(equipment.equipmentName.eq(equipmentType.equipmentName));
        if (equipmentDto.getStationaryNumber() != null) {
            booleanBuilder.and(equipment.stationaryNumber.eq(equipmentDto.getStationaryNumber()));
        }
        if (equipmentDto.getVolume() != null) {
            booleanBuilder.and(equipment.volume.eq(equipmentDto.getVolume()));
        }
        if (equipmentDto.getModel() != null) {
            booleanBuilder.and(equipment.model.eq(equipmentDto.getModel()));
        }
        return new JPAQueryFactory(em).from(equipment)
                .select(equipment)
                .where(booleanBuilder)
                .fetchOne();
    }
}