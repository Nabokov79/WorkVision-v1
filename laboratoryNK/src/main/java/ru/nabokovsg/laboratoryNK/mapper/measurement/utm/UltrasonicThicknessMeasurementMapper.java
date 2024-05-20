package ru.nabokovsg.laboratoryNK.mapper.measurement.utm;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.measurement.utm.ResponseUltrasonicThicknessMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.utm.UltrasonicThicknessMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.UltrasonicThicknessMeasurement;

@Mapper(componentModel = "spring")
public interface UltrasonicThicknessMeasurementMapper {

    @Mapping(source = "measurementDto.id", target = "id")
    @Mapping(source = "measurementDto.surveyJournalId", target = "surveyJournalId")
    @Mapping(source = "measurementDto.elementId", target = "elementId")
    @Mapping(source = "measurementDto.partElementId", target = "partElementId")
    @Mapping(source = "measurementDto.diameter", target = "diameter")
    @Mapping(source = "measurementDto.measurementNumber", target = "measurementNumber")
    @Mapping(source = "measurementDto.minMeasurementValue", target = "minMeasurementValue")
    @Mapping(source = "measurementDto.maxMeasurementValue", target = "maxMeasurementValue")
    UltrasonicThicknessMeasurement mapToUltrasonicThicknessMeasurement(UltrasonicThicknessMeasurementDto measurementDto);

    ResponseUltrasonicThicknessMeasurementDto mapToResponseUltrasonicThicknessMeasurementDto(UltrasonicThicknessMeasurement measurement);
}