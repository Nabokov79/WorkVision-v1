package ru.nabokovsg.laboratoryNK.mapper.measurement.gm;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.DeviationYear;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.ReferencePoint;

@Mapper(componentModel = "spring")
public interface DeviationYearMapper {

    @Mapping(source = "year", target = "year")
    @Mapping(source = "referencePoint.deviation", target = "deviation")
    @Mapping(source = "referencePoint", target = "referencePoint")
    @Mapping(target = "id", ignore = true)
    DeviationYear mapToDeviationYear(Integer year, ReferencePoint referencePoint);

    @Mapping(source = "year", target = "year")
    @Mapping(source = "referencePoint.deviation", target = "deviation")
    @Mapping(source = "referencePoint", target = "referencePoint")
    @Mapping(source = "id", target = "id")
    DeviationYear mapToUpdateDeviationYear(Integer year, ReferencePoint referencePoint, Long id);
}