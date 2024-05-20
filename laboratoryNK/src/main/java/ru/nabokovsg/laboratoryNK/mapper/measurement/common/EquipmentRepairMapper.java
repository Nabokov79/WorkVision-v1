package ru.nabokovsg.laboratoryNK.mapper.measurement.common;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.equipmentRepair.EquipmentRepairDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.equipmentRepair.ResponseEquipmentRepairDto;
import ru.nabokovsg.laboratoryNK.model.measurement.common.EquipmentRepair;

@Mapper(componentModel = "spring")
public interface EquipmentRepairMapper {

    EquipmentRepair mapToEquipmentRepair(EquipmentRepairDto repairDto);
    ResponseEquipmentRepairDto mapToResponseEquipmentRepairDto(EquipmentRepair equipmentRepair);
}