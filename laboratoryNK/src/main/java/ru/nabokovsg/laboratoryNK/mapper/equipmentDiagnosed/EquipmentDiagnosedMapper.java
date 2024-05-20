package ru.nabokovsg.laboratoryNK.mapper.equipmentDiagnosed;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipments.EquipmentDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipments.ResponseEquipmentDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipments.ResponseShortEquipmentDto;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentDiagnosed;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentType;

@Mapper(componentModel = "spring")
public interface EquipmentDiagnosedMapper {

    @Mapping(source = "equipmentType", target = "equipmentType")
    @Mapping(source = "equipmentDto.buildingId", target = "buildingId")
    @Mapping(source = "equipmentType.equipmentName", target = "equipmentName")
    @Mapping(source = "equipmentDto.stationaryNumber", target = "stationaryNumber")
    @Mapping(source = "equipmentDto.volume", target = "volume")
    @Mapping(source = "equipmentType.model", target = "model")
    @Mapping(source = "equipmentDto.id", target = "id")
    EquipmentDiagnosed mapToEquipment(EquipmentDto equipmentDto, EquipmentType equipmentType);

    ResponseShortEquipmentDto mapToResponseShortEquipmentDto(EquipmentDiagnosed equipment);

    ResponseEquipmentDto mapToResponseEquipmentDto(EquipmentDiagnosed equipment);
}