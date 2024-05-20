package ru.nabokovsg.laboratoryNK.mapper.measurement.common;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.equipmentInspection.EquipmentInspectionDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.equipmentInspection.ResponseEquipmentInspectionDto;
import ru.nabokovsg.laboratoryNK.model.measurement.common.EquipmentInspection;

@Mapper(componentModel = "spring")
public interface EquipmentInspectionMapper {

    EquipmentInspection mapToEquipmentInspection(EquipmentInspectionDto inspectionDto);

    ResponseEquipmentInspectionDto mapToResponseEquipmentInspectionDto(EquipmentInspection inspection);
}