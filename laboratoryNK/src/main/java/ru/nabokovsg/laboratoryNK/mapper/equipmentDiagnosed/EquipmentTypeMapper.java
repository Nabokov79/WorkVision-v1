package ru.nabokovsg.laboratoryNK.mapper.equipmentDiagnosed;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipmentType.EquipmentTypeDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipmentType.ResponseEquipmentTypeDto;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentType;

@Mapper(componentModel = "spring")
public interface EquipmentTypeMapper {

    EquipmentType mapToEquipmentType(EquipmentTypeDto equipmentTypeDto);

    ResponseEquipmentTypeDto mapResponseEquipmentTypeDto(EquipmentType equipmentType);
}