package ru.nabokovsg.laboratoryNK.mapper.vms.norm;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.vms.norm.elementRepair.ElementRepairDto;
import ru.nabokovsg.laboratoryNK.dto.vms.norm.elementRepair.ResponseElementRepairDto;
import ru.nabokovsg.laboratoryNK.dto.vms.norm.elementRepair.ResponseShortElementRepairDto;
import ru.nabokovsg.laboratoryNK.model.vms.norm.ElementRepair;
import ru.nabokovsg.laboratoryNK.model.vms.norm.CalculationType;

@Mapper(componentModel = "spring")
public interface ElementRepairMapper {

    @Mapping(source = "repairDto.id", target = "id")
    @Mapping(source = "repairDto.repairName", target = "repairName")
    @Mapping(source = "typeCalculation", target = "typeCalculation")
    ElementRepair mapToElementRepair(ElementRepairDto repairDto, CalculationType typeCalculation);

    ResponseElementRepairDto mapToResponseElementRepairDto(ElementRepair repair);

    ResponseShortElementRepairDto mapToResponseShortElementRepairDto(ElementRepair repair);
}