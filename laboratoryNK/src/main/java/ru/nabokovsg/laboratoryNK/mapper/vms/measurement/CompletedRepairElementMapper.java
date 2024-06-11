package ru.nabokovsg.laboratoryNK.mapper.vms.measurement;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.completedRepairElement.CompletedRepairElementDto;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.completedRepairElement.ResponseCompletedRepairElementDto;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.CompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.vms.EquipmentSurvey;
import ru.nabokovsg.laboratoryNK.model.vms.norm.ElementRepair;

@Mapper(componentModel = "spring")
public interface CompletedRepairElementMapper {

    @Mapping(source = "elementRepair.id", target = "repairId")
    @Mapping(source = "elementRepair.repairName", target = "repairName")
    @Mapping(source = "equipmentSurvey", target = "equipmentSurvey")
    @Mapping(source = "completedRepairDto.id", target = "id")
    @Mapping(target = "parameterMeasurements", ignore = true)
    CompletedRepairElement mapToCompletedRepairElement(CompletedRepairElementDto completedRepairDto
                                                    , ElementRepair elementRepair
                                                    , EquipmentSurvey equipmentSurvey);

    ResponseCompletedRepairElementDto mapToResponseCompletedRepairElementDto(CompletedRepairElement repair);
}