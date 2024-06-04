package ru.nabokovsg.laboratoryNK.mapper.measurement.vms;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.defectMeasurement.DefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.defectMeasurement.ResponseDefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.DefectMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.VMSurvey;
import ru.nabokovsg.laboratoryNK.model.norms.Defect;

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