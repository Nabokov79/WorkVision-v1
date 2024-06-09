package ru.nabokovsg.laboratoryNK.mapper.vms.measurement;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.defectMeasurement.DefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.defectMeasurement.ResponseDefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.DefectMeasurement;
import ru.nabokovsg.laboratoryNK.model.vms.VMSurvey;
import ru.nabokovsg.laboratoryNK.model.vms.norm.Defect;

@Mapper(componentModel = "spring")
public interface DefectMeasurementMapper {

    @Mapping(source = "defect.id", target = "defectId")
    @Mapping(source = "defect.defectName", target = "defectName")
    @Mapping(source = "defect.useCalculateThickness", target = "useCalculateThickness")
    @Mapping(source = "defect.notMeetRequirements", target = "notMeetRequirements")
    @Mapping(source = "vmSurvey", target = "vmSurvey")
    @Mapping(source = "defectDto.id", target = "id")
    @Mapping(target = "parameterMeasurements", ignore = true)
    DefectMeasurement mapWithEquipmentElement(DefectMeasurementDto defectDto, Defect defect, VMSurvey vmSurvey);
    ResponseDefectMeasurementDto mapToResponseDefectMeasurementDto(DefectMeasurement defectMeasurement);
}