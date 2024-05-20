package ru.nabokovsg.laboratoryNK.mapper.norms;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.norms.elementRepair.ElementRepairDto;
import ru.nabokovsg.laboratoryNK.dto.norms.elementRepair.ResponseElementRepairDto;
import ru.nabokovsg.laboratoryNK.dto.norms.elementRepair.ResponseShortElementRepairDto;
import ru.nabokovsg.laboratoryNK.model.norms.ElementRepair;
import ru.nabokovsg.laboratoryNK.model.norms.TypeCalculation;

@Mapper(componentModel = "spring")
public interface ElementRepairMapper {

    @Mapping(source = "repairDto.id", target = "id")
    @Mapping(source = "repairDto.repairName", target = "repairName")
    @Mapping(source = "typeCalculation", target = "typeCalculation")
    ElementRepair mapToElementRepair(ElementRepairDto repairDto, TypeCalculation typeCalculation);

    ResponseElementRepairDto mapToResponseElementRepairDto(ElementRepair repair);

    ResponseShortElementRepairDto mapToResponseShortElementRepairDto(ElementRepair repair);
}