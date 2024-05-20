package ru.nabokovsg.laboratoryNK.mapper.measurement;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryNK.dto.measurement.hardnessMeasurement.HardnessMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.hardnessMeasurement.ResponseHardnessMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentElement;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.PartElement;
import ru.nabokovsg.laboratoryNK.model.measurement.HardnessMeasurement;


@Mapper(componentModel = "spring")
public interface HardnessMeasurementMapper {

    @Mapping(source = "measurementDto.surveyJournalId", target = "surveyJournalId")
    @Mapping(source = "measurementDto.equipmentId", target = "equipmentId")
    @Mapping(source = "element.id", target = "elementId")
    @Mapping(source = "element.elementName", target = "elementName")
    @Mapping(source = "measurementDto.measurementNumber", target = "measurementNumber")
    @Mapping(source = "measurementDto.measurementValue", target = "measurementValue")
    @Mapping(source = "measurementDto.id", target = "id")
    HardnessMeasurement mapWithEquipmentElement(HardnessMeasurementDto measurementDto, EquipmentElement element);


    @Mapping(source = "partElement.id", target = "partElementId")
    @Mapping(source = "partElement.partName", target = "partName")
    HardnessMeasurement mapWithPartElement(@MappingTarget HardnessMeasurement measurement, PartElement partElement);

    ResponseHardnessMeasurementDto mapToResponseHardnessMeasurementDto(HardnessMeasurement measurement);


    @Mapping(source = "acceptableValue", target = "acceptableValue")
    HardnessMeasurement mapHardnessMeasurementWithAcceptableValue(@MappingTarget HardnessMeasurement measurement
                                                                               , Boolean acceptableValue);
}