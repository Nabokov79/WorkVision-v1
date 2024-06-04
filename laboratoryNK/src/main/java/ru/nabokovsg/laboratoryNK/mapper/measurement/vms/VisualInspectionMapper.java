package ru.nabokovsg.laboratoryNK.mapper.measurement.vms;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.visualInspection.ResponseVisualInspectionDto;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.VMSurvey;

@Mapper(componentModel = "spring")
public interface VisualInspectionMapper {

    ResponseVisualInspectionDto mapToResponseVisualInspectionDto(VMSurvey vmSurvey);
}