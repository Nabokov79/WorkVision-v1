package ru.nabokovsg.laboratoryNK.mapper.measurement.gm;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.GeodesicMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.ReferencePoint;

@Mapper(componentModel = "spring")
public interface ReferencePointMapper {

    @Mapping(source = "deviation", target = "deviation")
    @Mapping(source = "precipitation", target = "precipitation")
    @Mapping(source = "acceptablePrecipitation", target = "acceptablePrecipitation")
    ReferencePoint mapWithReferencePointData(@MappingTarget ReferencePoint point
                                           , Integer deviation
                                           , Integer precipitation
                                           , Boolean acceptablePrecipitation);

    @Mapping(source = "measurement.equipmentId", target = "equipmentId")
    @Mapping(source = "measurement.numberMeasurementLocation", target = "placeNumber")
    @Mapping(source = "measurement.referencePointValue", target = "calculatedHeight")
    @Mapping(target = "id", ignore = true)
    ReferencePoint mapToReferencePoint(GeodesicMeasurement measurement);

    @Mapping(source = "measurement.equipmentId", target = "equipmentId")
    @Mapping(source = "measurement.numberMeasurementLocation", target = "placeNumber")
    @Mapping(source = "measurement.referencePointValue", target = "calculatedHeight")
    @Mapping(target = "id", ignore = true)
    ReferencePoint mapToUpdateReferencePoint(@MappingTarget ReferencePoint point, GeodesicMeasurement measurement);
}