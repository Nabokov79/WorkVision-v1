package ru.nabokovsg.laboratoryNK.mapper.equipmentDiagnosed;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.standardSize.StandardSizeDto;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.StandardSize;

@Mapper(componentModel = "spring")
public interface StandardSizeMapper {

    StandardSize mapToStandardSize(StandardSizeDto standardSizeDto);
}