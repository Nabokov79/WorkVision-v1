package ru.nabokovsg.laboratoryNK.mapper.measurement.vms;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.visualInspection.ResponseVisualInspectionDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.visualInspection.VisualInspectionDto;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentElement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.VisualInspection;

@Mapper(componentModel = "spring")
public interface VisualInspectionMapper {

    @Mapping(source = "inspectionDto.surveyJournalId", target = "surveyJournalId")
    @Mapping(source = "inspectionDto.equipmentId", target = "equipmentId")
    @Mapping(source = "element.id", target = "elementId")
    @Mapping(source = "element.elementName", target = "elementName")
    @Mapping(source = "inspectionDto.inspection", target = "inspection")
    @Mapping(source = "inspectionDto.id", target = "id")
    VisualInspection mapToVisualInspection(VisualInspectionDto inspectionDto, EquipmentElement element);

    ResponseVisualInspectionDto mapToResponseVisualInspectionDto(VisualInspection visualInspection);
}