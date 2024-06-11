package ru.nabokovsg.laboratoryNK.mapper.vms.measurement;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.visualInspection.ResponseVisualInspectionDto;
import ru.nabokovsg.laboratoryNK.model.vms.EquipmentSurvey;

@Mapper(componentModel = "spring")
public interface VisualInspectionMapper {

    ResponseVisualInspectionDto mapToResponseVisualInspectionDto(EquipmentSurvey vmSurvey);
}