package ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipmentType.EquipmentTypeDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipmentType.ResponseEquipmentTypeDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.equipmentDiagnosed.EquipmentTypeMapper;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentType;
import ru.nabokovsg.laboratoryNK.repository.equipmentDiagnosed.EquipmentTypeRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EquipmentTypeServiceImpl implements EquipmentTypeService {

    private final EquipmentTypeRepository repository;
    private final EquipmentTypeMapper mapper;

    @Override
    public ResponseEquipmentTypeDto save(EquipmentTypeDto equipmentTypeDto) {
        return mapper.mapResponseEquipmentTypeDto(
                Objects.requireNonNullElseGet(repository.findByEquipmentNameAndModel(equipmentTypeDto.getEquipmentName()
                                                                                   , equipmentTypeDto.getModel()),
                                            () -> repository.save(mapper.mapToEquipmentType(equipmentTypeDto))));
    }

    @Override
    public ResponseEquipmentTypeDto update(EquipmentTypeDto equipmentTypeDto) {
        if (repository.existsById(equipmentTypeDto.getId())) {
            return mapper.mapResponseEquipmentTypeDto(repository.save(mapper.mapToEquipmentType(equipmentTypeDto)));
        }
        throw new NotFoundException(
                String.format("Equipment type with id=%s not found for update", equipmentTypeDto.getId()));
    }

    @Override
    public ResponseEquipmentTypeDto get(Long id) {
        return mapper.mapResponseEquipmentTypeDto(getById(id));
    }

    @Override
    public List<ResponseEquipmentTypeDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapResponseEquipmentTypeDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Equipment type with id=%s not found for delete", id));
    }

    @Override
    public EquipmentType getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Equipment type with id=%s not found for delete", id)));
    }
}