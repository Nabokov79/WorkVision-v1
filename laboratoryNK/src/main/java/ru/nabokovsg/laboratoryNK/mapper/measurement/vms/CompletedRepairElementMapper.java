package ru.nabokovsg.laboratoryNK.mapper.measurement.vms;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.completedRepairElement.CompletedRepairElementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.completedRepairElement.ResponseCompletedRepairElementDto;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.VMSurvey;
import ru.nabokovsg.laboratoryNK.model.norms.ElementRepair;

@Mapper(componentModel = "spring")
public interface CompletedRepairElementMapper {

    @Mapping(source = "elementRepair.id", target = "repairId")
    @Mapping(source = "elementRepair.repairName", target = "repairName")
    @Mapping(source = "vmSurvey", target = "vmSurvey")
    @Mapping(source = "completedRepairDto.id", target = "id")
    @Mapping(target = "parameterMeasurements", ignore = true)
    CompletedRepairElement mapToCompletedRepairElement(CompletedRepairElementDto completedRepairDto
                                                    , ElementRepair elementRepair
                                                    , VMSurvey vmSurvey);

    ResponseCompletedRepairElementDto mapToResponseCompletedRepairElementDto(CompletedRepairElement repair);
}