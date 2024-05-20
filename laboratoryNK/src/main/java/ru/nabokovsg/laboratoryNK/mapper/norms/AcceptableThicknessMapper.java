package ru.nabokovsg.laboratoryNK.mapper.norms;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.dto.norms.acceptableThickness.AcceptableThicknessDto;
import ru.nabokovsg.laboratoryNK.dto.norms.acceptableThickness.ResponseAcceptableThicknessDto;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableThickness;

@Mapper(componentModel = "spring")
public interface AcceptableThicknessMapper {

    AcceptableThickness mapToAcceptableThickness(AcceptableThicknessDto thicknessDto);

    ResponseAcceptableThicknessDto mapToResponseAcceptableThicknessDto(AcceptableThickness thickness);
}