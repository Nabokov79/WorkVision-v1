package ru.nabokovsg.laboratoryNK.mapper.norms;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.norms.defects.DefectDto;
import ru.nabokovsg.laboratoryNK.dto.norms.defects.ResponseDefectDto;
import ru.nabokovsg.laboratoryNK.dto.norms.defects.ResponseShortDefectDto;
import ru.nabokovsg.laboratoryNK.model.norms.Defect;
import ru.nabokovsg.laboratoryNK.model.norms.TypeCalculation;

@Mapper(componentModel = "spring")
public interface DefectMapper {

    @Mapping(source = "defectDto.id", target = "id")
    @Mapping(source = "defectDto.defectName", target = "defectName")
    @Mapping(source = "typeCalculation", target = "typeCalculation")
    @Mapping(source = "defectDto.notMeetRequirements", target = "notMeetRequirements")
    @Mapping(source = "defectDto.useCalculateThickness", target = "useCalculateThickness")
    Defect mapToDefect(DefectDto defectDto, TypeCalculation typeCalculation);

    ResponseDefectDto mapToResponseDefectDto(Defect defect);

    ResponseShortDefectDto mapToResponseShortDefectDto(Defect defect);
}