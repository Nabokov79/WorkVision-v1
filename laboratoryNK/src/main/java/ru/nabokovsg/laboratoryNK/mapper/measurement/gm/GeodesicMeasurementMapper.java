package ru.nabokovsg.laboratoryNK.mapper.measurement.gm;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.measurement.geodesicMeasurement.GeodesicMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.geodesicMeasurement.ResponseGeodesicMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.GeodesicMeasurement;

@Mapper(componentModel = "spring")
public interface GeodesicMeasurementMapper {

    @Mapping(source = "surveyJournalId", target = "surveyJournalId")
    @Mapping(source = "equipmentId", target = "equipmentId")
    @Mapping(source = "geodesicMeasurementDto.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "geodesicMeasurementDto.numberMeasurementLocation", target = "numberMeasurementLocation")
    @Mapping(source = "geodesicMeasurementDto.referencePointValue", target = "referencePointValue")
    @Mapping(source = "geodesicMeasurementDto.controlPointValue", target = "controlPointValue")
    @Mapping(source = "geodesicMeasurementDto.transitionValue", target = "transitionValue")
    @Mapping(source = "geodesicMeasurementDto.id", target = "id")
    GeodesicMeasurement mapToGeodesicMeasurement(GeodesicMeasurementDto geodesicMeasurementDto
                                               , Long equipmentId
                                               , Long surveyJournalId);

    ResponseGeodesicMeasurementDto mapToResponseGeodesicMeasurementDto(GeodesicMeasurement geodesicMeasurement);
}