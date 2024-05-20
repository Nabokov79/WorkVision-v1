package ru.nabokovsg.laboratoryNK.mapper.measurement.vms;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.defectMeasurement.DefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.defectMeasurement.ResponseDefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentElement;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.PartElement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.DefectMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.Defect;

@Mapper(componentModel = "spring")
public interface DefectMeasurementMapper {

    @Mapping(source = "defectDto.surveyJournalId", target = "surveyJournalId")
    @Mapping(source = "element.id", target = "elementId")
    @Mapping(source = "element.elementName", target = "elementName")
    @Mapping(source = "defectDto.partElementId", target = "partElementId")
    @Mapping(source = "defect.id", target = "defectId")
    @Mapping(source = "defect.defectName", target = "defectName")
    @Mapping(source = "defect.useCalculateThickness", target = "useCalculateThickness")
    @Mapping(source = "defect.notMeetRequirements", target = "notMeetRequirements")
    @Mapping(source = "defectDto.id", target = "id")
    @Mapping(target = "parameterMeasurements", ignore = true)
    DefectMeasurement mapWithEquipmentElement(DefectMeasurementDto defectDto, Defect defect, EquipmentElement element);

    @Mapping(source = "partElement.id", target = "partElementId")
    @Mapping(source = "partElement.partName", target = "partName")
    DefectMeasurement mapWithPartElement(@MappingTarget DefectMeasurement measurement, PartElement partElement);

    ResponseDefectMeasurementDto mapToResponseDefectMeasurementDto(DefectMeasurement defectMeasurement);
}