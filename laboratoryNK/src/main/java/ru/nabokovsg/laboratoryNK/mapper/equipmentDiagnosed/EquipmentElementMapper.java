package ru.nabokovsg.laboratoryNK.mapper.equipmentDiagnosed;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.element.ElementDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.element.ResponseElementDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.element.ShortResponseElementDto;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentDiagnosed;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentElement;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.StandardSize;

@Mapper(componentModel = "spring")
public interface EquipmentElementMapper {

    @Mapping(source = "elementDto.elementName", target = "elementName")
    @Mapping(source = "equipment", target = "equipmentDiagnosed")
    @Mapping(source = "elementDto.id", target = "id")
    EquipmentElement mapToElement(ElementDto elementDto, EquipmentDiagnosed equipment);

    ShortResponseElementDto mapToShortElementDto(EquipmentElement element);

    ResponseElementDto mapToElementDto(EquipmentElement element);

    @Mapping(source = "element.elementName", target = "elementName")
    @Mapping(source = "standardSize", target = "standardSize")
    @Mapping(source = "element.id", target = "id")
    EquipmentElement mapElementWithStandardSize(EquipmentElement element, StandardSize standardSize);
}