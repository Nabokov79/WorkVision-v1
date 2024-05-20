package ru.nabokovsg.laboratoryNK.mapper.equipmentDiagnosed;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.partElement.PartElementDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.partElement.ResponsePartElementDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.partElement.ShortResponsePartElementDto;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentElement;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.PartElement;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.StandardSize;

@Mapper(componentModel = "spring")
public interface PartElementMapper {

    @Mapping(source = "partElementDto.partName", target = "partName")
    @Mapping(source = "element", target = "element")
    @Mapping(target = "standardSize", ignore = true)
    @Mapping(source = "partElementDto.id", target = "id")
    PartElement mapToPartElement(PartElementDto partElementDto, EquipmentElement element);

    ShortResponsePartElementDto mapToShortPartElementDto(PartElement partElement);

    ResponsePartElementDto mapToFullPartElementDto(PartElement partElement);

    @Mapping(source = "partElement.partName", target = "partName")
    @Mapping(source = "standardSize", target = "standardSize")
    @Mapping(source = "partElement.id", target = "id")
    PartElement mapPartElementWithStandardSize(PartElement partElement, StandardSize standardSize);
}