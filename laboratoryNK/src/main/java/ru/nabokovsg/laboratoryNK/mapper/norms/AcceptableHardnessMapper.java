package ru.nabokovsg.laboratoryNK.mapper.norms;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.dto.norms.acceptableHardness.AcceptableHardnessDto;
import ru.nabokovsg.laboratoryNK.dto.norms.acceptableHardness.ResponseAcceptableHardnessDto;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableHardness;

@Mapper(componentModel = "spring")
public interface AcceptableHardnessMapper {

    AcceptableHardness mapToAcceptableHardness(AcceptableHardnessDto hardnessDto);

    ResponseAcceptableHardnessDto mapToResponseAcceptableHardnessDto(AcceptableHardness hardness);
}