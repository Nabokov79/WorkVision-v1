package ru.nabokovsg.laboratoryNK.mapper.norms;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.dto.norms.geodesy.AcceptableDeviationsGeodesyDto;
import ru.nabokovsg.laboratoryNK.dto.norms.geodesy.ResponseAcceptableDeviationsGeodesyDto;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableDeviationsGeodesy;

@Mapper(componentModel = "spring")
public interface AcceptableDeviationsGeodesyMapper {

    AcceptableDeviationsGeodesy mapToPermissibleDeviationsGeodesy(AcceptableDeviationsGeodesyDto geodesyDto);

    ResponseAcceptableDeviationsGeodesyDto
                                    mapToResponseAcceptableDeviationsGeodesyDto(AcceptableDeviationsGeodesy geodesy);
}